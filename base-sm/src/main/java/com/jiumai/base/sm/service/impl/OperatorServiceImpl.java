/*
 * OperatorServiceImpl.java Created on 2016年10月24日 下午3:08:20
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jiumai.base.sm.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.dto.TokenDTO;
import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.jwt.JwtConfig;
import com.jiumai.base.common.core.jwt.JwtHelper;
import com.jiumai.base.common.core.service.CurrentOperatorService;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.HttpUtils;
import com.jiumai.base.common.core.utils.PageUtils;
import com.jiumai.base.common.core.utils.SpringUtils;
import com.jiumai.base.sm.dto.AreaDTO;
import com.jiumai.base.sm.dto.OperatorDTO;
import com.jiumai.base.sm.dto.OrganizationDTO;
import com.jiumai.base.sm.dto.RoleDTO;
import com.jiumai.base.sm.entity.*;
import com.jiumai.base.sm.enums.CodeStatusEnum;
import com.jiumai.base.sm.enums.RelTypeEnum;
import com.jiumai.base.sm.mapper.SmOperatorMapper;
import com.jiumai.base.sm.query.OperatorQuery;
import com.jiumai.base.sm.service.*;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 */
@Service(value = "DbCurrentOperator")
@Slf4j
public class OperatorServiceImpl extends ServiceImpl<SmOperatorMapper, SmOperator> implements OperatorService, CurrentOperatorService {

    @Resource
    private OrganizationService organizationService;
    @Resource
    private RelOpOrgService relOpOrgService;
    @Resource
    private RelOpRoleService relOpRoleService;
    @Resource
    private RelOpAreaService relOpAreaService;
    @Resource
    private RoleService roleService;
    @Resource
    private AreaService areaService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SmOperatorMapper smOperatorMapper;

    @Resource
    private RelRoleInterfaceService relRoleInterfaceService;

    public static void main(String[] args) {
        String[] a = new String[]{};
        System.out.println(a.length);
    }

    @Override
    public IPage<OperatorDTO> findOperatorPaging(OperatorQuery operQuery) throws Exception {
        if (CommonFuntions.isEmptyObject(operQuery.getStatus())) {
            operQuery.setStatus(null);
        }
        if (operQuery.getCreateId() != 1) {
            List<Long> orgIds = operQuery.getOwnOrgIds();
            if (orgIds != null && orgIds.size() > 0) {
                operQuery.setOwnOrgId(null);
            } else {
                operQuery.setOwnOrgIds(null);
            }
        }
        if (operQuery.getOwnOrgIds() != null && operQuery.getOwnOrgIds().size() == 0) {
            operQuery.setOwnOrgIds(null);
        }
        PageUtils<OperatorDTO> pageUtils = new PageUtils<>();
        Page<OperatorDTO> page = pageUtils.getPageByPageParam(operQuery);
        smOperatorMapper.findOperatorPaging(page, operQuery);
        return page;
    }

    @Override
    public SmOperator getByOpLoginName(String opLoginName) throws Exception {
        if (StringUtils.isBlank(opLoginName)) {
            throw new BizException("用户名为空");
        }
        return smOperatorMapper.selectOne(new QueryWrapper<SmOperator>().lambda().eq(SmOperator::getOpLoginName, opLoginName).last("limit 1"));
    }

    @Override
    public OperatorDTO getById(Long opId) throws Exception {
        SmOperator oper = smOperatorMapper.selectById(opId);
        OperatorDTO operatorDTO = new OperatorDTO();
        if (CommonFuntions.isNotEmptyObject(oper)) {
            BeanUtils.copyProperties(oper, operatorDTO);
        }
        return operatorDTO;
    }

    @Override
    public OperatorDTO getFullOperatorInfo(long opId) throws Exception {
        OperatorDTO operatorDTO = new OperatorDTO();
        BeanUtils.copyProperties(getById(opId), operatorDTO);
        // 用户归属组织
        operatorDTO.setRelOpOrgList(relOpOrgService.findRelOpOrgListByOpId(opId));
        // 用户可访问组织
        // TODO 判断是否是归属组织和可访问组织
        operatorDTO.setRelOpAccOrgList(relOpOrgService.findRelOpOrgListByOpId(opId));
        // 用户角色
        operatorDTO.setRelOpRoleList(relOpRoleService.findRelOpRoleListByOpId(opId));
        return operatorDTO;
    }

