/*
 * ResuleCodeEnum.java Created on 2016年9月25日 下午8:59:38
 * Copyright (c)  2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
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
package com.jiumai.base.common.core.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 * 通用业务返回结果编码Code枚举
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public enum ResultCodeEnum {
    
    /**
     * 成功
     */
    SUCCESS(true, "10000", "成功"),

    /**
     * 失败
     */
    FAIL(false, "20000", "失败"),

    /**
     * 服务不可用（未知错误）
     */
    ERROR_UNKNOW(false, "30000", "服务不可用"),
    
    /**
     * 鉴权失败
     */
    ERROR_AUTH(false, "30001", "鉴权失败"),

    /**
     * 访问授权过期
     */
    ERROR_AUTH_TIME_OUT(false, "30002", "访问授权过期"),
    
    /**
     * 请求频繁
     */
    ERROR_REQ_OFTEN(false, "30003", "请求频繁"),
    
    /**
     * 缺少必须的参数
     */
    ERROR_MISSING_PARAMS(false, "40001", "缺少必须的参数"),
    
    /**
     * 非法参数
     */
    ERROR_INVALID_PARAMS(false, "40002", "非法参数"),
    
    /**
     * 业务处理失败
     */
    ERROR_HANDLE(false, "40004", "业务处理失败"),
    
    /**
     * 权限不足
     */
    ERROR_NOT_ALLOW(false, "40006", "权限不足");
    
    private boolean success; // 结果

    private String code;   // 代码

    private String msg; // 描述
    
    ResultCodeEnum(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
