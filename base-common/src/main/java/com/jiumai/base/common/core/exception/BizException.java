package com.jiumai.base.common.core.exception;

import com.jiumai.base.common.core.enums.ResultCodeEnum;

/**
 * @author skm
 * @date 2022/7/11 16:24
 */
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    private ResultCodeEnum resultCodeEnum;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMsg());
        this.resultCodeEnum = resultCodeEnum;
    }

    public BizException(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.resultCodeEnum = resultCodeEnum;
    }

    public ResultCodeEnum getResultCodeEnum() {
        return this.resultCodeEnum;
    }
}