    @Override
    public long saveOrUpdateOperator(OperatorDTO operDTO) throws Exception {
        // 更新操作员信息
        long opId = operDTO.getOpId() == null ? 0 : operDTO.getOpId().longValue();
        if (opId > 0) {
            updateById(operDTO);
        } else {
            save(operDTO);
            opId = operDTO.getOpId();
        }
        // 更新可访问组织

        this.updateOperatorAccOrgInfo(operDTO);
        // 更新操作员与角色关系信息
        this.updateOperatorRoleInfo(operDTO);
        // 更新操作员与区域关系信息
        this.updateOperatorAreaInfo(operDTO);

        // 更新redis缓存
        String redisToken = stringRedisTemplate.opsForValue().get("LOGIN_TOKEN_" + opId);
        if (StringUtils.isNotBlank(redisToken)) {
            // 操作员信息
            OperatorDTO newOperDTO = this.getById(opId);
            // 用户额外信息
            Map<String, Object> exetInfo = new HashMap<>();

            List<Long> accOrgList = relOpOrgService.findRelOpOrgIdsByOpId(opId);
            List<Long> relRoleList = relOpRoleService.findRelOpRoleIdsByOpId(opId);
            List<Long> accAreaList = relOpAreaService.findRelOpAreaIdsByOpId(opId);

            exetInfo.put("accAreaList", accAreaList);
            exetInfo.put("accOrgList", accOrgList);
            exetInfo.put("relRoleList", relRoleList);
            LoginOperator loginOperator = new LoginOperator();
            BeanUtils.copyProperties(newOperDTO, loginOperator);
            loginOperator.setExetInfo(exetInfo);

            Long expire = stringRedisTemplate.getExpire(redisToken, TimeUnit.SECONDS);
            if (expire == null) {
                expire = 3600L;
            }
            if (expire <= 0) {
                stringRedisTemplate.opsForValue().set(redisToken, JSON.toJSONString(loginOperator));
            } else {
                stringRedisTemplate.opsForValue().set(redisToken, JSON.toJSONString(loginOperator), expire, TimeUnit.SECONDS);
            }
        }

        return opId;
    }

    public List<OrganizationDTO> findOrganizationList() throws Exception {
        List<SmOrganization> orgList = organizationService.list(new QueryWrapper<SmOrganization>()
                .lambda()
                .isNull(SmOrganization::getExpireDate)
                .orderByAsc(SmOrganization::getCreateDate)
        );
        List<OrganizationDTO> orgs = new ArrayList<>();
        for (SmOrganization smOrg : orgList) {
            OrganizationDTO orgDTO = new OrganizationDTO();
            BeanUtils.copyProperties(smOrg, orgDTO);
            orgs.add(orgDTO);
        }
        return orgs;
    }

