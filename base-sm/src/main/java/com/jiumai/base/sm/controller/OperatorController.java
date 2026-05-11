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

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.security.MD5Util;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.RandomUtils;
import com.jiumai.base.common.core.utils.StringUtils;
import com.jiumai.base.common.core.utils.password.PasswordCheckUtil;
import com.jiumai.base.sm.dto.OperatorDTO;
import com.jiumai.base.sm.dto.UpdatePwdDTO;
import com.jiumai.base.sm.entity.SmOperator;
import com.jiumai.base.sm.query.OperatorQuery;
import com.jiumai.base.sm.service.OperatorService;
import com.jiumai.base.sm.service.RelOpAreaService;
import com.jiumai.base.sm.service.RelOpOrgService;
import com.jiumai.base.sm.service.RelOpRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * 操作员控制器
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
@Api(tags = {"操作员类"})
@RestController
@RequestMapping("d-admin/operator")
public class OperatorController {

    private static SysLog log = SysLogFactory.getLogger(OperatorController.class);

    @Resource
    private OperatorService operatorService;
    @Resource
    private RelOpOrgService relOpOrgService;
    @Resource
    private RelOpRoleService relOpRoleService;
    //	@Resource
//	private RelOrgRoleService relOrgRoleService;
//	@Resource
//	private RelResRoleService relResRoleService;
//	@Resource
//	private RoleService roleService;
    @Resource
    private RelOpAreaService relOpAreaService;
    @Value("${sys.sm4.key}")
    public String key;


    @PostMapping("findOperatorPaging")
    @ApiOperation(value = "查询用户分页列表")
    public ResultDTO<IPage<OperatorDTO>> findOperatorPaging(@RequestBody OperatorQuery operQuery, HttpServletRequest request) throws Exception {

        operQuery.setCreateId(OperatorUtil.getOperatorId(request));

        IPage<OperatorDTO> page = operatorService.findOperatorPaging(operQuery);
        return new ResultDTO<IPage<OperatorDTO>>().set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }


