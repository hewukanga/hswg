package com.jiumai.base.common.core.quartz.dto;

import lombok.Data;

/**
 * 处理定时任务入参
 */
@Data
public class HandlerTriggerDTO {

    /**
     * Job名称
     */
    private String jobName;

    /**
     * Job分组
     */
    private String jobGroup;

    /**
     * 触发器名称
     */
    private String triggerName;

    /**
     * 触发器分组
     */
    private String triggerGroup;

    /**
     * CRON表达式 针对CRON任务
     */
    private String cronExpression;
}