    /**
     * 更新账户可访问组织信息
     *
     * @param operator
     */
    private void updateOperatorAccOrgInfo(OperatorDTO operator) throws Exception {
        if (operator.getAccOrgIds() == null) {
            operator.setAccOrgIds(operator.getOrgId() + "");
        } else {
            operator.setAccOrgIds(operator.getAccOrgIds() + "," + operator.getOrgId());
        }
        // 本次选择的组织
        String orgIds = operator.getAccOrgIds();
        String[] accOrgIds = orgIds.split(",");
        Set<Long> ids = new HashSet<Long>();
        for (String orgIdStr : accOrgIds) {
            if (StringUtils.isEmpty(orgIdStr)) {
                continue;
            }
            ids.add(Long.valueOf(orgIdStr));
        }
        // 查询所有组织
        List<OrganizationDTO> allOrgs = this.findOrganizationList();
        Map<Integer, Map<String, List<OrganizationDTO>>> levelmap = new HashMap<Integer, Map<String, List<OrganizationDTO>>>();
        Map<Long, OrganizationDTO> allOrgMap = new HashMap<Long, OrganizationDTO>();
        // 按上一级分组
        for (OrganizationDTO orgDTO : allOrgs) {
            if (ids.contains(orgDTO.getOrgId())) {
                // orgDTO.setChecked(true);
                orgDTO.setRelType(RelTypeEnum.REAL);
            }
            String path = orgDTO.getOrgPath();
            int lastIndex = path.lastIndexOf("#");
            String tempPath = path.substring(0, lastIndex);
            int level = tempPath.split("#").length - 1;
            Map<String, List<OrganizationDTO>> lmap = levelmap.get(level);
            if (lmap == null) {
                lmap = new HashMap<String, List<OrganizationDTO>>();
                levelmap.put(level, lmap);
            }
            String key = tempPath.substring(0, tempPath.lastIndexOf("#"));
            if (lmap.get(key) == null) {
                lmap.put(key, new ArrayList<OrganizationDTO>());
            }
            lmap.get(key).add(orgDTO);
            allOrgMap.put(orgDTO.getOrgId(), orgDTO);
        }
        int i = levelmap.size();
        while (i >= 0) {
            Map<String, List<OrganizationDTO>> lmap = levelmap.get(i);
            if (lmap == null || lmap.size() <= 0) {
                i--;
                continue;
            }
            for (String key : lmap.keySet()) {
                List<OrganizationDTO> sublist = lmap.get(key);
                if (sublist == null || sublist.size() <= 0) {
                    continue;
                }
                int realCheckedCount = 0, unrealCheckCount = 0;

                Long parentId = 0L;
                for (OrganizationDTO org : sublist) {
                    if (org.getRelType() == RelTypeEnum.REAL) {
                        realCheckedCount++;
                    }
                    if (org.getRelType() == RelTypeEnum.UNREAL) {
                        unrealCheckCount++;
                    }
                    parentId = org.getParentId();
                }
                OrganizationDTO parent = allOrgMap.get(parentId);
                if (parent == null) {
                    continue;
                }
                if (realCheckedCount == sublist.size()) {
                    parent.setRelType(RelTypeEnum.REAL);
                    // parent.setChecked(true);
                } else if (unrealCheckCount > 0 || realCheckedCount > 0) {
                    parent.setRelType(RelTypeEnum.UNREAL);
                    // parent.setChecked(true);
                } else {
                    parent.setRelType(null);
                }

            }

            i--;
        }

        List<Long> preOrgIds = this.relOpOrgService.findRelOpOrgIdsByOpId(operator.getOpId());
        List<OrganizationDTO> orgs = new ArrayList<OrganizationDTO>();
        List<SmRelOpOrg> updates = new ArrayList<SmRelOpOrg>();
        List<SmRelOpOrg> inserts = new ArrayList<SmRelOpOrg>();
        for (OrganizationDTO orgDTO : allOrgs) {
            if (orgDTO.getRelType() != null) {
                orgs.add(orgDTO);
                if (preOrgIds.contains(orgDTO.getOrgId())) { // 未变更,不处理
                    // update
                    SmRelOpOrg rel = new SmRelOpOrg();
                    rel.setModifyId(operator.getModifyId());
                    rel.setOrgId(orgDTO.getOrgId());
                    rel.setOpId(operator.getOpId());
                    rel.setRelType(orgDTO.getRelType());
                    updates.add(rel);
                    // 剩下的remove
                    preOrgIds.remove(orgDTO.getOrgId());
                } else { // 新增关系
                    SmRelOpOrg rel = new SmRelOpOrg();
                    rel.setOpId(operator.getOpId());
                    rel.setOrgId(orgDTO.getOrgId());
                    rel.setRelType(orgDTO.getRelType());
                    rel.setCreateId(operator.getModifyId());
                    rel.setCreateDate(operator.getModifyDate());
                    inserts.add(rel);
                    // this.relOpOrgService.save(rel);
                }
            }
        }

        for (Long preOrgId : preOrgIds) {
            SmRelOpOrg rel = new SmRelOpOrg();
            rel.setModifyId(operator.getModifyId());
            rel.setOrgId(preOrgId);
            rel.setOpId(operator.getOpId());
            // rel.setRelType(orgDTO.getRelType());
            rel.setExpireDate(new Date());
            updates.add(rel);
        }
        if (updates != null && updates.size() > 0) {
            for (SmRelOpOrg update : updates) {
                relOpOrgService.update(new UpdateWrapper<SmRelOpOrg>()
                        .lambda()
                        .set(CommonFuntions.isNotEmptyObject(update.getExpireDate()), SmRelOpOrg::getExpireDate, update.getExpireDate())
                        .set(CommonFuntions.isNotEmptyObject(update.getModifyId()), SmRelOpOrg::getModifyId, update.getModifyId())
                        .set(CommonFuntions.isNotEmptyObject(update.getModifyDate()), SmRelOpOrg::getModifyDate, update.getModifyDate())
                        .set(CommonFuntions.isNotEmptyObject(update.getRelType()), SmRelOpOrg::getRelType, update.getRelType())
                        .eq(update.getOpId() > 1, SmRelOpOrg::getOpId, update.getOpId())
                        .eq(update.getOrgId() > 1, SmRelOpOrg::getOrgId, update.getOrgId())
                        .isNull(SmRelOpOrg::getExpireDate)
                );
            }
        }
        if (inserts != null && inserts.size() > 0) {
            relOpOrgService.saveBatch(inserts);
        }
    }

