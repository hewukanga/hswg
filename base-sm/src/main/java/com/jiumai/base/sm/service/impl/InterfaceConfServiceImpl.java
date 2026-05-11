package com.jiumai.base.sm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiumai.base.common.core.dto.InterfaceResourceBO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonConstant;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.dto.InterfaceConfDTO;
import com.jiumai.base.sm.dto.InterfaceConfTreeDTO;
import com.jiumai.base.sm.entity.SmResource;
import com.jiumai.base.sm.query.InterfaceConfQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jiumai.base.sm.entity.InterfaceConf;
import com.jiumai.base.sm.mapper.InterfaceConfMapper;
import com.jiumai.base.sm.service.InterfaceConfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.sm.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 接口管理 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2023-08-21
 */
@Service
public class InterfaceConfServiceImpl extends ServiceImpl<InterfaceConfMapper, InterfaceConf> implements InterfaceConfService {

    @Resource
    private InterfaceConfMapper interfaceConfMapper;

    @Resource
    private ResourceService resourceService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Page<InterfaceConfDTO> findInterfaceConfPage(InterfaceConfQuery query) {
        Page<InterfaceConfDTO> page = new Page<>(query.getPage(),query.getSize());

        List<InterfaceConfDTO> list = interfaceConfMapper.findInterfaceConfPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateInterfaceConf(InterfaceConfDTO interfaceConfDTO){
        //校验请求参数
        checkParams(interfaceConfDTO);

        //校验资源是否存在
        long count = resourceService.count(new QueryWrapper<SmResource>()
                .lambda()
                .eq(SmResource::getResourceId, interfaceConfDTO.getResourceId())
                .eq(SmResource::getResourceType, interfaceConfDTO.getResourceType())
        );

        //资源不存在
        if(count <= 0){
            throw new BizException(ResultCodeEnum.FAIL, "资源不存在");
        }

        InterfaceConf interfaceConf = new InterfaceConf();

        BeanUtils.copyProperties(interfaceConfDTO, interfaceConf);

        this.saveOrUpdate(interfaceConf);

        //更新redis
        InterfaceResourceBO interfaceResourceBO = new InterfaceResourceBO();
        interfaceResourceBO.setInterfaceId(interfaceConf.getInterfaceId());
        interfaceResourceBO.setInterfacePath(interfaceConf.getInterfacePath());
        interfaceResourceBO.setInterfaceName(interfaceConf.getInterfaceName());
        interfaceResourceBO.setResourceId(interfaceConf.getResourceId());
        //资源id作为hashKey value为资源对应的接口信息
        stringRedisTemplate.opsForHash().put(CommonConstant.INTERFACE_RESOURCE_BASE_KEY, String.valueOf(interfaceConf.getResourceId()), JSONObject.toJSONString(interfaceResourceBO));
        return interfaceConf.getInterfaceId();
    }

    @Override
    public InterfaceConfDTO getInterfaceConfById(Long id){
        InterfaceConf interfaceConf = this.getById(id);
        if (CommonFuntions.isEmptyObject(interfaceConf)){
            throw new BizException("查询失败，接口管理不存在");
        }

        InterfaceConfDTO interfaceConfDTO = new InterfaceConfDTO();
        BeanUtils.copyProperties(interfaceConf, interfaceConfDTO);

        return interfaceConfDTO;
    }

    @Override
    public void removeInterfaceConfById(Long id) {
        InterfaceConf interfaceConf = getById(id);
        interfaceConfMapper.deleteById(id);

        //更新redis
        stringRedisTemplate.opsForHash().delete(CommonConstant.INTERFACE_RESOURCE_BASE_KEY, String.valueOf(interfaceConf.getResourceId()));
    }

    @Override
    public List<InterfaceConfTreeDTO> findInterfaceConfTree() {
        List<InterfaceConf> interfaceConfList = list();
        if(CommonFuntions.isEmptyObject(interfaceConfList)){
            return new ArrayList<>();
        }
        Map<String, List<InterfaceConf>> map = interfaceConfList.stream().collect(Collectors.groupingBy(InterfaceConf::getInterfaceGroupName));
        List<InterfaceConfTreeDTO> interfaceConfTreeDTOS = new ArrayList<>();
        for (Map.Entry<String, List<InterfaceConf>> entry : map.entrySet()){
            InterfaceConfTreeDTO interfaceConfTreeDTO = new InterfaceConfTreeDTO();
            interfaceConfTreeDTO.setInterfaceGroupName(entry.getKey());
            interfaceConfTreeDTO.setInterfaceConfDTOS(BeanUtil.copyToList(entry.getValue(), InterfaceConfDTO.class));
            interfaceConfTreeDTOS.add(interfaceConfTreeDTO);
        }
        return interfaceConfTreeDTOS;
    }

    /**
     * 校验请求参数
     * @param interfaceConfDTO
     */
    private void checkParams(InterfaceConfDTO interfaceConfDTO){
        if(CommonFuntions.isEmptyObject(interfaceConfDTO.getInterfaceName())){
            throw new BizException(ResultCodeEnum.ERROR_MISSING_PARAMS, "接口名称不允许为空");
        }
        if(CommonFuntions.isEmptyObject(interfaceConfDTO.getInterfaceUri())){
            throw new BizException(ResultCodeEnum.ERROR_MISSING_PARAMS, "接口路径不允许为空");
        }
        if(CommonFuntions.isEmptyObject(interfaceConfDTO.getInterfaceGroupName())){
            throw new BizException(ResultCodeEnum.ERROR_MISSING_PARAMS, "接口组名不允许为空");
        }
        if(CommonFuntions.isEmptyObject(interfaceConfDTO.getResourceId())){
            throw new BizException(ResultCodeEnum.ERROR_MISSING_PARAMS, "资源id不允许为空");
        }
        if(CommonFuntions.isEmptyObject(interfaceConfDTO.getResourceName())){
            throw new BizException(ResultCodeEnum.ERROR_MISSING_PARAMS, "资源名称不允许为空");
        }
        if(CommonFuntions.isEmptyObject(interfaceConfDTO.getResourceType())){
            throw new BizException(ResultCodeEnum.ERROR_MISSING_PARAMS, "资源类型不允许为空");
        }
    }
}
