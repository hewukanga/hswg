package com.jiumai.base.common.core.quartz.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "org.quartz")
@Data
public class QuartzPropertiesConfig {

    /**
     * 定时任务线程池属性配置
     */
    private QuartzThreadPoolPropertiesConfig threadPool = new QuartzThreadPoolPropertiesConfig();

    /**
     * 定时任务存储方式属性配置
     */
    private QuartzJobStorePropertiesConfig jobStore = new QuartzJobStorePropertiesConfig();

    /**
     * 定时任务数据源性配置
     */
    private QuartzDataSourcePropertiesConfig dataSource = new QuartzDataSourcePropertiesConfig();
}