    private List<RoleDTO> findAllRoleList() throws Exception {
        List<SmRole> roleList = roleService.list(new QueryWrapper<SmRole>()
                .lambda()
                .isNull(SmRole::getExpireDate)
                .orderByAsc(SmRole::getCreateDate)
        );
        List<RoleDTO> roles = new ArrayList<RoleDTO>();
        for (SmRole smOrg : roleList) {
            RoleDTO orgDTO = new RoleDTO();
            BeanUtils.copyProperties(smOrg, orgDTO);
            roles.add(orgDTO);
        }
        return roles;
    }

    /**
     * 更新账户角色信息
     *
     * @param operator
     */
    private void updateOperatorRoleInfo(OperatorDTO operator) throws Exception {

        String roleIds = "";
        if (CommonFuntions.isNotEmptyObject(operator.getRoleIds())) {
            roleIds = operator.getRoleIds();
        }
        String[] idsarr = roleIds.split(",");
        Set<Long> ids = new HashSet<Long>();
        for (String roleIdStr : idsarr) {
            if (StringUtils.isEmpty(roleIdStr)) {
                continue;
            }
            ids.add(Long.valueOf(roleIdStr));
        }
        // 查询所有组织
        List<RoleDTO> allRoles = this.findAllRoleList();
        Map<Integer, Map<String, List<RoleDTO>>> levelmap = new HashMap<Integer, Map<String, List<RoleDTO>>>();
        Map<Long, RoleDTO> allOrgMap = new HashMap<Long, RoleDTO>();
        // 按上一级分组
        for (RoleDTO roleDTO : allRoles) {
            if (ids.contains(roleDTO.getRoleId())) {
                roleDTO.setRelType(RelTypeEnum.REAL);
            }
            String path = roleDTO.getRolePath();
            int lastIndex = path.lastIndexOf("#");
            String tempPath = path.substring(0, lastIndex);
            int level = tempPath.split("#").length - 1;
            Map<String, List<RoleDTO>> lmap = levelmap.get(level);
            if (lmap == null) {
                lmap = new HashMap<String, List<RoleDTO>>();
                levelmap.put(level, lmap);
            }
            String key = tempPath.substring(0, tempPath.lastIndexOf("#"));
            if (lmap.get(key) == null) {
                lmap.put(key, new ArrayList<RoleDTO>());
            }
            lmap.get(key).add(roleDTO);
            allOrgMap.put(roleDTO.getRoleId(), roleDTO);
        }
        int i = levelmap.size();
        while (i >= 0) {
            Map<String, List<RoleDTO>> lmap = levelmap.get(i);
            if (lmap == null || lmap.size() <= 0) {
                i--;
                continue;
            }
            for (String key : lmap.keySet()) {
                List<RoleDTO> sublist = lmap.get(key);
                if (sublist == null || sublist.size() <= 0) {
                    continue;
                }
                int realCheckedCount = 0, unrealCheckCount = 0;

                Long parentId = 0L;
                for (RoleDTO org : sublist) {
                    if (org.getRelType() == RelTypeEnum.REAL) {
                        realCheckedCount++;
                    }
                    if (org.getRelType() == RelTypeEnum.UNREAL) {
                        unrealCheckCount++;
                    }
                    parentId = org.getParentId();
                }
                RoleDTO parent = allOrgMap.get(parentId);
                if (parent == null) {
                    continue;
                }
                if (realCheckedCount == sublist.size()) {
                    parent.setRelType(RelTypeEnum.REAL);
                    // parent.setChecked(true);
                } else if (unrealCheckCount > 0 || realCheckedCount > 0) {
                    parent.setRelType(RelTypeEnum.UNREAL);
                    // parent.setChecked(true);
                } else {
                    parent.setRelType(null);
                }

            }

            i--;
        }

        List<Long> preRoleIds = this.relOpRoleService.findRelOpRoleIdsByOpId(operator.getOpId());
        // List<Long> preOrgIds =
        // this.relOpOrgService.findRelOpOrgIdsByOpId(operator.getOpId());
        List<RoleDTO> orgs = new ArrayList<RoleDTO>();
        List<SmRelOpRole> updates = new ArrayList<SmRelOpRole>();
        List<SmRelOpRole> inserts = new ArrayList<SmRelOpRole>();
        for (RoleDTO roleDTO : allRoles) {
            if (roleDTO.getRelType() != null) {
                orgs.add(roleDTO);
                if (preRoleIds.contains(roleDTO.getRoleId())) { // 未变更,不处理
                    // update
                    SmRelOpRole rel = new SmRelOpRole();
                    rel.setModifyDate(LocalDateTime.now());
                    rel.setModifyId(operator.getModifyId());
                    rel.setRoleId(roleDTO.getRoleId());
                    rel.setOpId(operator.getOpId());
                    rel.setRelType(roleDTO.getRelType());
                    updates.add(rel);
                    // 剩下的remove
                    preRoleIds.remove(roleDTO.getRoleId());
                } else { // 新增关系
                    SmRelOpRole rel = new SmRelOpRole();
                    rel.setOpId(operator.getOpId());
                    rel.setRoleId(roleDTO.getRoleId());
                    rel.setRelType(roleDTO.getRelType());
                    rel.setCreateId(operator.getModifyId());
                    rel.setCreateDate(operator.getModifyDate());
                    inserts.add(rel);
                    // this.relOpOrgService.save(rel);
                }
            }
        }

        for (Long preRoleId : preRoleIds) {
            SmRelOpRole rel = new SmRelOpRole();
            rel.setModifyDate(LocalDateTime.now());
            rel.setModifyId(operator.getModifyId());
            rel.setRoleId(preRoleId);
            rel.setOpId(operator.getOpId());
            rel.setExpireDate(new Date());
            updates.add(rel);
        }
        if (updates != null && updates.size() > 0) {
            for (SmRelOpRole update : updates) {
                relOpRoleService.update(new UpdateWrapper<SmRelOpRole>()
                        .lambda()
                        .set(CommonFuntions.isNotEmptyObject(update.getExpireDate()), SmRelOpRole::getExpireDate, update.getExpireDate())
                        .set(CommonFuntions.isNotEmptyObject(update.getModifyId()), SmRelOpRole::getModifyId, update.getModifyId())
                        .set(CommonFuntions.isNotEmptyObject(update.getModifyDate()), SmRelOpRole::getModifyDate, update.getModifyDate())
                        .set(CommonFuntions.isNotEmptyObject(update.getRelType()), SmRelOpRole::getRelType, update.getRelType())
                        .eq(SmRelOpRole::getOpId, update.getOpId())
                        .eq(SmRelOpRole::getRoleId, update.getRoleId())
                        .isNull(SmRelOpRole::getExpireDate)
                );
            }
        }
        if (inserts != null && inserts.size() > 0) {
            relOpRoleService.saveBatch(inserts);
        }

        //同步更新缓存中角色与资源关系
        //获取到过期时间为空的关联关系 表示未删除的
        List<SmRelOpRole> realUpdates = updates.stream().filter(u -> CommonFuntions.isEmptyObject(u.getExpireDate())).collect(Collectors.toList());
        if (CommonFuntions.isNotEmptyObject(realUpdates)) {
            inserts.addAll(realUpdates);
        }
        List<Long> allRoleIds = inserts.stream().map(SmRelOpRole::getRoleId).collect(Collectors.toList());
        relRoleInterfaceService.updateLoginRelRoleInterface(operator.getOpId(), allRoleIds);
        //
        //
        // String roleIds = "";
        // if (CommonFuntions.isNotEmptyObject(operator.getRoleIds())) {
        // roleIds = operator.getRoleIds();
        // }
        //
        // // 取得账户关联原有角色
        // List<Long> preRoleIds =
        // this.relOpRoleService.findRelOpRoleIdsByOpId(operator.getOpId());
        // String[] arrRoleIds = roleIds.split(",");
        // for (String roleIdStr : arrRoleIds) {
        // if (StringUtils.isEmpty(roleIdStr)) {
        // continue;
        // }
        // if (preRoleIds.contains(Long.valueOf(roleIdStr))) { // 未变更,不处理
        // preRoleIds.remove(Long.valueOf(roleIdStr));
        // continue;
        // } else { // 新增关系
        // SmRelOpRole rel = new SmRelOpRole();
        // rel.setOpId(operator.getOpId());
        // rel.setRoleId(Long.valueOf(roleIdStr));
        // rel.setCreateOpId(operator.getModifyOpId());
        // rel.setCreateDate(operator.getModifyDate());
        // relOpRoleService.save(rel);
        // }
        // }
        //
        // // 删除取消关联角色
        // for (Long preRoleId : preRoleIds) {
        // relOpRoleService.deleteRelOpRole(operator.getOpId(), preRoleId);
        // }
    }

