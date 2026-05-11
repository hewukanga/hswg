package com.jiumai.base.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @ Author     ：cz
 * @ Date       ：Created in 2023-03-0214:17
 * @ Description：指定SpringBoot自带的定时任务的线程数量，默认单线程
 * @ Modified By：
 */
@Configuration
public class ExecutorConfig implements SchedulingConfigurer {
    @Bean
    public Executor taskExecutor() {
        //指定定时任务线程数量，可根据需求自行调节
        return Executors.newScheduledThreadPool(5);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }
}
