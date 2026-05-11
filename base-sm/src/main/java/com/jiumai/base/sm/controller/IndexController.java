/*
 * MainConroller.java Created on 2016年9月21日 下午1:36:23
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

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.constant.LoginPlatform;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.dto.RefreshTokenDTO;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.dto.TokenDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.service.CurrentOperatorService;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.common.core.utils.PropertiesUtils;
import com.jiumai.base.common.core.utils.SpringUtils;
import com.jiumai.base.common.core.utils.StringUtils;
import com.jiumai.base.sm.dto.LoginDTO;
import com.jiumai.base.sm.dto.PermissionDTO;
import com.jiumai.base.sm.dto.ResourceDTO;
import com.jiumai.base.sm.dto.SysInfoDTO;
import com.jiumai.base.sm.service.AuthService;
import com.jiumai.base.sm.service.LoginLogService;
import com.jiumai.base.sm.service.RelRoleInterfaceService;
import com.jiumai.base.sm.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Index控制器
 */

@Api(tags = {"用户登陆认证类"})
@RestController
@RequestMapping("d-admin")
public class IndexController {

    @Resource
    private AuthService authService;

    @Resource
    private ResourceService resourceService;

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private RelRoleInterfaceService relRoleInterfaceService;

    /**
     * 登陆认证
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("login")
    @ApiOperation(value = "登陆认证(JSON)", notes = "通过用户名和密码登陆，返回用户token")
    @OpLog(title = "登陆认证", businessType = BusinessTypeEnum.LOGIN)
    public ResultDTO<TokenDTO> login(HttpServletRequest request, @RequestBody LoginDTO loginDTO) throws Exception {
        ResultDTO<TokenDTO> result = new ResultDTO<>();
        if (StringUtils.isBlank(loginDTO.getOpLoginName())) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "用户不能为空！");
        }
        if (StringUtils.isBlank(loginDTO.getPassword())) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "密码不能为空！");
        }
        // 验证密码，获取用户信息
        LoginOperator op = authService.login(loginDTO.getOpLoginName(), loginDTO.getPassword());
        if (op == null) {
            return result.set(ResultCodeEnum.FAIL, "账号或密码错误！");
        }
        CurrentOperatorService service = SpringUtils.getCurrentOperatorService();
        if (service == null) {
            return result.set(ResultCodeEnum.ERROR_AUTH, "无法获取有效的Token：no service");
        }
        // 生成TOKEN
        TokenDTO token = service.genTokenByNewWay(op);
        if (token == null || StringUtils.isEmpty(token.getRefreshToken()) || StringUtils.isEmpty(token.getRequestToken())) {
            return result.set(ResultCodeEnum.ERROR_AUTH, "无法通过" + service.getClass() + "服务获取有效的Token");
        }

        // 存储当前token关联接口权限
//        relRoleInterfaceService.handleLoginRelRoleInterface(op.getOpId(), op.getRoleIds(), token.getRequestToken());

        loginLogService.saveLoginLog(op.getOpId(), 1, LoginPlatform.LOGIN_PC);

        return result.set(ResultCodeEnum.SUCCESS, "", token);
    }

    @PostMapping("autoLogin")
    @ApiOperation(value = "第三方免密登陆", notes = "通过第三方授权码登陆，返回用户token")
    @ApiImplicitParam(paramType = "query", name = "code", value = "授权码", required = true, dataType = "String", dataTypeClass = String.class)
    @OpLog(title = "第三方免密登陆", businessType = BusinessTypeEnum.LOGIN)
    public ResultDTO<TokenDTO> autoLogin(HttpServletRequest request, String code) throws Exception {

        ResultDTO<TokenDTO> result = new ResultDTO<>();
        // 获取用户信息
        LoginOperator op = authService.autoLogin(code);

        if (op == null) {
            return result.set(ResultCodeEnum.FAIL, "用户不存在！");
        }
        CurrentOperatorService service = SpringUtils.getCurrentOperatorService();
        if (service == null) {
            return result.set(ResultCodeEnum.ERROR_AUTH, "无法获取有效的Token：no service");
        }
        // 生成TOKEN
        TokenDTO token = service.genTokenByNewWay(op);
        if (token == null || StringUtils.isEmpty(token.getRefreshToken()) || StringUtils.isEmpty(token.getRequestToken())) {
            return result.set(ResultCodeEnum.ERROR_AUTH, "无法通过" + service.getClass() + "服务获取有效的Token");
        }

        loginLogService.saveLoginLog(op.getOpId(), 1, LoginPlatform.LOGIN_THIRD_AUTO);

        return result.set(ResultCodeEnum.SUCCESS, "", token);
    }

    @GetMapping("currentOp")
    @ApiOperation("获取当前登陆用户")
    public ResultDTO<LoginOperator> getCurrentOperator(HttpServletRequest request) {

        LoginOperator opdto = OperatorUtil.getOperator(request);

        // 加载菜单信息
        ResultDTO<LoginOperator> result = new ResultDTO<>();
        result.set(ResultCodeEnum.SUCCESS, "", opdto);
        return result;
    }

    /**
     * 首页
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "用户权限信息", notes = "查询用户可访问菜单信息，可访问组织、可访问的按钮信息")
    @GetMapping("userPermission")
    public ResultDTO<PermissionDTO> userPermission(HttpServletRequest request) throws Exception {
        ResultDTO<PermissionDTO> result = new ResultDTO<>();
        Long opId = OperatorUtil.getOperatorId(request);
        if (opId != null && opId.longValue() > 0) {
            List<ResourceDTO> res = this.resourceService.getOperatorMenusTree(opId);
            List<ResourceDTO> btns = this.resourceService.findOperatorBtnList(opId);
            //session.setAttribute(OP_MENUS_SESSION, res);
            PermissionDTO per = new PermissionDTO();
            per.setRes(res);
            per.setResBtn(btns);
            return result.set(ResultCodeEnum.SUCCESS, "", per);
        } else {
            return result.set(ResultCodeEnum.ERROR_AUTH_TIME_OUT, null);
        }
    }

    /**
     * 下线
     *
     * @param request
     * @return
     * @throws Exception
     */

