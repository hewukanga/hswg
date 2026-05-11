/*
 * RoleServiceImpl.java Created on 2016年10月25日 下午8:36:45
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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.enums.IsValidEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.TreeUtils;
import com.jiumai.base.sm.dto.ResourceDTO;
import com.jiumai.base.sm.dto.RoleDTO;
import com.jiumai.base.sm.entity.*;
import com.jiumai.base.sm.enums.RelTypeEnum;
import com.jiumai.base.sm.enums.ResourceTypeEnum;
import com.jiumai.base.sm.mapper.SmRoleMapper;
import com.jiumai.base.sm.query.RoleQuery;
import com.jiumai.base.sm.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 角色Service
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<SmRoleMapper, SmRole> implements RoleService {

    @Resource
    private RelOrgRoleService relOrgRoleService;

    @Resource
    private RelResRoleService relResRoleService;

    @Resource
    private RelOpRoleService relOpRoleService;

    @Resource
    private ResourceService resourceService;

    @Resource
    private SmRoleMapper smRoleMapper;

    @Resource
    private RelRoleInterfaceService relRoleInterfaceService;

    @Override
    public List<RoleDTO> findRoleList(Long opId) throws Exception {

        List<SmRole> smRoles = smRoleMapper.selectList(new QueryWrapper<SmRole>()
                .lambda()
                .ne(SmRole::getRoleId, 2)
                .isNull(SmRole::getExpireDate)
        );
//		Map<String, Object> map = new HashMap<>();
//		map.put("opId", opId);
//		List<SmRole> smRoles = sqlManager.select("sm.role.findRoleListByOpId", SmRole.class, map);

        List<RoleDTO> roleDTOs = new ArrayList<>();

        for (SmRole smRole : smRoles) {
            RoleDTO roleDTO = new RoleDTO();
            BeanUtils.copyProperties(smRole, roleDTO);
            roleDTOs.add(roleDTO);
        }

        return roleDTOs;
    }

    @Override
    public IPage<RoleDTO> findRolePaging(RoleQuery roleQuery) throws Exception {
        return null;
    }

    @Override
    public RoleDTO findRoleById(Long roleId) throws Exception {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(getById(roleId), roleDTO);
        return roleDTO;

    }

    @Override
    public RoleDTO findRoleByPrentId(Long parentId) throws Exception {
        return smRoleMapper.getRoleByPrentId(parentId);
    }

    /**
     * 查看组织名是否重复
     *
     * @param roleDTO
     * @return
     * @throws Exception
     */
    @Override
    public boolean ifRepeatName(RoleDTO roleDTO) throws Exception {
        boolean b = false;
        if (roleDTO.getRoleId() == null) {
            Long roleCount = smRoleMapper.selectCount(new QueryWrapper<SmRole>()
                    .lambda()
                    .eq(SmRole::getRoleName, roleDTO.getRoleName())
                    .eq(SmRole::getParentId, roleDTO.getParentId())
                    .isNull(SmRole::getExpireDate)
            );
            if (roleCount == 0) {
                b = true;
            }
        } else {
            Integer roleCount = smRoleMapper.ifRepeatNameUpdate(roleDTO);
            if (roleCount == 0) {
                b = true;
            }
        }
        return b;
    }

    @Override
    public void addRole(RoleDTO roleDTO, String resIdListStr, String orgIdListStr) throws Exception {
        if (StringUtils.isEmpty(resIdListStr)) {
            resIdListStr = "";
        }
        if (StringUtils.isEmpty(orgIdListStr)) {
            orgIdListStr = "";
        }
        // Long parentId = roleDTO.getParentId();
        smRoleMapper.insert(roleDTO);
        roleDTO.setRoleId(roleDTO.getRoleId());

        // 如果无父节点则默认为自身
        if (roleDTO.getParentId() == 0) {
            roleDTO.setRolePath("#" + roleDTO.getRoleId() + "#");
            roleDTO.setParentId(roleDTO.getRoleId());
        } else {
            roleDTO.setRolePath(roleDTO.getRolePath() + roleDTO.getRoleId() + "#");
        }
        smRoleMapper.updateById(roleDTO);
        //新增当前操作员与新增角色的关系
        SmRelOpRole opRole = new SmRelOpRole();
        opRole.setCreateId(roleDTO.getCreateId());
        opRole.setOpId(roleDTO.getCreateId());
        opRole.setRelType(RelTypeEnum.REAL);
        opRole.setRoleId(roleDTO.getRoleId());
        relOpRoleService.save(opRole);
//		// 添加当前角色的所属权限
//		String[] resIdList = resIdListStr.split(",");
//		for (int i = 0; i < resIdList.length; i++) {
//			if (StringUtils.isNotBlank(resIdList[i])) {
//				SmRelResRole rrr = new SmRelResRole();
//				rrr.setResourceId(Long.valueOf(resIdList[i]));
//				rrr.setRoleId(roleDTO.getRoleId());
//				rrr.setCreateOpId(roleDTO.getCreateOpId());
//				rrr.setCreateDate(roleDTO.getCreateDate());
//				sqlManager.insertTemplate(SmRelResRole.class, rrr, true);
//			}
//		}
        this.updateResRoleInfo(roleDTO, resIdListStr);

        //处理接口权限管理
        if (CommonFuntions.isNotEmptyObject(roleDTO.getInterfaceConfDTOS())) {
            relRoleInterfaceService.handleRelRoleInterface(roleDTO, roleDTO.getInterfaceConfDTOS());
        }
//		// 添加当前角色的所属组织
//		String[] orgIdList = orgIdListStr.split(",");
//		for (int i = 0; i < orgIdList.length; i++) {
//			if (StringUtils.isNotBlank(orgIdList[i])) {
//				SmRelOrgRole rrr = new SmRelOrgRole();
//				rrr.setOrgId(Long.valueOf(orgIdList[i]));
//				rrr.setRoleId(roleDTO.getRoleId());
//				rrr.setCreateOpId(roleDTO.getCreateOpId());
//				rrr.setCreateDate(roleDTO.getCreateDate());
//				sqlManager.insertTemplate(SmRelOrgRole.class, rrr, true);
//			}
//		}
    }

    @Override
    public void updateRole(RoleDTO role, String resIdListStr, String orgIdListStr) throws Exception {
        this.updateResRoleInfo(role, resIdListStr);
        // this.updateOrgRoleInfo(role, orgIdListStr);
        this.updateRoleBaseInfo(role);

        //处理接口权限管理
        if (CommonFuntions.isNotEmptyObject(role.getInterfaceConfDTOS())) {
            relRoleInterfaceService.handleRelRoleInterface(role, role.getInterfaceConfDTOS());
        }
    }

    private List<ResourceDTO> findAllResourceList() throws Exception {
        List<SmResource> resList = resourceService.list(new QueryWrapper<SmResource>().lambda().isNull(SmResource::getExpireDate));
        List<ResourceDTO> ress = new ArrayList<ResourceDTO>();
        for (SmResource res : resList) {
            ResourceDTO resDTO = new ResourceDTO();
            BeanUtils.copyProperties(res, resDTO);
            ress.add(resDTO);
        }
        return ress;
    }

    /**
     * 更新角色菜单权限信息
     *
     * @param role
     * @throws Exception
     */
    public void updateResRoleInfo(RoleDTO role, String resIdListStr) throws Exception {

        // 本次选择的组织
        String[] resIds = resIdListStr.split(",");
        Set<Long> ids = new HashSet<Long>();
        for (String resIdStr : resIds) {
            if (StringUtils.isEmpty(resIdStr)) {
                continue;
            }
            ids.add(Long.valueOf(resIdStr));
        }
        // 查询所有组织
        List<ResourceDTO> allRess = this.findAllResourceList();
        Map<Integer, Map<String, List<ResourceDTO>>> levelmap = new HashMap<Integer, Map<String, List<ResourceDTO>>>();
        Map<Long, ResourceDTO> allOrgMap = new HashMap<Long, ResourceDTO>();
        // 按上一级分组
        for (ResourceDTO orgDTO : allRess) {
            if (ids.contains(orgDTO.getResourceId())) {
                orgDTO.setRelType(RelTypeEnum.REAL);
            }
            String path = orgDTO.getResourcePath();
            int lastIndex = path.lastIndexOf("#");
            String tempPath = path.substring(0, lastIndex);
            int level = tempPath.split("#").length - 1;
            Map<String, List<ResourceDTO>> lmap = levelmap.get(level);
            if (lmap == null) {
                lmap = new HashMap<String, List<ResourceDTO>>();
                levelmap.put(level, lmap);
            }
            String key = tempPath.substring(0, tempPath.lastIndexOf("#"));
            if (lmap.get(key) == null) {
                lmap.put(key, new ArrayList<ResourceDTO>());
            }
            lmap.get(key).add(orgDTO);
            allOrgMap.put(orgDTO.getResourceId(), orgDTO);
        }
        int i = levelmap.size();
        while (i >= 0) {
            Map<String, List<ResourceDTO>> lmap = levelmap.get(i);
            if (lmap == null || lmap.size() <= 0) {
                i--;
                continue;
            }
            for (String key : lmap.keySet()) {
                List<ResourceDTO> sublist = lmap.get(key);
                if (sublist == null || sublist.size() <= 0) {
                    continue;
                }
                int realCheckedCount = 0, unrealCheckCount = 0;

                Long parentId = 0L;
                for (ResourceDTO org : sublist) {
                    if (org.getRelType() == RelTypeEnum.REAL) {
                        realCheckedCount++;
                    }
                    if (org.getRelType() == RelTypeEnum.UNREAL) {
                        unrealCheckCount++;
                    }
                    parentId = org.getParentId();
                }
                ResourceDTO parent = allOrgMap.get(parentId);
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

        // List<Long> preOrgIds = this.relOpOrgService.findRelOpOrgIdsByOpId(operator.getOpId());
        List<Long> preResIds = relResRoleService.findResIdsByRoleId(role.getRoleId());
        List<ResourceDTO> orgs = new ArrayList<ResourceDTO>();
        List<SmRelResRole> updates = new ArrayList<SmRelResRole>();
        List<SmRelResRole> inserts = new ArrayList<SmRelResRole>();
        for (ResourceDTO resDTO : allRess) {
            if (resDTO.getRelType() != null) {
                orgs.add(resDTO);
                if (preResIds.contains(resDTO.getResourceId())) {
                    // update
                    SmRelResRole rel = new SmRelResRole();
                    rel.setModifyDate(LocalDateTime.now());
                    rel.setModifyId(role.getModifyId());
                    rel.setRoleId(role.getRoleId());
                    rel.setResourceId(resDTO.getResourceId());
                    rel.setRelType(resDTO.getRelType());
                    updates.add(rel);
                    // 剩下的remove
                    preResIds.remove(resDTO.getResourceId());
                } else { // 新增关系
                    SmRelResRole rel = new SmRelResRole();
                    rel.setRoleId(role.getRoleId());
                    rel.setResourceId(resDTO.getResourceId());
                    rel.setCreateId(role.getCreateId());
                    rel.setCreateDate(role.getCreateDate());
                    rel.setRelType(resDTO.getRelType());
                    inserts.add(rel);
                }
            }
        }

        for (Long preResId : preResIds) {
            SmRelResRole rel = new SmRelResRole();
            rel.setModifyDate(LocalDateTime.now());
            rel.setModifyId(role.getModifyId());
            rel.setRoleId(role.getRoleId());
            rel.setResourceId(preResId);
            rel.setExpireDate(new Date());
            updates.add(rel);
        }
        if (updates != null && updates.size() > 0) {
            for (SmRelResRole update : updates) {
                relResRoleService.update(new UpdateWrapper<SmRelResRole>()
                        .lambda()
                        .set(CommonFuntions.isNotEmptyObject(update.getExpireDate()), SmRelResRole::getExpireDate, update.getExpireDate())
                        .set(CommonFuntions.isNotEmptyObject(update.getModifyId()), SmRelResRole::getModifyId, update.getModifyId())
                        .set(CommonFuntions.isNotEmptyObject(update.getModifyDate()), SmRelResRole::getModifyDate, update.getModifyDate())
                        .set(CommonFuntions.isNotEmptyObject(update.getRelType()), SmRelResRole::getRelType, update.getRelType())
                        .eq(SmRelResRole::getRoleId, update.getRoleId())
                        .eq(SmRelResRole::getResourceId, update.getResourceId())
                );
            }
        }
        if (inserts != null && inserts.size() > 0) {
            relResRoleService.saveBatch(inserts);
        }


//		if (StringUtils.isEmpty(resIdListStr)) {
//			resIdListStr = "";
//		}
//		// 取得角色原有权限
//		List<String> resIds = new ArrayList<String>();
//		List<String> preResIds = relResRoleService.findResIdsByRoleId(role.getRoleId());
//		if (null != preResIds && preResIds.size() > 0) {
//			for (String resId : preResIds) {
//				resIds.add(resId);
//			}
//		}
//		String[] arrResIds = resIdListStr.split(",");
//		for (String resIdStr : arrResIds) {
//			if (StringUtils.isEmpty(resIdStr) || StringUtils.equals("undefined", resIdStr)) {
//				continue;
//			}
//			Long resId = Long.parseLong(resIdStr);
//			if (resIds.contains(resIdStr)) { // 未变更,不处理	
//				resIds.remove(resIdStr);
//				continue;
//			} else {
//				//获取已经存在但是过期的菜单权限
//				List<String> exResIds = new ArrayList<String>();
//				List<String> expireResIds=relResRoleService.findExpieResIdsByRoleId(role.getRoleId());
//				if (null != expireResIds && expireResIds.size() > 0) {
//					for (String esResId : expireResIds) {
//						exResIds.add(esResId);
//					}
//				}
//				if(exResIds.contains(resIdStr)) {//已存在，但过期 删除过期时间
//					SmRelResRole rrr = new SmRelResRole();
//					rrr.setResourceId(resId);
//					rrr.setRoleId(role.getRoleId());
//					rrr.setModifyOpId(role.getModifyOpId());
//					rrr.setModifyDate(new Date());
//					rrr.setExpireDate(null);
//					relResRoleService.updateByResIdByRoleId(rrr);
//				}else {
//					SmRelResRole rrr = new SmRelResRole();
//					rrr.setResourceId(resId);
//					rrr.setRoleId(role.getRoleId());
//					rrr.setCreateOpId(role.getModifyOpId());
//					rrr.setCreateDate(role.getModifyDate());
//					sqlManager.insertTemplate(SmRelResRole.class, rrr, true);
//				}
//				
//			}
//		}
//
//		// 删除取消关联权限
//		for (String resId : resIds) {
//			SmRelResRole relResRole = this.relResRoleService.getRelResRoleByRoleIdAndResId(role.getRoleId(),
//					Long.valueOf(resId));
//			if (relResRole == null) {
//				continue;
//			}
//			relResRole.setModifyOpId(role.getModifyOpId());
//				this.relResRoleService.deleteRelResRole(relResRole);
//			
//		}
        // TODO 保存历史删除历史记录
        // RelResRoleHis relResRoleHis = (RelResRoleHis)
        // EntityBeanUtils.getHisEntity(relResRole);
        // relResRoleHis.setExpireOpId(role.getModifyOpId());
        // relResRoleHis.setExpireTime(role.getModifyTime());
        // this.relResRoleHisDao.save(relResRoleHis);
        // }
    }


    /**
     * 添加历史记录
     *
     * @throws Exception
     */
    public void updateRoleBaseInfo(RoleDTO roleDTO) throws Exception {
        /*
         * SmRole role = sqlManager.single(SmRole.class,roleDTO.getRoleId()); RoleDTO
         * dbRole =new RoleDTO(); BeanUtils.copyProperties(role, dbRole); // 数据库数据不存在 if
         * (dbRole == null) { return; }
         *
         * // 更新当前表 dbRole.setRoleName(roleDTO.getRoleName());
         * dbRole.setRoleDesc(roleDTO.getRoleDesc());
         */
        smRoleMapper.updateById(roleDTO);
        // TODO 保存历史记录
        // if (isRoleBaseInfoChange(role, dbRole)) {
        // 保存历史
        // RoleHisDO roleHis = (RoleHisDO)
        // EntityBeanUtils.getHisEntity(dbRole);
        // roleHis.setExpireOpId(role.getModifyOpId());
        // roleHis.setExpireTime(role.getModifyTime());
        // this.roleHisDao.save(roleHis);
        // 更新当前表
        // this.roleDao.updateRole(role);
        // }
    }

    @Override
    public List<Long> findRelResRoleByRoleId(Long roleId) throws Exception {
        List<SmRelResRole> rrrList = this.relResRoleService.findRelResRoleByRoleId(roleId);
        List<Long> resIdList = new ArrayList<Long>();
        if (rrrList != null && rrrList.size() != 0) {
            for (int i = 0; i < rrrList.size(); i++) {
                SmRelResRole rrr = rrrList.get(i);
                resIdList.add(rrr.getResourceId());
            }
        }
        return resIdList;
    }

    @Override
    public List<Long> findRelOrgRoleByRoleId(Long roleId) throws Exception {
        List<SmRelOrgRole> orgList = this.relOrgRoleService.findRelOrgRoleByRoleId(roleId);
        List<Long> orgIdList = new ArrayList<Long>();
        if (orgList != null && orgList.size() != 0) {
            for (int i = 0; i < orgList.size(); i++) {
                SmRelOrgRole rrr = orgList.get(i);
                orgIdList.add(rrr.getOrgId());
            }
        }
        return orgIdList;
    }

    @Override
    public void delRoleById(Long roleId, IsValidEnum invalid, Long opId) throws Exception {

        Long parentCount = smRoleMapper.selectCount(new QueryWrapper<SmRole>()
                .lambda()
                .eq(SmRole::getParentId, roleId)
                .isNull(SmRole::getExpireDate)
        );
        if (parentCount > 0) {
            throw new Exception("该角色下有子角色，不能删除！");
        }
        SmRole role = new SmRole();
        role.setRoleId(roleId);
        role.setExpireDate(new Date());
        role.setModifyId(opId);
        role.setModifyDate(LocalDateTime.now());
        role.setStatus(invalid);
        int index = smRoleMapper.updateById(role);
        if (index <= 0) {
            throw new Exception("删除失败！");
        }
    }

    @Override
    public List<RoleDTO> findRoleTree(Long opId) throws Exception {
        List<RoleDTO> roleList = this.findRoleList(opId);

        List<RoleDTO> rootTrees = TreeUtils.parseListToTree(roleList, "roleId", "parentId", "childrens");

        return rootTrees;
    }
}
