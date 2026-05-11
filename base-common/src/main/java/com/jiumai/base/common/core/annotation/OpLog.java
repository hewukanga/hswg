package com.jiumai.base.common.core.annotation;


import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.OperateTypeEnum;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 * 
 * @author ruoyi
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpLog
{
    /**
     * 模块 
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;

    /**
     * 操作人类别
     */
    OperateTypeEnum operateType() default OperateTypeEnum.PC;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