    @PostMapping("saveOper")
    @ApiOperation(value = "新增或更新用户", notes = "参数类型为json")
    @OpLog(title = "新增或更新用户", businessType = BusinessTypeEnum.INSERT_OR_UPDATE)
    public ResultDTO<Void> saveOper(HttpServletRequest request, @RequestBody OperatorDTO oper) throws Exception {
        ResultDTO<Void> result = new ResultDTO<>();
        OperatorDTO operatorDTO = new OperatorDTO();
        BeanUtils.copyProperties(oper, operatorDTO);
        if (CommonFuntions.isEmptyObject(operatorDTO.getOpName())) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "姓名为空");
        }
        if (operatorDTO.getOpId() == null) {
            if (CommonFuntions.isEmptyObject(operatorDTO.getPassword())) {
                return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "初始密码为空");
            }
        }
        String password = null;
        if (CommonFuntions.isNotEmptyObject(operatorDTO.getPassword())) {
            if (!oper.getPassword().equals(oper.getRePassword())) {
                return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "两次密码输入不一致");
            }
            SM4 sm4 = SmUtil.sm4(key.getBytes(StandardCharsets.UTF_8));
            password = sm4.decryptStr(operatorDTO.getPassword(), StandardCharsets.UTF_8);
            String errMsg = PasswordCheckUtil.checkPwdStrong(password);
            if (!"OK".equals(errMsg)) {
                return result.set(ResultCodeEnum.ERROR_INVALID_PARAMS, errMsg);
            }
        }
        long opId = 0;
        // 当前登录人
        LoginOperator nowOperDTO = OperatorUtil.getOperator(request);
        if (operatorDTO.getOpId() == null) {
            if (CommonFuntions.isEmptyObject(operatorDTO.getOpLoginName())) {
                return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "登录名为空");
            }
            SmOperator op = operatorService.getByOpLoginName(operatorDTO.getOpLoginName());
            if (CommonFuntions.isNotEmptyObject(op) && op.getOpId() > 0) {
                return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "登录名已存在");
            }

            String salt = RandomUtils.generateString(8);
            password = SmUtil.sm3WithSalt(salt.getBytes(StandardCharsets.UTF_8)).digestHex(password);
            operatorDTO.setSalt(salt);
            operatorDTO.setPassword(password);
            operatorDTO.setCreateId(nowOperDTO.getOpId());
            operatorDTO.setCreateDate(LocalDateTime.now());
            opId = operatorService.saveOrUpdateOperator(operatorDTO);
            operatorDTO.setOpId(opId);
        } else {
            SmOperator op = operatorService.getByOpLoginName(operatorDTO.getOpLoginName());
            if (op != null && !op.getOpId().equals(operatorDTO.getOpId())) {
                // 用户已存在，不允许修改为相同的用户
                return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "登录名已存在");
            }

            if (StringUtils.isNotEmpty(password)) {
                String salt = op.getSalt();
                if (CommonFuntions.isEmptyObject(salt)) {
                    // 当前用户不存咋盐值情况需要随机生成盐值
                    salt = RandomUtils.generateString(8);
                    operatorDTO.setSalt(salt);
                }
                password = SmUtil.sm3WithSalt(salt.getBytes(StandardCharsets.UTF_8)).digestHex(password);
                operatorDTO.setPassword(password);
            }
            operatorDTO.setModifyId(nowOperDTO.getOpId());
            operatorDTO.setModifyDate(LocalDateTime.now());
            opId = operatorService.saveOrUpdateOperator(operatorDTO);
        }
        if (opId > 0) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功");
        } else {
            return result.set(ResultCodeEnum.ERROR_HANDLE, "保存失败");
        }
    }


    @GetMapping("getAllById")
    @ApiOperation(value = "根据操作员ID获取操作员信息", notes = "返回用户对象")
    @ApiImplicitParam(paramType = "query", dataType = "Long", name = "opId", value = "用户id", example = "0", required = true, dataTypeClass = Long.class)
    public ResultDTO<OperatorDTO> getAllById(Long opId) throws Exception {
        ResultDTO<OperatorDTO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(opId)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "缺少用户ID");
        }
        // 操作员信息
        OperatorDTO operDTO = operatorService.getById(opId);
        if (CommonFuntions.isNotEmptyObject(opId)) {
            // 用户可访问组织
            operDTO.setAccOrgList(relOpOrgService.findRelOpOrgIdsByOpId(opId));
            // 用户拥有的角色
            operDTO.setRoles(relOpRoleService.findRelOpRoleIdsByOpId(opId));
            // 可访问区域
            operDTO.setAreas(relOpAreaService.findRelOpAreaIdsByOpId(opId));
        }
        operDTO.setPassword(null);
        return result.set(ResultCodeEnum.SUCCESS, null, operDTO);
    }

    @PostMapping("modifyOwnInfo")
    @ApiOperation(value = "修改个人信息", notes = "参数类型为smOperator")
    @OpLog(title = "修改个人信息", businessType = BusinessTypeEnum.UPDATE)
    public ResultDTO<Void> modifyOwnInfo(HttpServletRequest request, @RequestBody OperatorDTO operatorDTO) {
        ResultDTO<Void> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(operatorDTO) || CommonFuntions.isEmptyObject(operatorDTO.getOpId())) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "用户Id不能为空");
        }

        if (CommonFuntions.isNotEmptyObject(operatorDTO.getPassword())
                && !operatorDTO.getPassword().equals(operatorDTO.getRePassword())) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "两次密码输入不一致");
        }
        String inputPwd = operatorDTO.getPassword();
        if (StringUtils.isNotBlank(inputPwd)) {
            SM4 sm4 = SmUtil.sm4(key.getBytes(StandardCharsets.UTF_8));
            String pwd = sm4.decryptStr(inputPwd, StandardCharsets.UTF_8);
            if (StringUtils.isBlank(pwd)) {
                throw new BizException("修改密码失败，PC端请使用 Ctrl+F5 或 Fn+Ctrl+F5 刷新后重试！");
            }
            String errMsg = PasswordCheckUtil.checkPwdStrong(pwd);
            if (!"OK".equals(errMsg)) {
                return result.set(ResultCodeEnum.ERROR_INVALID_PARAMS, errMsg);
            }
            Long opId = operatorDTO.getOpId();
            try {
                OperatorDTO op = operatorService.getById(opId);
                String salt = op.getSalt();
                if (CommonFuntions.isEmptyObject(salt)) {
                    // 当前用户不存咋盐值情况需要随机生成盐值
                    salt = RandomUtils.generateString(8);
                    operatorDTO.setSalt(salt);
                }
                operatorDTO.setPassword(SmUtil.sm3WithSalt(salt.getBytes()).digestHex(pwd));
            } catch (Exception e) {
                log.error("根据id获取个人信息失败{}", e);
                return result.set(ResultCodeEnum.ERROR_HANDLE, "更新个人信息失败");
            }
        }

        try {
            SmOperator smOperator = BeanUtil.copyProperties(operatorDTO, SmOperator.class);
            operatorService.updateOperatorInfo(smOperator, request);
        } catch (Exception e) {
            log.error("更新个人信息失败{}", e);
            return result.set(ResultCodeEnum.ERROR_HANDLE, "更新个人信息失败");
        }

        return result.set(ResultCodeEnum.SUCCESS, "更新成功");
    }

    @PostMapping("updatePwd")
    @ApiOperation(value = "修改密码")
    @OpLog(title = "修改密码", businessType = BusinessTypeEnum.UPDATE)
    public ResultDTO<Void> updatePwd(HttpServletRequest request, @RequestBody UpdatePwdDTO updatePwdDTO) throws Exception {
        ResultDTO<Void> result = new ResultDTO<>();
        Long operatorId = OperatorUtil.getOperatorId(request);

        if (CommonFuntions.isNotEmptyObject(updatePwdDTO.getNewPassword())
                && !updatePwdDTO.getNewPassword().equals(updatePwdDTO.getRePassword())) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "两次密码输入不一致！");
        }
        SM4 sm4 = SmUtil.sm4(key.getBytes(StandardCharsets.UTF_8));
        String oldPwd = sm4.decryptStr(updatePwdDTO.getOldPassword(), StandardCharsets.UTF_8);
        String newPwd = sm4.decryptStr(updatePwdDTO.getNewPassword(), StandardCharsets.UTF_8);

        OperatorDTO dbOp = operatorService.getById(operatorId);
        String salt = dbOp.getSalt();
        if (CommonFuntions.isEmptyObject(salt)) {
            if (!dbOp.getPassword().equals(MD5Util.MD5(oldPwd))) {
                return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "原密码错误！");
            }
            // 当前用户不存咋盐值情况需要随机生成盐值
            salt = RandomUtils.generateString(8);
        } else {
            if (!dbOp.getPassword().equals(SmUtil.sm3WithSalt(salt.getBytes()).digestHex(oldPwd))) {
                return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "原密码错误！");
            }
        }
        String errMsg = PasswordCheckUtil.checkPwdStrong(newPwd);
        if (!"OK".equals(errMsg)) {
            return result.set(ResultCodeEnum.ERROR_INVALID_PARAMS, errMsg);
        }
        SmOperator smOperator = new SmOperator();
        smOperator.setOpId(operatorId);
        smOperator.setSalt(salt);
        smOperator.setPassword(SmUtil.sm3WithSalt(salt.getBytes()).digestHex(newPwd));
        operatorService.updateOperatorInfo(smOperator, null);

        return result.set(ResultCodeEnum.SUCCESS);
    }


    @PostMapping(value = "updateOpStatus")
    @ApiOperation(value = "修改操作员状态", notes = "参数类型为formData")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", dataTypeClass = Long.class, name = "opId", example = "0", value = "用户id", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", dataTypeClass = String.class, name = "status", value = "新的状态", required = true)})
    @OpLog(title = "修改操作员状态", businessType = BusinessTypeEnum.UPDATE)
    public ResultDTO<String> updateOpStatus(Long opId, String status, HttpServletRequest request) {

        ResultDTO<String> resultDTO = new ResultDTO<String>();

        if (CommonFuntions.isEmptyObject(opId)) {
            return resultDTO.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "操作员Id不能为空");
        }
        if (CommonFuntions.isEmptyObject(status)) {
            return resultDTO.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "操作员状态不能为空");
        }
        int index = 0;
        try {
            // 登录帐号
            LoginOperator operDTO = OperatorUtil.getOperator(request);
            if (null == operDTO) {
                return resultDTO.set(ResultCodeEnum.ERROR_UNKNOW, "你还未登陆");
            }
            /** 参数校验 */
            if (CommonFuntions.isEmptyObject(opId)) {
                resultDTO.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "参数为空");
                return resultDTO;
            }
            index = operatorService.updateOpStatus(opId, status);
        } catch (Exception e) {
            log.error("删除操作员发生系统异常", e);
            return resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "操作系统异常");
        }
        if (index > 0) {
            return resultDTO.set(ResultCodeEnum.SUCCESS, "操作成功");
        } else {
            return resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "操作系统异常");
        }
    }

}
