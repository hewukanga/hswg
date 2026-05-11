package com.jiumai.base.sm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiumai.base.common.core.dto.InterfaceResourceBO;
import com.jiumai.base.common.core.dto.InterfaceRoleBO;
import com.jiumai.base.common.core.service.RedisCurrentOperatorServiceImpl;
import com.jiumai.base.common.core.utils.CommonConstant;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.dto.RelRoleInterfaceDTO;
import com.jiumai.base.sm.dto.RoleDTO;
import com.jiumai.base.sm.entity.InterfaceConf;
import com.jiumai.base.sm.entity.RelRoleInterface;
import com.jiumai.base.sm.entity.SmRelResRole;
import com.jiumai.base.sm.entity.SmResource;
import com.jiumai.base.sm.enums.RelTypeEnum;
import com.jiumai.base.sm.mapper.RelRoleInterfaceMapper;
import com.jiumai.base.sm.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 接口权限关联管理 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2023-08-21
 */
@Service
@Slf4j
public class RelRoleInterfaceServiceImpl extends ServiceImpl<RelRoleInterfaceMapper, RelRoleInterface> implements RelRoleInterfaceService {

    @Resource
    private RelRoleInterfaceMapper relRoleInterfaceMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RelResRoleService relResRoleService;

    @Resource
    private InterfaceConfService interfaceConfService;

    @Override
    public void handleRelRoleInterface(RoleDTO roleDTO, List<RelRoleInterfaceDTO> relRoleInterfaceDTOS) {
        //删除
        relRoleInterfaceMapper.delete(new QueryWrapper<RelRoleInterface>().lambda().eq(RelRoleInterface::getRoleId, roleDTO.getRoleId()));

        //新增
        List<RelRoleInterface> relRoleInterfaces = new ArrayList<>();
        for (RelRoleInterfaceDTO relRoleInterfaceDTO : relRoleInterfaceDTOS) {
            RelRoleInterface relRoleInterface = new RelRoleInterface();
            relRoleInterface.setInterfaceId(relRoleInterfaceDTO.getInterfaceId());
            relRoleInterface.setInterfaceName(relRoleInterfaceDTO.getInterfaceName());
            relRoleInterface.setInterfaceUri(relRoleInterfaceDTO.getInterfaceUri());
            relRoleInterface.setRoleId(roleDTO.getRoleId());
            relRoleInterfaces.add(relRoleInterface);
        }
        saveBatch(relRoleInterfaces);

        //更新缓存
        List<InterfaceRoleBO> interfaceRoleBOS = BeanUtil.copyToList(relRoleInterfaces, InterfaceRoleBO.class);
        //roleId作为hashKey value为角色具有访问权限的接口信息
        stringRedisTemplate.opsForHash().put(CommonConstant.INTERFACE_ROLE_BASE_KEY, roleDTO.getRoleId(), JSONObject.toJSONString(interfaceRoleBOS));
    }

    @Override
    public void handleLoginRelRoleInterface(Long opId, List<Long> roleIds, String token) {
        //存储opId和token的关系，允许覆盖，用于修改操作员角色和资源时同步修改此操作员最新token和角色、资源的绑定关系
//        stringRedisTemplate.opsForValue().set(String.format(CommonConstant.OP_ID_REL_TOKEN_KEY, opId), token, RedisCurrentOperatorServiceImpl.REQUEST_TOKEN_EXIST_TIME);
        //角色不为空才进行存储 否则无意义
//        if (CommonFuntions.isNotEmptyObject(roleIds)) {
//            insertRoleAndResPermissionCache(roleIds, token);
//        }
        //3.校验权限基础池是否存在 不存在则重新初始化
//        checkInterfaceBaseDataExist();
    }

    @Override
    public void updateLoginRelRoleInterface(Long opId, List<Long> roleIds) {
        String token = stringRedisTemplate.opsForValue().get(String.format(CommonConstant.OP_ID_REL_TOKEN_KEY, opId));
        //token为空 不处理 下次登录时自然是最新的权限
        if (CommonFuntions.isEmptyObject(token)) {
            return;
        }
        //roleIds为空情况 需要清空此操作员的接口权限
        if (CommonFuntions.isEmptyObject(roleIds)) {
            stringRedisTemplate.delete(String.format(CommonConstant.INTERFACE_AUTH_TOKEN_REL_ROLE_KEY, token));
            stringRedisTemplate.delete(String.format(CommonConstant.INTERFACE_AUTH_TOKEN_REL_RESOURCE_KEY, token));
        } else {
            //roleIds不为空清空 更新接口权限
            insertRoleAndResPermissionCache(roleIds, token);
        }
    }

