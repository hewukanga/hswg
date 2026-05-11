package com.jiumai.base.common.core.quartz.config;

import lombok.Data;

/**
 * 定时任务存储方式属性配置
 */
@Data
public class QuartzJobStorePropertiesConfig {

    /**
     * 事务管理类
     */
    private String jobStoreClass;

    /**
     * 类似于Hibernate的dialect，用于处理DB之间的差异，StdJDBCDelegate能满足大部分的DB
     */
    private String driverDelegateClass;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 是否用properties
     */
    private Boolean useProperties;

    /**
     * 数据源
     */
    private String dataSource;
}
