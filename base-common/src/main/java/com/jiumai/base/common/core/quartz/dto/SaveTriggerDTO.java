package com.jiumai.base.common.core.quartz.dto;

import lombok.Data;
import org.quartz.JobDataMap;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * 创建定时任务入参
 */
@Data
public class SaveTriggerDTO {
    /**
     * 调度器名称
     */
    private String schedName;

    /**
     * 触发器名称
     */
    private String triggerName;

    /**
     * 触发器分组
     */
    private String triggerGroup;

    /**
     * Job名称
     */
    private String jobName;

    /**
     * Job分组
     */
    private String jobGroup;

    /**
     * Job描述
     */
    private String description;

    /**
     * 下次执行时间 毫秒级时间戳
     */
    private Long nextFireTime;

    /**
     * 上次执行时间 毫秒级时间戳
     */
    private Long prevFireTime;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 触发器状态
     */
    private String triggerState;

    /**
     * 触发器类型
     */
    private String triggerType;

    /**
     * 定时器开始时间 毫秒级时间戳
     * 1.指定开始时间 则到时开始执行
     * 2.未指定开始时间 则立马执行
     */
    private Long startTime;

    /**
     * 定时器结束时间 毫秒级时间戳
     * 1.时间到定时任务不会再执行 优先级最高 例如重复次数还未执行完 只要时间到了 也会结束
     * 2.未指定结束时间 cron类型下不会自动结束 simple类型下重复次数执行完则自动结束
     */
    private Long endTime;

    private String calendarName;

    private Integer misfireInstr;

    /**
     * 实际执行任务类
     */
    private Class jobClassName;

    /**
     * 实际执行任务类全路径
     */
    private String jobClassNamePath;

    /**
     * CRON表达式 针对CRON任务
     */
    private String cronExpression;

    /**
     * 重复次数 针对简单任务 0表示无限次数
     */
    private Integer repeatCount;

    /**
     * 重复时间间隔 针对简单任务 单位秒
     */
    private Integer repeatInterval;

    /**
     * 定时任务额外参数
     */
    private JobDataMap jobDataMap;
}
