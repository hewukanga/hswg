package com.jiumai.base.common.core.annotation;


import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.OperateTypeEnum;
import com.jiumai.base.common.core.enums.TriggerTypeEnum;

import java.lang.annotation.*;
import java.time.LocalDateTime;

/**
 * 自定义定时任务注解 加到定时任务代码处理类上
 * 支持不指定开始时间、结束时间、入参的定时任务，满足日常需求
 * 否则请调用TriggerService中的方法或者通过系统管理-定时任务管理界面执行新增等操作
 * @author hwk
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Quartz
{
    /**
     * Job名称
     */
    String jobName() default "";

    /**
     * Job分组
     */
    String jobGroup() default "";

    /**
     * 触发器分组
     */
    String triggerGroup() default "";

    /**
     * 触发器名称
     */
    String triggerName() default "";

    /**
     * Job描述
     */
    String description() default "";

    /**
     * CRON表达式 针对CRON任务
     */
    String cronExpression() default "";

    /**
     * 重复次数 针对简单任务 0表示无限次数
     */
    int repeatCount() default 0;

    /**
     * 重复时间间隔 针对简单任务 单位秒
     */
    int repeatInterval() default 60;

    /**
     * 实际执行任务类
     */
    Class jobClassName();

    /**
     * 触发器类型 cron：cron表达式任务  simple：简单任务
     */
    TriggerTypeEnum triggerType() default TriggerTypeEnum.CRON;


}
