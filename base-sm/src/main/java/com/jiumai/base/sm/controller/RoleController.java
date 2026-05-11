/*
 * RoleController.java Created on 2016年10月26日 上午10:44:36
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

import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.IsValidEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.dto.RoleDTO;
import com.jiumai.base.sm.entity.SmOperator;
import com.jiumai.base.sm.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 角色控制器
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
@Api(tags = {"角色类"})
@RestController
@RequestMapping("d-admin/role")
public class RoleController {

    private static SysLog log = SysLogFactory.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;

//	@GetMapping(value = "findOpRoleList")
//	@ApiOperation(value = "用户角色列表")
//	public List<RoleDTO> findRoleListByOpId(HttpServletRequest request) throws Exception {
//		Long opId = OperatorUtil.getOperatorId(request);
//		return this.roleService.findRoleList(opId);
//	}

    @PostMapping(value = "addRole")
    @ApiOperation(value = "新增或修改角色")
    @OpLog(title = "新增或修改角色", businessType = BusinessTypeEnum.INSERT_OR_UPDATE)
    public ResultDTO<String> addRole(@RequestBody RoleDTO roleDTO, HttpServletRequest request) throws Exception {
        ResultDTO<String> result = new ResultDTO<String>();
        LoginOperator oper = OperatorUtil.getOperator(request);
        if (oper == null) {
            return result.set(ResultCodeEnum.ERROR_UNKNOW, "请先登陆！");
        }
        // 无父节点直接返回
        if (roleDTO.getParentId() == null) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "父Id为空");
        }
        if (roleDTO.getRoleName() == null) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "角色名称为空");
        }
        if (roleDTO.getRoleCode() == null) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "角色编码为空");
        }
        if (roleDTO.getRoleId() == null) {
            roleDTO.setCreateId(oper.getOpId());
        }
        // 角色名是否重复
        boolean b = roleService.ifRepeatName(roleDTO);
        if (b == false) {
            return result.set(ResultCodeEnum.ERROR_AUTH, "角色名已存在");
        }

        // 上一级角色
        RoleDTO parentRoleDO = roleService.findRoleById(roleDTO.getParentId());

        // 级别,根据path获取
        if (CommonFuntions.isEmptyObject(parentRoleDO.getRolePath())) {
            String rolePath = "#" + parentRoleDO.getRoleId() + "#";
            roleDTO.setRolePath(rolePath);
        } else {
            roleDTO.setRolePath(parentRoleDO.getRolePath());
        }

        String resIdListStr = roleDTO.getResIdList();
        String orgIdListStr = roleDTO.getOrgIdList();
        //新增
        if (roleDTO.getRoleId() == null) {
            try {
                roleService.addRole(roleDTO, resIdListStr, orgIdListStr);
            } catch (Exception e) {
                log.error("角色添加失败", e);
                return result.set(ResultCodeEnum.ERROR_HANDLE, "角色添加失败");
            }
            return result.set(ResultCodeEnum.SUCCESS, "");
        } else {
            //修改
            try {
                roleService.updateRole(roleDTO, resIdListStr, orgIdListStr);
            } catch (Exception e) {
                log.error("角色修改失败", e);
                return result.set(ResultCodeEnum.ERROR_HANDLE, "角色修改失败");
            }
            return result.set(ResultCodeEnum.SUCCESS, "修改成功");
        }

    }

    @GetMapping("findRoleTree")
    @ApiOperation(value = "获取角色树")
    public ResultDTO<List<RoleDTO>> findRoleTree(HttpServletRequest request) throws Exception {
        ResultDTO<List<RoleDTO>> result = new ResultDTO<>();
        List<RoleDTO> roleTree = roleService.findRoleTree(OperatorUtil.getOperatorId(request));

        return result.set(ResultCodeEnum.SUCCESS, null, roleTree);

    }

//    @GetMapping("findRolePaging")
//    @ApiOperation(value = "查询角色分页列表")
//    public ResultDTO<PageQuery<RoleDTO>> findRolePaging(RoleQuery roleQuery) throws Exception {
//        ResultDTO<PageQuery<RoleDTO>> result = new ResultDTO<>();
//
//        PageQuery<RoleDTO> rolePaging = roleService.findRolePaging(roleQuery);
//
//        return result.set(ResultCodeEnum.SUCCESS, null, rolePaging);
//    }

//    @GetMapping("getRoleById")
//    @ApiOperation(value = "根据角色id获取角色信息")
//    @ApiImplicitParam(paramType = "query", dataType = "Long", name = "roleId", value = "角色id", example = "0", required = true)
//    public RoleDTO getRoleById(Long roleId) throws Exception {
//        return roleService.findRoleById(roleId);
//    }

    @GetMapping("getRoleAllById")
    @ApiOperation(value = "根据角色id获取角色信息")
    @ApiImplicitParam(paramType = "query", dataType = "Long", dataTypeClass = Long.class, name = "roleId", value = "角色id", example = "0", required = true)
    public ResultDTO<RoleDTO> getRoleALLById(@RequestParam("roleId") Long roleId) throws Exception {
        ResultDTO<RoleDTO> result = new ResultDTO<>();
        RoleDTO role = roleService.findRoleById(roleId);
        List<Long> resIds = roleService.findRelResRoleByRoleId(roleId);
        List<Long> orgIds = roleService.findRelOrgRoleByRoleId(roleId);
        role.setOrgIds(orgIds);
        role.setResIds(resIds);

        return result.set(ResultCodeEnum.SUCCESS, null, role);
    }

//    @GetMapping("getRoleByParentId")
//    @ApiOperation(value = "根据父类角色id获取角色信息")
//    @ApiImplicitParam(paramType = "query", dataType = "Long", name = "parentId", value = "父角色id", example = "0", required = true)
//    public RoleDTO getRoleByParentId(long parentId) throws Exception {
//        return roleService.findRoleByPrentId(parentId);
//    }


//    @GetMapping(value = "findRelOrgRoleByRoleId")
//    @ApiOperation(value = "通过角色Id查询角色拥有的组织")
//    @ApiImplicitParam(paramType = "query", dataType = "Long", name = "roleId", value = "角色id", example = "0", required = true)
//    public ResultDTO<List<Long>> findRelOrgRoleByRoleId(Long roleId, HttpServletRequest request) throws Exception {
//        ResultDTO<List<Long>> resultDTO = new ResultDTO<List<Long>>();
//        // 登录帐号
//        LoginOperator oper = OperatorUtil.getOperator(request);
//        if (null == oper) {
//            return resultDTO.set(ResultCodeEnum.ERROR_UNKNOW, "你还未登陆");
//        }
//        // TODO 验证操作权限
//
//        // 查询角色与组织之间的关系信息
//        List<Long> list = roleService.findRelOrgRoleByRoleId(roleId);
//        return resultDTO.set(ResultCodeEnum.SUCCESS, "操作成功", list);
//    }


//    @PostMapping(value = "enableEvent")
//    @ApiOperation(value = "启用角色")
//    public ResultDTO<String> enableEvent(String roleIds, HttpServletRequest request) {
//        ResultDTO<String> resultDTO = new ResultDTO<String>();
//        // 登录帐号
//        LoginOperator oper = OperatorUtil.getOperator(request);
//        if (null == oper) {
//            return resultDTO.set(ResultCodeEnum.ERROR_UNKNOW, "你还未登陆");
//        }
//        try {
//            /** 参数校验 */
//            if (CommonFuntions.isEmptyObject(roleIds)) {
//                resultDTO.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "参数为空");
//                return resultDTO;
//            }
//            roleService.enableOrDisableRole(roleIds, IsValidEnum.VALID, oper.getOpId());
//        } catch (Exception e) {
//            log.error("角色启用发生系统异常", e);
//            resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "操作系统异常");
//        }
//        return resultDTO.set(ResultCodeEnum.SUCCESS, "操作成功");
//    }