    /**
     * 插入角色和资源的权限到缓存
     *
     * @param roleIds
     * @param token
     */
    private void insertRoleAndResPermissionCache(List<Long> roleIds, String token) {
        List<String> roleIdStr = roleIds.stream().map(r -> String.valueOf(r)).collect(Collectors.toList());
        //1.存入token对应的角色id
        stringRedisTemplate.opsForValue().set(String.format(CommonConstant.INTERFACE_AUTH_TOKEN_REL_ROLE_KEY, token), JSONObject.toJSONString(roleIdStr), RedisCurrentOperatorServiceImpl.REQUEST_TOKEN_EXIST_TIME);

        //2.存入token对应的资源id
        List<SmRelResRole> resRoles = relResRoleService.list(new QueryWrapper<SmRelResRole>().lambda().in(SmRelResRole::getRoleId, roleIds).eq(SmRelResRole::getRelType, RelTypeEnum.REAL).select(SmRelResRole::getResourceId));
        if (CommonFuntions.isNotEmptyObject(resRoles)) {
            Set<Long> resId = resRoles.stream().map(SmRelResRole::getResourceId).collect(Collectors.toSet());
            stringRedisTemplate.opsForValue().set(String.format(CommonConstant.INTERFACE_AUTH_TOKEN_REL_RESOURCE_KEY, token), JSONObject.toJSONString(resId), RedisCurrentOperatorServiceImpl.REQUEST_TOKEN_EXIST_TIME);
        }
    }

    /**
     * 校验权限基础池是否存在
     */
    public void checkInterfaceBaseDataExist() {
        //------------------------------------------接口与菜单/按钮关联关系基础池-----------------------------------
        if (!stringRedisTemplate.hasKey(CommonConstant.INTERFACE_RESOURCE_BASE_KEY)) {
            List<InterfaceConf> interfaceConfList = interfaceConfService.list(new QueryWrapper<InterfaceConf>().lambda().select(InterfaceConf::getInterfaceName, InterfaceConf::getInterfacePath, InterfaceConf::getInterfaceId, InterfaceConf::getResourceId));
            if (CommonFuntions.isNotEmptyObject(interfaceConfList)) {
                for (InterfaceConf interfaceConf : interfaceConfList) {
                    InterfaceResourceBO interfaceResourceBO = new InterfaceResourceBO();
                    interfaceResourceBO.setInterfaceId(interfaceConf.getInterfaceId());
                    interfaceResourceBO.setInterfacePath(interfaceConf.getInterfacePath());
                    interfaceResourceBO.setInterfaceName(interfaceConf.getInterfaceName());
                    interfaceResourceBO.setResourceId(interfaceConf.getResourceId());
                    //资源id作为hashKey value为资源对应的接口信息
                    stringRedisTemplate.opsForHash().put(CommonConstant.INTERFACE_RESOURCE_BASE_KEY, String.valueOf(interfaceConf.getResourceId()), JSONObject.toJSONString(interfaceResourceBO));
                }
            }
        }


        //------------------------------------------接口与角色的关联关系基础池---------------------------------------
        if (!stringRedisTemplate.hasKey(CommonConstant.INTERFACE_ROLE_BASE_KEY)) {
            List<RelRoleInterface> relRoleInterfaces = list(new QueryWrapper<RelRoleInterface>().lambda().select(RelRoleInterface::getInterfaceId, RelRoleInterface::getInterfaceUri, RelRoleInterface::getInterfaceName, RelRoleInterface::getRoleId));
            if (CommonFuntions.isNotEmptyObject(relRoleInterfaces)) {
                Map<Long, List<RelRoleInterface>> map = relRoleInterfaces.stream().collect(Collectors.groupingBy(RelRoleInterface::getRoleId));
                for (Map.Entry<Long, List<RelRoleInterface>> entry : map.entrySet()) {
                    List<RelRoleInterface> list = entry.getValue();
                    List<InterfaceRoleBO> interfaceRoleBOS = BeanUtil.copyToList(list, InterfaceRoleBO.class);
                    //roleId作为hashKey value为角色具有访问权限的接口信息
                    stringRedisTemplate.opsForHash().put(CommonConstant.INTERFACE_ROLE_BASE_KEY, String.valueOf(entry.getKey()), JSONObject.toJSONString(interfaceRoleBOS));
                }
            }
        }
    }
}