    @Override
    public List<SmOperator> findList(String searchKey, LoginOperator nowOperDTO) {
        List<SmOperator> list = smOperatorMapper.selectList(new QueryWrapper<SmOperator>()
                .lambda()
                .isNull(SmOperator::getExpireDate)
                .and(CommonFuntions.isNotEmptyObject(searchKey), child -> child.like(SmOperator::getOpLoginName, searchKey).or().like(SmOperator::getOpName, searchKey))
                .orderByDesc(SmOperator::getCreateDate));
        return list;
    }

    private List<AreaDTO> findAllAreaList() throws Exception {
        List<SmArea> areaList = areaService.list(new QueryWrapper<SmArea>()
                .lambda()
                .isNull(SmArea::getExpireDate)
                .ne(SmArea::getAreaType, "4")
                .orderByDesc(SmArea::getCreateDate).select()
        );
        List<AreaDTO> areas = new ArrayList<AreaDTO>();
        for (SmArea area : areaList) {
            AreaDTO areaDTO = new AreaDTO();
            BeanUtils.copyProperties(area, areaDTO);
            areas.add(areaDTO);
        }
        return areas;
    }

    /**
     * 更新账户区域信息
     *
     * @param operator
     */
    private void updateOperatorAreaInfo(OperatorDTO operator) throws Exception {

        String areaIds = "";
        if (CommonFuntions.isNotEmptyObject(operator.getAreaIds())) {
            areaIds = operator.getAreaIds();
        }

        String[] accAreaIds = areaIds.split(",");
        Set<Long> ids = new HashSet<Long>();
        for (String areaIdStr : accAreaIds) {
            if (StringUtils.isEmpty(areaIdStr)) {
                continue;
            }
            ids.add(Long.valueOf(areaIdStr));
        }
        // 查询所有组织
        List<AreaDTO> allAreas = this.findAllAreaList();
        Map<Integer, Map<String, List<AreaDTO>>> levelmap = new HashMap<Integer, Map<String, List<AreaDTO>>>();
        Map<Long, AreaDTO> allOrgMap = new HashMap<Long, AreaDTO>();
        // 按上一级分组
        for (AreaDTO areaDTO : allAreas) {
            if (ids.contains(areaDTO.getAreaId())) {
                areaDTO.setRelType(RelTypeEnum.REAL);
            }
            String path = areaDTO.getAreaPath();
            int lastIndex = path.lastIndexOf("#");
            String tempPath = path.substring(0, lastIndex);
            int level = tempPath.split("#").length - 1;
            Map<String, List<AreaDTO>> lmap = levelmap.get(level);
            if (lmap == null) {
                lmap = new HashMap<String, List<AreaDTO>>();
                levelmap.put(level, lmap);
            }
            String key = tempPath.substring(0, tempPath.lastIndexOf("#"));
            if (lmap.get(key) == null) {
                lmap.put(key, new ArrayList<AreaDTO>());
            }
            lmap.get(key).add(areaDTO);
            allOrgMap.put(areaDTO.getAreaId(), areaDTO);
        }
        int i = levelmap.size();
        while (i >= 0) {
            Map<String, List<AreaDTO>> lmap = levelmap.get(i);
            if (lmap == null || lmap.size() <= 0) {
                i--;
                continue;
            }
            for (String key : lmap.keySet()) {
                List<AreaDTO> sublist = lmap.get(key);
                if (sublist == null || sublist.size() <= 0) {
                    continue;
                }
                int realCheckedCount = 0, unrealCheckCount = 0;

                Long parentId = 0L;
                for (AreaDTO org : sublist) {
                    if (org.getRelType() == RelTypeEnum.REAL) {
                        realCheckedCount++;
                    }
                    if (org.getRelType() == RelTypeEnum.UNREAL) {
                        unrealCheckCount++;
                    }
                    parentId = org.getParentId();
                }
                AreaDTO parent = allOrgMap.get(parentId);
                if (parent == null) {
                    continue;
                }
                if (realCheckedCount == sublist.size()) {
                    parent.setRelType(RelTypeEnum.REAL);
                    // parent.setChecked(true);
                } else if (unrealCheckCount > 0 || realCheckedCount > 0) {
                    parent.setRelType(RelTypeEnum.UNREAL);
                    // parent.setChecked(true);
                } else {
                    parent.setRelType(null);
                }

            }

            i--;
        }

        List<Long> preAreaIds = this.relOpAreaService.findRelOpAreaIdsByOpId(operator.getOpId());
        List<AreaDTO> orgs = new ArrayList<AreaDTO>();
        List<SmRelOpArea> updates = new ArrayList<SmRelOpArea>();
        //正常修改 有realType情况
        List<SmRelOpArea> normalRealTypeUpdates = new ArrayList<SmRelOpArea>();
        //正常修改 realType为空情况
        List<SmRelOpArea> normalEmptyRealTypeUpdates = new ArrayList<SmRelOpArea>();
        List<SmRelOpArea> inserts = new ArrayList<SmRelOpArea>();
        for (AreaDTO areaDTO : allAreas) {
            if (CommonFuntions.isNotEmptyObject(areaDTO.getAreaType())) {
                orgs.add(areaDTO);
                if (preAreaIds.contains(areaDTO.getAreaId())) { // 未变更,不处理
                    // update
                    SmRelOpArea rel = new SmRelOpArea();
                    rel.setAreaId(areaDTO.getAreaId());
                    rel.setOpId(operator.getOpId());
                    rel.setRelType(areaDTO.getRelType());
                    if (CommonFuntions.isNotEmptyObject(areaDTO.getRelType())) {
                        normalRealTypeUpdates.add(rel);
                    } else {
                        normalEmptyRealTypeUpdates.add(rel);
                    }
                    // 剩下的remove
                    preAreaIds.remove(areaDTO.getAreaId());
                } else { // 新增关系
                    SmRelOpArea rel = new SmRelOpArea();
                    rel.setOpId(operator.getOpId());
                    rel.setAreaId(areaDTO.getAreaId());
                    rel.setRelType(areaDTO.getRelType());
//                    rel.setCreateId(operator.getModifyId());
//                    rel.setCreateDate(operator.getModifyDate());
                    inserts.add(rel);
                    // this.relOpOrgService.save(rel);
                }
            }
        }

        for (Long preAreaId : preAreaIds) {
            SmRelOpArea rel = new SmRelOpArea();
            rel.setModifyDate(LocalDateTime.now());
            rel.setModifyId(operator.getModifyId());
            rel.setAreaId(preAreaId);
            rel.setOpId(operator.getOpId());
            rel.setExpireDate(new Date());
            updates.add(rel);
        }
        if (updates != null && updates.size() > 0) {
            Long opId = updates.get(0).getOpId();
            List<Long> areaIdList = updates.stream().map(SmRelOpArea::getAreaId).collect(Collectors.toList());
            //根据opId和需要修改的区域id集合进行单次更新
            relOpAreaService.update(new UpdateWrapper<SmRelOpArea>().lambda().set(SmRelOpArea::getExpireDate, new Date()).eq(SmRelOpArea::getOpId, opId).in(SmRelOpArea::getAreaId, areaIdList));
        }
        if (normalRealTypeUpdates != null && normalRealTypeUpdates.size() > 0) {
            Long opId = normalRealTypeUpdates.get(0).getOpId();
            List<Long> areaIdList = normalRealTypeUpdates.stream().map(SmRelOpArea::getAreaId).collect(Collectors.toList());
            //根据opId和需要修改的区域id集合进行单次更新
            relOpAreaService.update(new UpdateWrapper<SmRelOpArea>().lambda().set(SmRelOpArea::getRelType, RelTypeEnum.REAL.getValue()).set(SmRelOpArea::getModifyId, operator.getModifyId()).set(SmRelOpArea::getModifyDate, LocalDateTime.now()).eq(SmRelOpArea::getOpId, opId).in(SmRelOpArea::getAreaId, areaIdList).ne(SmRelOpArea::getAreaId, 330000));
        }
        if (normalEmptyRealTypeUpdates != null && normalEmptyRealTypeUpdates.size() > 0) {
            Long opId = normalEmptyRealTypeUpdates.get(0).getOpId();
            List<Long> areaIdList = normalEmptyRealTypeUpdates.stream().map(SmRelOpArea::getAreaId).collect(Collectors.toList());
            //根据opId和需要修改的区域id集合进行单次更新
            relOpAreaService.update(new UpdateWrapper<SmRelOpArea>().lambda().set(SmRelOpArea::getRelType, null).set(SmRelOpArea::getModifyId, operator.getModifyId()).set(SmRelOpArea::getModifyDate, LocalDateTime.now()).eq(SmRelOpArea::getOpId, opId).in(SmRelOpArea::getAreaId, areaIdList));
        }
        if (inserts != null && inserts.size() > 0) {
            relOpAreaService.saveBatch(inserts);
        }
    }

