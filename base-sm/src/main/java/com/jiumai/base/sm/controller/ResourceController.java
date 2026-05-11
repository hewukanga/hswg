/*
 * ResourceController.java Created on 2016年10月27日 上午9:53:50
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
package com.jiumai.base.sm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.TreeUtils;
import com.jiumai.base.sm.dto.ResourceDTO;
import com.jiumai.base.sm.entity.SmRelOpRole;
import com.jiumai.base.sm.entity.SmRelResRole;
import com.jiumai.base.sm.entity.SmResource;
import com.jiumai.base.sm.enums.RelTypeEnum;
import com.jiumai.base.sm.query.ResourceQuery;
import com.jiumai.base.sm.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 功能菜单控制器
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
@Api(tags = {"功能菜单权限类"})
@RestController
@RequestMapping("d-admin/resource")
public class ResourceController {

    private static SysLog log = SysLogFactory.getLogger(ResourceController.class);

    @Resource
    private ResourceService resourceService;


    @GetMapping("findResourceTree")
    @ApiOperation(value = "获取功能菜单树")
    public ResultDTO<List<ResourceDTO>> findResourceTree() throws Exception {
        ResultDTO<List<ResourceDTO>> resultDTO = new ResultDTO<>();
        List<ResourceDTO> resourceList = resourceService.findResourceList();
        List<ResourceDTO> resourceTree = TreeUtils.parseListToTree(resourceList, "resourceId", "parentId", "childrens");
        return resultDTO.set(ResultCodeEnum.SUCCESS, null, resourceTree);
    }


    @GetMapping("findResourceTreeByOpId")
    @ApiOperation(value = "获取操作员权限功能菜单树")
    public ResultDTO<List<ResourceDTO>> findResourceTreeByOpId(HttpServletRequest request) throws Exception {
        ResultDTO<List<ResourceDTO>> resultDTO = new ResultDTO<>();

        LoginOperator oper = OperatorUtil.getOperator(request);
        List<ResourceDTO> resourceList = resourceService.findResourceDTOByOpId(oper.getOpId());
        List<ResourceDTO> resourceTree = TreeUtils.parseListToTree(resourceList, "resourceId", "parentId", "childrens");
        return resultDTO.set(ResultCodeEnum.SUCCESS, null, resourceTree);

    }

    @GetMapping("getResourceById")
    @ApiOperation(value = "查询功能菜单首页信息")
    @ApiImplicitParam(name = "resourceId", value = "菜单Id", paramType = "query", dataType = "Long", example = "0", required = true, dataTypeClass = Long.class)
    public ResultDTO<ResourceDTO> getResourceById(Long resourceId) throws Exception {
        ResultDTO<ResourceDTO> resultDTO = new ResultDTO<>();
        ResourceDTO resourceDTO = resourceService.getByResourceId(resourceId);

        return resultDTO.set(ResultCodeEnum.SUCCESS, null, resourceDTO);
    }

    @PostMapping("findResourcePaging")
    @ApiOperation(value = "查询功能菜单分页列表")
    public ResultDTO<IPage<ResourceDTO>> findResourcePaging(@RequestBody ResourceQuery resourceQuery, HttpServletRequest request) throws Exception {
        resourceQuery.setOpId(OperatorUtil.getOperatorId(request));
        IPage<ResourceDTO> resourcePaging = resourceService.findResourcePaging(resourceQuery);

        return new ResultDTO<IPage<ResourceDTO>>().set(ResultCodeEnum.SUCCESS, null, resourcePaging);
    }

    @PostMapping("saveResource")
    @ApiOperation(value = "保存或更新菜单")
    @OpLog(title = "保存或更新菜单", businessType = BusinessTypeEnum.INSERT_OR_UPDATE)
    public ResultDTO<String> saveResource(HttpServletRequest request, @RequestBody SmResource resource)
            throws Exception {
        ResultDTO<String> result = new ResultDTO<>();
        // 当前登录人
        LoginOperator oper = OperatorUtil.getOperator(request);
        if (oper == null) {
            return result.set(ResultCodeEnum.ERROR_UNKNOW, "请先登陆！");
        }
        if (CommonFuntions.isEmptyObject(resource.getResourceTitle())) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "功能名称为空！");
        }
        if (resource.getResourceId() == null && resource.getParentId() == null) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "父标识为空！");
        }
        if (oper.getOpId().longValue() > 1) {
            List<SmRelOpRole> opRoles = resourceService.getRelOpRoleByOpId(oper.getOpId());
            Boolean flag = false;
            for (SmRelOpRole rel : opRoles) {
                SmRelResRole resRole = resourceService.getRelResRoleBy(rel.getRoleId(), resource.getParentId());
                if (CommonFuntions.isNotEmptyObject(resRole) && RelTypeEnum.REAL.equals(resRole.getRelType())) {
                    flag = true;
                }
            }
            if (!flag) {
                return result.set(ResultCodeEnum.ERROR_NOT_ALLOW, "权限不足");
            }
        }
        // 功能 名称是否存在
        if (resourceService.checkResourceTitle(resource) > 0) {
            return result.set(ResultCodeEnum.ERROR_HANDLE, "功能名称已存在！");
        }
        // 功能路径是否存在
        if (CommonFuntions.isNotEmptyObject(resource.getMenuUrl()) && resourceService.checkResourceUrl(resource) > 0) {
            return result.set(ResultCodeEnum.ERROR_HANDLE, "功能路径已存在！");
        }

        if (resource.getResourceId() == null) {
            resource.setCreateId(oper.getOpId());
            try {
                resourceService.saveResource(resource);
            } catch (Exception e) {
                log.error("添加功能菜单失败{}", resource);
                return result.set(ResultCodeEnum.ERROR_HANDLE, e.getMessage());
            }

        } else {
            resource.setModifyId(oper.getOpId());
            try {
                resourceService.updateResource(resource);
            } catch (Exception e) {
                log.error("更新功能菜单失败{}", resource.getResourceId());
                return result.set(ResultCodeEnum.ERROR_HANDLE, e.getMessage());
            }

        }
        return result.set(ResultCodeEnum.SUCCESS, "操作成功！", resource.getResourceId() + "");
    }


    @PostMapping("deleteResource")
    @ApiOperation(value = "删除菜单")
    @OpLog(title = "删除菜单", businessType = BusinessTypeEnum.DELETE)
    public ResultDTO<String> deleteResource(HttpServletRequest request, @RequestBody SmResource resource) throws Exception {
        Long resourceId = resource.getResourceId();
        ResultDTO<String> result = new ResultDTO<>();
        LoginOperator oper = OperatorUtil.getOperator(request);
        if (CommonFuntions.isEmptyObject(resourceId)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "功能菜单ID不能为空");
        }
        if (oper.getOpId().longValue() > 1) {
            //判断权限
            List<SmRelOpRole> opRoles = resourceService.getRelOpRoleByOpId(oper.getOpId());
            Boolean flag = false;
            for (SmRelOpRole rel : opRoles) {
                SmRelResRole resRole = resourceService.getRelResRoleBy(rel.getRoleId(), resourceService.getByResourceId(resourceId).getParentId());
                if (CommonFuntions.isNotEmptyObject(resRole) && RelTypeEnum.REAL.equals(resRole.getRelType())) {
                    flag = true;
                }
            }
            if (!flag) {
                return result.set(ResultCodeEnum.ERROR_NOT_ALLOW, "权限不足");
            }
        }
        try {
            resourceService.deleteResource(resourceId, oper.getOpId());
        } catch (Exception e) {
            log.error("删除功能菜单出错{}", resourceId);
            return result.set(ResultCodeEnum.ERROR_HANDLE, e.getMessage());
        }
        return result.set(ResultCodeEnum.SUCCESS, "");
    }


    @PostMapping("updateSort")
    @ApiOperation(value = "更新菜单顺序")
    @ApiImplicitParam(name = "menuSortlist", value = "菜单排序集", paramType = "query", dataType = "List", dataTypeClass = List.class, example = "[{\"resourceId\":\"1\",\"menuSort\":\"2\"},{\"resourceId\":\"2\",\"menuSort\":\"3\"}]", required = true)
    @OpLog(title = "更新菜单顺序", businessType = BusinessTypeEnum.UPDATE)
    public ResultDTO<String> updateSort(HttpServletRequest request, @RequestBody List<ResourceDTO> menuSortlist)
            throws Exception {
        ResultDTO<String> result = new ResultDTO<>();
        try {
            resourceService.updateSort(menuSortlist);
        } catch (Exception e) {
            log.error("更新菜单顺序出错", e);
            return result.set(ResultCodeEnum.ERROR_HANDLE, e.getMessage());
        }
        return result.set(ResultCodeEnum.SUCCESS, "");
    }


    @PostMapping("getBtnByOpId")
    @ApiOperation(value = "查询菜单下所有功能按钮")
    public ResultDTO<List<ResourceDTO>> getBtnByOpId(HttpServletRequest request, @RequestBody Long resourceId)
            throws Exception {
        ResultDTO<List<ResourceDTO>> result = new ResultDTO<>();
        if (resourceId == null || resourceId == 0L) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "缺少菜单ID");
        }
        Long opId = OperatorUtil.getOperatorId(request);
        List<ResourceDTO> btnByOpId = resourceService.getBtnByOpId(resourceId, opId);
        return result.set(ResultCodeEnum.SUCCESS, null, btnByOpId);
    }
}
