package com.jiumai.base.common.core.dto;

import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.ReflectionUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 通用业务返回信息
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
@Data
@ApiModel(value = "返回结果信息")
public class ResultDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否成功", position = 1)
    private boolean success;

    @ApiModelProperty(value = "错误代码", position = 2)
    private String errCode;

    @ApiModelProperty(value = "错误描述", position = 3)
    private String errDesc;

    @ApiModelProperty(value = "结果信息", position = 4)
    private T result;

    public ResultDTO() {
        super();
    }

    public ResultDTO(ResultCodeEnum errCode, String errDesc, T result) {
        super();
        set(errCode, errDesc, result);
    }

    /**
     * 通用set方法
     */
    public ResultDTO<T> set(ResultCodeEnum resultEnum) {
        Assert.notNull(resultEnum, "resultEnum is null");

        this.success = resultEnum.isSuccess();
        this.errCode = resultEnum.getCode();
        this.errDesc = resultEnum.getMsg();
        return this;

    }

    /**
     * 通用set方法
     *
     * @param code 编码
     * @param msg  描述
     */
    public ResultDTO<T> set(String code, String msg) {

        return set(code, msg, null);
    }


    /**
     * 通用set方法
     *
     * @param code   编码
     * @param msg    描述
     * @param result 结果
     */
    public ResultDTO<T> set(String code, String msg, T result) {
        ResultCodeEnum codeEnum = getEnum(ResultCodeEnum.values(), "code", code);
        Assert.notNull(codeEnum, "codeEnum is null");

        this.success = codeEnum.isSuccess();
        this.errCode = codeEnum.getCode();
        this.errDesc = msg;
        this.result = result;

        return this;
    }


    /**
     * 通用set方法
     *
     * @param resultEnum 结果枚举
     * @param msg        描述信息
     * @return
     * @throws Exception
     */
    public ResultDTO<T> set(ResultCodeEnum resultEnum, String msg) {

        return set(resultEnum, msg, null);
    }

    /**
     * 通用set方法
     *
     * @param resultEnum 结果枚举
     * @param msg        描述信息
     * @param result     返回结果
     */
    public ResultDTO<T> set(ResultCodeEnum resultEnum, String msg, T result) {
        Assert.notNull(resultEnum, "resultEnum is null");

        this.success = resultEnum.isSuccess();
        this.errCode = resultEnum.getCode();
        if (msg == null) {
            this.errDesc = resultEnum.getMsg();
        } else {

            this.errDesc = msg;
        }
        this.result = result;

        return this;
    }

    public static <E extends Enum<E>> E getEnum(E[] enumValus, String propertyName, Object value) {
        if (StringUtils.isNotBlank(propertyName) && value != null) {
            for (E e : enumValus) {
                if (value.toString().equals(ReflectionUtils.getFieldValue(e, propertyName).toString())) {
                    return e;
                }
            }
        }
        return null;
    }

    public static <T> ResultDTO<T> error(ResultCodeEnum resultCodeEnum) {
        ResultDTO<T> result = new ResultDTO<>();
        result.errCode = resultCodeEnum.getCode();
        result.errDesc = resultCodeEnum.getMsg();
        return result;
    }

    public static <T> ResultDTO<T> error(String code, String message) {
        ResultDTO<T> result = new ResultDTO<>();
        result.errCode = code;
        result.errDesc = message;
        return result;
    }

}