    @Override
    public int updateOpStatus(Long opId, String status) throws Exception {
        SmOperator smOperator = new SmOperator();
        smOperator.setOpId(opId);
        smOperator.setStatus(CodeStatusEnum.jsonInit(status));
        return smOperatorMapper.updateById(smOperator);
    }

    @Override
    public void updateOperatorInfo(SmOperator operator, HttpServletRequest request) throws Exception {
        smOperatorMapper.updateById(operator);
        if (request == null) {
            return;
        }
        String token = HttpUtils.getToken(request);
        Long opId = OperatorUtil.getOperatorId(request);
        // 操作员信息
        OperatorDTO newOperDTO = this.getById(opId);
        // 用户额外信息
        Map<String, Object> exetInfo = new HashMap<>();

        List<Long> accOrgList = relOpOrgService.findRelOpOrgIdsByOpId(opId);
        List<Long> relRoleList = relOpRoleService.findRelOpRoleIdsByOpId(opId);
        List<Long> accAreaList = relOpAreaService.findRelOpAreaIdsByOpId(opId);

        exetInfo.put("accAreaList", accAreaList);
        exetInfo.put("accOrgList", accOrgList);
        exetInfo.put("relRoleList", relRoleList);
        LoginOperator loginOperator = new LoginOperator();
        BeanUtils.copyProperties(newOperDTO, loginOperator);
        loginOperator.setExetInfo(exetInfo);
        CurrentOperatorService currentOperatorService = SpringUtils.getCurrentOperatorService();
        currentOperatorService.updateRedisToken(token, loginOperator);
    }

