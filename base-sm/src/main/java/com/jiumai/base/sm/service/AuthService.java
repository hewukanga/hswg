/*
 * @(#)LoginService.java 2016年9月6日下午9:32:34
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.sm.service;

import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.sm.dto.ResourceDTO;

import java.util.List;

/**
 * 登陆、下线处理Service
 */

public interface AuthService {

    /**
     * 登陆
     *
     * @return
     * @throws Exception
     */
    LoginOperator login(String opLoginName, String password) throws Exception;

    /**
     * 第三方授权登录
     *
     * @param code
     * @return
     * @throws Exception
     */
    LoginOperator autoLogin(String code) throws Exception;
}
