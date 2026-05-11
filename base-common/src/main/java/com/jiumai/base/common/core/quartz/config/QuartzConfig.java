package com.jiumai.base.common.core.quartz.config;

import com.jiumai.base.common.core.mapper.QuartzMapper;
import com.jiumai.base.common.core.mapper.QuartzRunRecordMapper;
import com.jiumai.base.common.core.quartz.factory.ApplicationContextHolder;
import com.jiumai.base.common.core.quartz.listen.QuartzJobListener;
import com.jiumai.base.common.core.utils.PropertiesUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuartzConfig {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private QuartzPropertiesConfig quartzPropertiesConfig;

    @Value("${spring.datasource.driver-class-name}")
    public String driver;
    @Value("${spring.datasource.url}")
    public String url;
    @Value("${spring.datasource.username}")
    public String user;
    @Value("${spring.datasource.password}")
    public String password;

    /**
     * Create the job factory bean
     *
     * @return Job factory bean
     */
    @Bean
    public JobFactory jobFactory() {
        ApplicationContextHolder jobFactory = new ApplicationContextHolder();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
    /**
     * Create the Scheduler Factory bean
     *
     * @return scheduler factory object
     */
    @Bean
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        try {
            factory.setSchedulerName("quartzScheduler");
            // 延时启动
            factory.setStartupDelay(1);
            factory.setApplicationContextSchedulerContextKey("applicationContextKey");
            factory.setQuartzProperties(quartzProperties());
            // 可选，QuartzScheduler
            // 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
            factory.setOverwriteExistingJobs(true);
            factory.setJobFactory(jobFactory());
            // 设置自动启动，默认为true
            factory.setAutoStartup(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return factory;
    }
    @Bean
    public Properties quartzProperties() throws IOException {
        // quartz参数
        Properties prop = new Properties();
        // 线程池配置
        prop.put("org.quartz.threadPool.class", quartzPropertiesConfig.getThreadPool().getThreadPoolClass());
        prop.put("org.quartz.threadPool.threadCount", String.valueOf(quartzPropertiesConfig.getThreadPool().getThreadCount()));
        prop.put("org.quartz.threadPool.threadPriority", String.valueOf(quartzPropertiesConfig.getThreadPool().getThreadPriority()));
        //存储方式配置
        prop.put("org.quartz.jobStore.class", quartzPropertiesConfig.getJobStore().getJobStoreClass());
        prop.put("org.quartz.jobStore.driverDelegateClass", quartzPropertiesConfig.getJobStore().getDriverDelegateClass());
        prop.put("org.quartz.jobStore.tablePrefix", quartzPropertiesConfig.getJobStore().getTablePrefix());
        prop.put("org.quartz.jobStore.useProperties", quartzPropertiesConfig.getJobStore().getUseProperties());
        prop.put("org.quartz.jobStore.dataSource", quartzPropertiesConfig.getJobStore().getDataSource());
        //数据源配置
        prop.put("org.quartz.dataSource.qzDS.provider", quartzPropertiesConfig.getDataSource().getProvider());
        prop.put("org.quartz.dataSource.qzDS.driver",driver);
        prop.put("org.quartz.dataSource.qzDS.URL", url);
        prop.put("org.quartz.dataSource.qzDS.user", user);
        prop.put("org.quartz.dataSource.qzDS.password", password);
        prop.put("org.quartz.dataSource.qzDS.maxConnections", String.valueOf(quartzPropertiesConfig.getDataSource().getMaxConnections()));
        return prop;
    }
    /**
     * Create the Scheduler bean
     *
     * @param quartzRunRecordMapper
     * @param quartzMapper
     * @return Scheduler
     * @throws SchedulerException
     */
    @Bean
    public Scheduler scheduler(@Autowired QuartzRunRecordMapper quartzRunRecordMapper, @Autowired QuartzMapper quartzMapper) throws SchedulerException {
        //在这里注入日志服务类且激活监听器，如果直接在监听器类里面使用@Autowired会出现注入为null
        schedulerFactory().getScheduler().getListenerManager().addJobListener(new QuartzJobListener(quartzRunRecordMapper, quartzMapper));
        return schedulerFactory().getScheduler();
    }
}