    @Override
    public List<SmOperator> findOperatorByRoleId(long roleId) {
        return smOperatorMapper.findOperatorByRoleId(roleId);
    }

    @Override
    public List<SmOperator> getOperatorByOpName(String opName, List<Long> accOrgList) {

        return smOperatorMapper.selectList(new QueryWrapper<SmOperator>().lambda().like(SmOperator::getOpName, opName)
                .in(SmOperator::getOpId, accOrgList).isNull(SmOperator::getExpireDate).select());
    }

    /*
     * ------------------------CurrentOperatorService-------------------------------
     */

    @Resource
    private JwtConfig jwtConfig;

    private LoginOperator getCurentOperatorById(Long opId) throws Exception {
        OperatorDTO operatorDTO = this.getById(opId);
        LoginOperator loginOperator = new LoginOperator();
        BeanUtils.copyProperties(operatorDTO, loginOperator);
        Map<String, Object> exetInfo = new HashMap<>();

        OrganizationDTO org = organizationService.getOrganizationByOrgId(loginOperator.getOrgId());
        loginOperator.setOrgName(org.getOrgName());
        List<Long> accOrgList = relOpOrgService.findRelOpOrgIdsByOpId(opId);
        List<Long> accAreaList = relOpAreaService.findRelOpAreaIdsByOpId(opId);
        List<Long> relRoleList = relOpRoleService.findRelOpRoleIdsByOpId(opId);

        exetInfo.put("accOrgList", accOrgList);
        exetInfo.put("accAreaList", accAreaList);
        exetInfo.put("relRoleList", relRoleList);
        loginOperator.setExetInfo(exetInfo);
        loginOperator.setRoleIds(relRoleList);
        return loginOperator;
    }

