/*
 * ResourceServiceImpl.java Created on 2016年10月26日 下午9:52:03
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.utils.PageUtils;
import com.jiumai.base.sm.dto.ResourceDTO;
import com.jiumai.base.sm.entity.SmRelOpRole;
import com.jiumai.base.sm.entity.SmRelResRole;
import com.jiumai.base.sm.entity.SmResource;
import com.jiumai.base.sm.enums.RelTypeEnum;
import com.jiumai.base.sm.enums.ResourceTypeEnum;
import com.jiumai.base.sm.mapper.SmResourceMapper;
import com.jiumai.base.sm.query.ResourceQuery;
import com.jiumai.base.sm.service.RelOpRoleService;
import com.jiumai.base.sm.service.RelResRoleService;
import com.jiumai.base.sm.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<SmResourceMapper, SmResource> implements ResourceService {

    @Resource
    private SmResourceMapper smResourceMapper;

    @Resource
    private RelOpRoleService relOpRoleService;

    @Resource
    private RelResRoleService relResRoleService;

    @Override
    public Long saveResource(SmResource resource) throws Exception {
        SmResource resource2 = smResourceMapper.selectOne(new QueryWrapper<SmResource>()
                .lambda()
                .eq(SmResource::getResourceId, resource.getParentId())
        );

        smResourceMapper.insert(resource);
        if (resource.getResourceId() <= 0) {
            throw new RuntimeException("添加失败");
        }
        resource.setResourcePath(resource2.getResourcePath() + resource.getResourceId() + "#");

        smResourceMapper.updateById(resource);

        return resource.getResourceId();

    }

    @Override
    public List<ResourceDTO> findResourceList() throws Exception {
        List<SmResource> smrs = smResourceMapper.selectList(new QueryWrapper<SmResource>()
                .lambda()
                .isNull(SmResource::getExpireDate)
                .orderByAsc(SmResource::getParentId).orderByAsc(SmResource::getMenuSort)
        );

        List<ResourceDTO> ress = new ArrayList<>();
        for (SmResource smResource : smrs) {
            ResourceDTO res = new ResourceDTO();
            BeanUtils.copyProperties(smResource, res);
            ress.add(res);
        }
        return ress;
    }

    @Override
    public ResourceDTO getByResourceId(Long resourceId) throws Exception {
        SmResource res = smResourceMapper.selectOne(new QueryWrapper<SmResource>()
                .lambda()
                .eq(SmResource::getResourceId, resourceId)
                .last("limit 1")
        );
        ResourceDTO resDTO = new ResourceDTO();
        BeanUtils.copyProperties(res, resDTO);
        return resDTO;
    }

    @Override
    public IPage<ResourceDTO> findResourcePaging(ResourceQuery pageParam) throws Exception {
        PageUtils<ResourceDTO> pageUtils = new PageUtils<>();
        Page<ResourceDTO> page = pageUtils.getPageByPageParam(pageParam);

        smResourceMapper.findResourcePaging(page, pageParam);
        return page;
    }

    @Override
    public Long checkResourceTitle(SmResource resource) throws Exception {
        if (resource.getResourceType() == ResourceTypeEnum.RESOURCE_BTN) {
            return 0L;
        }

        List<SmResource> list = smResourceMapper.selectList(new QueryWrapper<SmResource>()
                .lambda()
                .isNull(SmResource::getExpireDate)
                .eq(SmResource::getResourceTitle, resource.getResourceTitle())
        );
        if (list == null || list.size() <= 0) {
            return 0L;
        }
        SmResource res = list.get(0);
        if ((resource.getResourceId() == null || resource.getResourceId().longValue() == 0)) {
            return 1L;
        }
        if (res.getResourceId().longValue() == resource.getResourceId().longValue()) {
            return 0L;
        }
        return 1L;
    }

    @Override
    public Long checkResourceUrl(SmResource resource) throws Exception {
        if (resource.getResourceType() == ResourceTypeEnum.RESOURCE_BTN) {
            return 0L;
        }

        List<SmResource> list = smResourceMapper.selectList(new QueryWrapper<SmResource>()
                .lambda()
                .isNull(SmResource::getExpireDate)
                .eq(SmResource::getMenuUrl, resource.getMenuUrl())
        );
        if (list == null || list.size() <= 0) {
            return 0L;
        }
        SmResource res = list.get(0);
        if ((resource.getResourceId() == null || resource.getResourceId().longValue() == 0)) {
            return 1L;
        }
        if (res.getResourceId().longValue() == resource.getResourceId().longValue()) {
            return 0L;
        }
        return 1L;
    }

    @Override
    public Integer updateResource(SmResource resource) throws Exception {
        return smResourceMapper.updateById(resource);
    }

    @Override
    public Integer deleteResource(Long resourceId, Long opId) throws Exception {
        Long parentCount = smResourceMapper.selectCount(new QueryWrapper<SmResource>()
                .lambda()
                .eq(SmResource::getParentId, resourceId)
                .isNull(SmResource::getExpireDate)
        );
        if (parentCount > 0) {
            throw new Exception("该功能下有子功能，不能删除！");
        }

        SmResource resource = new SmResource();
        resource.setResourceId(resourceId);
        resource.setModifyId(opId);
        resource.setExpireDate(new Date());
        int index = smResourceMapper.updateById(resource);
        if (index <= 0) {
            throw new Exception("删除失败！");
        }
        return index;
    }

    @Override
    public void updateSort(List<ResourceDTO> resourceList) throws Exception {
        for (ResourceDTO resource : resourceList) {
            SmResource smr = new SmResource();
            smr.setResourceId(resource.getResourceId());
            smr.setMenuSort(resource.getMenuSort());
            smResourceMapper.updateById(smr);
        }
    }

    @Override
    public List<ResourceDTO> findResourceDTOByOpId(long opId) throws Exception {
        return smResourceMapper.getByOpId(opId);
    }

    @Override
    public List<ResourceDTO> getOperatorMenusTree(Long opId) throws Exception {
        List<ResourceDTO> resources = smResourceMapper.getMenuByOpId(opId);
        // 补充所有上级节点
        // resources = setParents(resources);
        List<ResourceDTO> rootTrees = new ArrayList<ResourceDTO>();
        for (ResourceDTO tree : resources) {
            if (tree.getParentId().longValue() == tree.getResourceId().longValue()) {
                rootTrees.add(tree);
            }
            for (ResourceDTO t : resources) {
                if (t.getParentId().longValue() == t.getResourceId().longValue()) {
                    continue;
                }
                if (t.getParentId().longValue() == tree.getResourceId().longValue()) {
                    if (tree.getChildrens() == null) {
                        List<ResourceDTO> myChildrens = new ArrayList<ResourceDTO>();
                        myChildrens.add(t);
                        tree.setChildrens(myChildrens);
                    } else {
                        tree.getChildrens().add(t);
                    }
                }
            }
        }

        return rootTrees;
    }

    @Override
    public List<ResourceDTO> findOperatorBtnList(Long opId) throws Exception {
        List<ResourceDTO> operatorBtn = smResourceMapper.findOperatorBtn(opId);
        for (ResourceDTO resourceDTO : operatorBtn) {
            resourceDTO.setBtnPermission(true);
        }
        return operatorBtn;
    }

    @Override
    public List<SmRelOpRole> getRelOpRoleByOpId(Long opId) {
        return relOpRoleService.list(new QueryWrapper<SmRelOpRole>()
                .lambda()
                .eq(SmRelOpRole::getOpId, opId)
                .eq(SmRelOpRole::getRelType, RelTypeEnum.REAL.getValue())
                .isNull(SmRelOpRole::getExpireDate)
        );
    }

    @Override
    public SmRelResRole getRelResRoleBy(Long roleId, Long resId) {
        return relResRoleService.getOne(new QueryWrapper<SmRelResRole>()
                .lambda()
                .eq(SmRelResRole::getRoleId, roleId)
                .eq(SmRelResRole::getResourceId, resId)
                .isNull(SmRelResRole::getExpireDate)
        );
    }

    @Override
    public List<ResourceDTO> getBtnByOpId(Long resId, Long opId) throws Exception {
        if (opId.longValue() == 1L) {
            List<ResourceDTO> btns = smResourceMapper.getBtnByOpId(resId, opId);
            for (ResourceDTO resourceDTO : btns) {
                resourceDTO.setBtnPermission(true);
            }
            return btns;
        } else {
            List<ResourceDTO> userBtns = smResourceMapper.getBtnByOpId(resId, opId);
            List<ResourceDTO> resources = smResourceMapper.getBtnByOpId(resId, 1);
            boolean isContains = false;
            for (ResourceDTO resource : resources) {
                for (ResourceDTO btn : userBtns) {
                    if (btn.getResourceId().longValue() == resource.getResourceId().longValue()) {
                        isContains = true;
                        break;
                    }
                }
                resource.setBtnPermission(isContains);
                isContains = false;
            }
            return resources;
        }
    }

}