    @GetMapping("logout")
    @ApiOperation(value = "退出登录", notes = "将用户信息从redis中删除")
    @OpLog(title = "退出登录", businessType = BusinessTypeEnum.LOGOUT)
    public ResultDTO<Void> logout(HttpServletRequest request) {
        Long operatorId = OperatorUtil.getOperatorId(request);
        OperatorUtil.deleteOperator(request);

        ResultDTO<Void> result = new ResultDTO<>();

        loginLogService.saveLoginLog(operatorId, 2, LoginPlatform.LOGIN_PC);

        return result.set(ResultCodeEnum.SUCCESS, "登出成功！");
    }

    @ApiOperation(value = "刷新token", notes = "刷新refreshToken的信息,重新获取requestToken信息")
    @PostMapping("/refresh")
    public ResultDTO<TokenDTO> refresh(@RequestBody RefreshTokenDTO refreshToken) {
        ResultDTO<TokenDTO> result = new ResultDTO<>();
        if (StringUtils.isEmpty(refreshToken.getRefreshToken())) {
            return result.set(ResultCodeEnum.ERROR_AUTH, "无法获取refreshToken");
        }
        CurrentOperatorService service = SpringUtils.getCurrentOperatorService();
        if (service == null) {
            return result.set(ResultCodeEnum.ERROR_AUTH, "无法获取有效的Token：no service");
        }
        TokenDTO token = service.refreshTokenByNewWay(refreshToken.getRefreshToken());
        if (token == null || StringUtils.isEmpty(token.getRefreshToken()) || StringUtils.isEmpty(token.getRequestToken())) {
            return result.set(ResultCodeEnum.ERROR_AUTH, "无法通过" + service.getClass() + "服务获取有效的Token");
        }
        return result.set(ResultCodeEnum.SUCCESS, "", token);
    }

    @ApiOperation(value = "获取系统信息")
    @GetMapping("/getSysInfo")
    public ResultDTO<SysInfoDTO> getSysInfo() {
        ResultDTO<SysInfoDTO> result = new ResultDTO<>();
        SysInfoDTO sysInfo = new SysInfoDTO();
        sysInfo.setKey(PropertiesUtils.getProperty("sys.sm4.key"));
        return result.set(ResultCodeEnum.SUCCESS, "", sysInfo);
    }

}