    @Override
    public LoginOperator getCurentOperatorByToken(String token) throws Exception {
        Long opId = this.getOpIdFromToken(token);
        return this.getCurentOperatorById(opId);
    }

    @Override
    public void destroyToken(String token) {
        // TODO Auto-generated method stub
    }

    @Override
    public TokenDTO refreshTokenByNewWay(String refreshToken) {
        return null;
    }

    @Override
    public List<Object> getRoleIdsByToken(String token) {
        return null;
    }

    @Override
    public List<Object> getResourceIdsByToken(String token) {
        return null;
    }

    @Override
    public String genToken(LoginOperator op) throws Exception {
        String token = JwtHelper.createJWT(op, jwtConfig.getClientId(), jwtConfig.getName(),
                jwtConfig.getExpiresSecond(), jwtConfig.getBase64Secret());
        return token;
    }

    @Override
    public TokenDTO genTokenByNewWay(LoginOperator op) {
        return null;
    }

    @Override
    public Long getOpIdFromToken(String token) {
        final Claims claims = JwtHelper.parseJWT(token, jwtConfig.getBase64Secret());
        if (claims == null) {
            return null;
        }
        Long opId = claims.get("opId", Long.class);
        return opId;
    }

    @Override
    public void refToken(String token) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getTokenFromOpId(Long opId) {
        // TODO Auto-generated method stub
        return null;
    }
    /*
     * ----------------------CurrentOperatorService-end-----------------------------
     * -----
     */

    @Override
    public List<Long> findOperatorIdsByOrgIds(List<Long> recOrgs) throws Exception {
        List<SmOperator> smOperators = smOperatorMapper.selectList(new QueryWrapper<SmOperator>()
                .lambda()
                .isNull(SmOperator::getExpireDate)
                .in(CommonFuntions.isNotEmptyObject(recOrgs), SmOperator::getOrgId, recOrgs)
                .select(SmOperator::getOpId)
        );
        if (CommonFuntions.isEmptyObject(smOperators)) {
            return new ArrayList<>();
        }
        return smOperators.stream().map(SmOperator::getOpId).collect(Collectors.toList());
    }

    @Override
    public void updateRedisToken(String token, LoginOperator op) {

    }
}
