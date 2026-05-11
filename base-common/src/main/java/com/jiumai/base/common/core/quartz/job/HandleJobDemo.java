package com.jiumai.base.common.core.quartz.job;

import com.alibaba.fastjson2.JSONObject;
import com.jiumai.base.common.core.utils.CommonFuntions;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * 定时任务处理类 在新增定时任务时和定时任务做绑定 此类只做例子 实际项目的处理类在biz模块自建
 */
@Component
@Slf4j
//@Quartz(description = "cron定时任务例子（指定cron表达式）", jobGroup = "handleJobGroupDemo", jobName = "handleJobGroupDemo", jobClassName = HandleJobDemo.class, triggerGroup = "handleTriggerGroupDemo", triggerName = "handleTriggerGroupDemo", triggerType = TriggerTypeEnum.CRON, cronExpression = "0 0/1 * * * ? ")
//@Quartz(description = "简单定时任务例子（指定重复执行次数以及间隔时间）", jobGroup = "handleJobGroupDemo", jobName = "handleJobGroupDemo", jobClassName = HandleJobDemo.class, triggerGroup = "handleTriggerGroupDemo", triggerName = "handleTriggerGroupDemo", triggerType = TriggerTypeEnum.SIMPLE, repeatCount = 10, repeatInterval = 3)
public class HandleJobDemo implements Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        log.info("定时任务自定义入参：{}", JSONObject.toJSONString(jobDataMap));
        if(CommonFuntions.isEmptyObject(jobDataMap)){
            log.error("非法定时任务入参，缺少自定义参数");
            return;
        }
        //通过jobDataMap.get("***");获取具体字段的值
    }
}
