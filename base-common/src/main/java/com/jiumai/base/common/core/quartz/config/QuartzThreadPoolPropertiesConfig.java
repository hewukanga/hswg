package com.jiumai.base.common.core.quartz.config;

import lombok.Data;

/**
 * 定时任务线程池属性配置
 */
@Data
public class QuartzThreadPoolPropertiesConfig {

    /**
     * 线程池类
     */
    private String threadPoolClass;

    /**
     * 线程个数
     */
    private Integer threadCount;

    /**
     * 线程优先级
     */
    private Integer threadPriority;
}
