/*
 * OperatorController.java Created on 2016年10月24日 下午4:22:56
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
import com.jiumai.base.sm.dto.OrganizationDTO;
import com.jiumai.base.sm.entity.SmRelOpOrg;
import com.jiumai.base.sm.enums.RelTypeEnum;
import com.jiumai.base.sm.query.OrganizationQuery;
import com.jiumai.base.sm.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 组织控制器
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
@Api(tags = {"组织类"})
@RestController
@RequestMapping("d-admin/org")
public class OrganizationController {

    private static SysLog log = SysLogFactory.getLogger(OrganizationController.class);

    @Resource
    private OrganizationService organizationService;


//	@ResponseBody
//	@GetMapping("searchPayeeOrgs")
//	@ApiOperation(value = "获取所有公司组织")
//	@ApiImplicitParam(paramType = "query", dataType = "boolean", name = "searchKey", value = "组织名称关键字", required = true)
//	public List<SmOrganization> searchPayeeOrgs(String searchKey) throws Exception {
//		List<SmOrganization> orgs = this.organizationService.searchPayeeOrgs(searchKey);
//		return orgs;
//	}


    @GetMapping("findOrganizationTreeByOpId")
    @ApiOperation(value = "获取操作员可访问组织树")
    public ResultDTO<List<OrganizationDTO>> findOrganizationTreeByOpId(HttpServletRequest request) throws Exception {
        ResultDTO<List<OrganizationDTO>> resultDTO = new ResultDTO<>();
        LoginOperator oper = OperatorUtil.getOperator(request);
        List<OrganizationDTO> orgList = organizationService.findOrganizationTreeByOpId(oper.getOpId());

        return resultDTO.set(ResultCodeEnum.SUCCESS, null, orgList);
    }


    // @GetMapping("findDisabledOrgTreeByOpId")
    // @ApiOperation(value = "获取操作员可访问组织树")
    // public List<OrganizationDTO> findDisabledOrgTreeByOpId(HttpServletRequest
    // request) throws Exception {
    // LoginOperator oper = OperatorUtil.getOperator(request);
    // List<OrganizationDTO> orgList =
    // organizationService.findDisabedOrgTreeByOpId(oper.getOpId());
    // return orgList;
    // }

    /**
     * 根据组织ID获取信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping("getOrganizationByOrgId")
    @ApiOperation(value = "根据组织ID获取信息")
    @ApiImplicitParam(paramType = "query", dataType = "Long", dataTypeClass = Long.class, name = "orgId", value = "组织id", example = "0", required = true)
    public ResultDTO<OrganizationDTO> getOrganizationByOrgId(Long orgId) throws Exception {
        ResultDTO<OrganizationDTO> resultDTO = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(orgId)) {
            return resultDTO.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "缺少组织ID");
        }
        OrganizationDTO organizationByOrgId = organizationService.getOrganizationByOrgId(orgId);
        return resultDTO.set(ResultCodeEnum.SUCCESS, null, organizationByOrgId);
    }

    @PostMapping("saveOrg")
    @ApiOperation(value = "保存或更新组织")
    @OpLog(title = "保存或更新组织", businessType = BusinessTypeEnum.INSERT_OR_UPDATE)
    public ResultDTO<String> saveOrg(HttpServletRequest request, @RequestBody OrganizationDTO organizationDTO)
            throws Exception {
        ResultDTO<String> result = new ResultDTO<>();
        // 当前登录人
        LoginOperator oper = OperatorUtil.getOperator(request);
        if (oper == null) {
            return result.set(ResultCodeEnum.ERROR_UNKNOW, "请先登陆！");
        }
        if (CommonFuntions.isEmptyObject(organizationDTO.getOrgName())) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "组织名称为空");
        }
        if (organizationDTO.getOrgId() == null && organizationDTO.getParentId() == null) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "父标识为空");
        }
        if (oper.getOpId().longValue() > 1) {
            SmRelOpOrg rel = organizationService.getRelOpOrgByOpOrgId(oper.getOpId(), organizationDTO.getParentId());
            if (CommonFuntions.isEmptyObject(rel) || !RelTypeEnum.REAL.equals(rel.getRelType())) {
                return result.set(ResultCodeEnum.ERROR_NOT_ALLOW, "权限不足");
            }
        }
        // 组织名称是否已存在
        if (organizationService.checkOrgName(organizationDTO) > 0) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "组织名称已存在！");
        }

        if (organizationDTO.getOrgId() == null) {
            organizationDTO.setCreateId(oper.getOpId());
            organizationService.save(organizationDTO);
        } else {
            organizationDTO.setModifyId(oper.getOpId());
            organizationService.updateOrganization(organizationDTO);
        }
        return result.set(ResultCodeEnum.SUCCESS, "", organizationDTO.getOrgId() + "");
    }


    @PostMapping("findOrgPaging")
    @ApiOperation(value = "查询组织分页列表")
    public ResultDTO<IPage<OrganizationDTO>> findOrgPaging(HttpServletRequest request, @RequestBody OrganizationQuery queryCondition)
            throws Exception {
        queryCondition.setOpId(OperatorUtil.getOperatorId(request));
        IPage<OrganizationDTO> organizationPaging = organizationService.findOrganizationPaging(queryCondition);
        return new ResultDTO<IPage<OrganizationDTO>>().set(ResultCodeEnum.SUCCESS, null, organizationPaging);
    }


    @GetMapping("deleteOrg")
    @ApiOperation(value = "根据组织ID删除组织")
    @ApiImplicitParam(paramType = "query", dataType = "Long", dataTypeClass = Long.class, name = "orgId", value = "组织id", example = "0", required = true)
    @OpLog(title = "根据组织ID删除组织", businessType = BusinessTypeEnum.DELETE)
    public ResultDTO<Void> deleteOrg(HttpServletRequest request, Long orgId) throws Exception {
        ResultDTO<Void> result = new ResultDTO<>();
        LoginOperator oper = OperatorUtil.getOperator(request);
        if (oper.getOpId().longValue() > 1) {
            SmRelOpOrg rel = organizationService.getRelOpOrgByOpOrgId(oper.getOpId(), organizationService.getOrganizationByOrgId(orgId).getParentId());
            if (CommonFuntions.isEmptyObject(rel) || !RelTypeEnum.REAL.equals(rel.getRelType())) {
                return result.set(ResultCodeEnum.ERROR_NOT_ALLOW, "权限不足");
            }
        }
        try {
            organizationService.deleteOrg(orgId, oper.getOpId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除组织失败：{}", orgId);
            return result.set(ResultCodeEnum.ERROR_UNKNOW, e.getMessage());
        }
        return result.set(ResultCodeEnum.SUCCESS, "");
    }


    @GetMapping("searchOrganizations")
    @ApiOperation(value = "根据查询条件查询组织，支持名称模糊查询，编码精确查询")
    public ResultDTO<List<OrganizationDTO>> searchOrganizations(String searchKey, HttpServletRequest request) throws Exception {
        ResultDTO<List<OrganizationDTO>> result = new ResultDTO<>();
        // 当前登录人
        LoginOperator nowOperDTO = OperatorUtil.getOperator(request);
        List<OrganizationDTO> organizationDTOS = organizationService.searchOrganizations(searchKey, nowOperDTO);
        return result.set(ResultCodeEnum.SUCCESS, null, organizationDTOS);
    }
}