//    @PostMapping(value = "disableEvent")
//    @ApiOperation(value = "禁用角色")
//    @ApiImplicitParam(paramType = "query", dataType = "String", name = "roleIds", value = "角色id", required = true)
//    public ResultDTO<String> disableEvent(String roleIds, HttpServletRequest request) {
//        ResultDTO<String> resultDTO = new ResultDTO<String>();
//        // 登录帐号
//        LoginOperator oper = OperatorUtil.getOperator(request);
//        if (null == oper) {
//            return resultDTO.set(ResultCodeEnum.ERROR_UNKNOW, "你还未登陆");
//        }
//        try {
//            /** 参数校验 */
//            if (CommonFuntions.isEmptyObject(roleIds)) {
//                return resultDTO.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "参数为空");
//            }
//            roleService.enableOrDisableRole(roleIds, IsValidEnum.INVALID, oper.getOpId());
//        } catch (Exception e) {
//            log.error("角色禁用用发生系统异常", e);
//            return resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "操作系统异常");
//        }
//        return resultDTO.set(ResultCodeEnum.SUCCESS, "操作成功");
//    }


    @GetMapping(value = "delRoleByRoleId")
    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(paramType = "query", dataType = "Long", dataTypeClass = Long.class, name = "roleId", value = "角色id", example = "0", required = true)
    @OpLog(title = "删除角色", businessType = BusinessTypeEnum.DELETE)
    public ResultDTO<String> delRoleByRoleId(HttpServletRequest request, Long roleId) {

        ResultDTO<String> result = new ResultDTO<String>();
        if (CommonFuntions.isEmptyObject(roleId)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "角色ID不能为空");
        }
        // 登录帐号
        LoginOperator oper = OperatorUtil.getOperator(request);

        if (null == oper) {
            return result.set(ResultCodeEnum.ERROR_UNKNOW, "你还未登陆");
        }
        try {
            /** 参数校验 */
            if (CommonFuntions.isEmptyObject(roleId)) {
                return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "参数为空");
            }
            roleService.delRoleById(roleId, IsValidEnum.INVALID, oper.getOpId());
        } catch (Exception e) {
            log.error("角色禁用用发生系统异常", e);
            return result.set(ResultCodeEnum.ERROR_HANDLE, e.getMessage());
        }
        return result.set(ResultCodeEnum.SUCCESS, "");
    }


    @GetMapping("findOpByRoleId")
    @ApiOperation(value = "根据角色id获取拥有该角色的账号")
    @ApiImplicitParam(paramType = "query", dataType = "Long", dataTypeClass = Long.class, name = "roleId", value = "角色id", example = "0", required = true)
    public ResultDTO<SmOperator> findOpByRoleId(@RequestParam("roleId") Long roleId) throws Exception {
        ResultDTO<SmOperator> result = new ResultDTO<>();
        return result.set(ResultCodeEnum.SUCCESS, "查询成功");
    }

}
