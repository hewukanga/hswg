package com.jiumai.base.common.core.quartz.config;

import lombok.Data;

/**
 * 定时任务数据源性配置
 */
@Data
public class QuartzDataSourcePropertiesConfig {

    private String provider;

    private Integer maxConnections;
}
