package com.jiumai.base.common.core.quartz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.enums.TriggerTypeEnum;
import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.quartz.dto.HandlerTriggerDTO;
import com.jiumai.base.common.core.quartz.dto.SaveTriggerDTO;
import com.jiumai.base.common.core.quartz.service.TriggerService;
import com.jiumai.base.common.core.utils.CommonFuntions;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 定时任务服务实现类
 */
@Service
@Slf4j
public class TriggerServiceImpl implements TriggerService {

    @Resource
    private Scheduler scheduler;

    @Override
    public void createTrigger(SaveTriggerDTO saveTriggerDTO) throws SchedulerException {
        if (!org.quartz.Job.class.isAssignableFrom(saveTriggerDTO.getJobClassName())) {
            throw new BizException(ResultCodeEnum.FAIL, "任务类需要继承 org.quartz.Job");
        }

        JobDetail jobDetail = wrapJobDetail(saveTriggerDTO);


        Trigger trigger = wrapTrigger(saveTriggerDTO, jobDetail);
        scheduler.scheduleJob(jobDetail, trigger);
        log.info("定时任务triggerName：【" + saveTriggerDTO.getTriggerName() + "】创建成功");
    }

    /**
     * 封装trigger
     * @param saveTriggerDTO
     * @param jobDetail
     * @return
     */
    private Trigger wrapTrigger(SaveTriggerDTO saveTriggerDTO, JobDetail jobDetail){
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(saveTriggerDTO.getTriggerName(), saveTriggerDTO.getTriggerGroup()).forJob(jobDetail);
        if (saveTriggerDTO.getStartTime() != null && saveTriggerDTO.getStartTime() > 0) {
            triggerBuilder.startAt(new Date(saveTriggerDTO.getStartTime()));
        }
        if (saveTriggerDTO.getEndTime() != null && saveTriggerDTO.getEndTime() > 0) {
            triggerBuilder.endAt(new Date(saveTriggerDTO.getEndTime()));
        }

        ScheduleBuilder scheduleBuilder;
        // 根据不同的类型，设置不同参数，构建对应类型的触发器
        if (TriggerTypeEnum.CRON.getValue().equalsIgnoreCase(saveTriggerDTO.getTriggerType())) {
            if (CommonFuntions.isEmptyObject(saveTriggerDTO.getCronExpression())) {
                throw new BizException(ResultCodeEnum.FAIL, "CRON任务中，CRON表达式不能为空");
            }
            scheduleBuilder = CronScheduleBuilder.cronSchedule(saveTriggerDTO.getCronExpression());

        } else if (TriggerTypeEnum.SIMPLE.getValue().equalsIgnoreCase(saveTriggerDTO.getTriggerType())) {
            if (saveTriggerDTO.getRepeatInterval() == null) {
                throw new BizException(ResultCodeEnum.FAIL, "简单任务中，重复间隔不能为空");
            }
            if (saveTriggerDTO.getRepeatCount() == null) {
                throw new BizException(ResultCodeEnum.FAIL, "简单任务中，重复次数不能为空");
            }

            int interval = saveTriggerDTO.getRepeatInterval();
            int count = saveTriggerDTO.getRepeatCount();
            if (count < 1) {
                scheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForever(interval);
            } else {
                scheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(count, interval);
            }
        } else {
            throw new BizException(ResultCodeEnum.FAIL, "不支持的触发器类型:" + saveTriggerDTO.getTriggerType());
        }
        Trigger trigger = triggerBuilder.withSchedule(scheduleBuilder).build();
        return trigger;
    }

    /**
     * 封装jobDetail
     * @param saveTriggerDTO
     * @return
     */
    private JobDetail wrapJobDetail(SaveTriggerDTO saveTriggerDTO){
        JobBuilder jobBuilder = JobBuilder.newJob(saveTriggerDTO.getJobClassName())
                .withIdentity(saveTriggerDTO.getJobName(), saveTriggerDTO.getJobGroup())
                .withDescription(saveTriggerDTO.getDescription());

        // 检查是否有额外的任务参数，并设置Job Data
        if (CollectionUtils.isNotEmpty(saveTriggerDTO.getJobDataMap())) {
            jobBuilder = jobBuilder.usingJobData(saveTriggerDTO.getJobDataMap());
        }

        JobDetail jobDetail = jobBuilder.build();
        return jobDetail;
    }
    @Override
    public Boolean checkTriggerExist(String triggerName, String triggerGroup) throws SchedulerException {
        TriggerKey tk = TriggerKey.triggerKey(triggerName, triggerGroup);
        Trigger trigger = scheduler.getTrigger(tk);
        //存在 返回true
        if(CommonFuntions.isNotEmptyObject(trigger)){
            return true;
        }
        return false;
    }

    @Override
    public void pauseTrigger(HandlerTriggerDTO handlerTriggerDTO) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(handlerTriggerDTO.getJobName(), handlerTriggerDTO.getJobGroup());
        scheduler.pauseJob(jobKey);
        log.info("定时任务JobName：【" + handlerTriggerDTO.getJobName() + "】暂停成功");
    }

    @Override
    public void resumeTrigger(HandlerTriggerDTO handlerTriggerDTO) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(handlerTriggerDTO.getJobName(), handlerTriggerDTO.getJobGroup());
        scheduler.resumeJob(jobKey);
        log.info("定时任务JobName：【" + handlerTriggerDTO.getJobName() + "】恢复成功");
    }

    @Override
    public void rescheduleTrigger(HandlerTriggerDTO handlerTriggerDTO) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(handlerTriggerDTO.getTriggerName(), handlerTriggerDTO.getTriggerGroup());
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(handlerTriggerDTO.getCronExpression());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        // 按新的trigger重新设置job执行，重启触发器
        scheduler.rescheduleJob(triggerKey, trigger);
        log.info("定时任务triggerName：【" + handlerTriggerDTO.getTriggerName() + "】重启或修改成功");
    }

    @Override
    public void modifyTrigger(SaveTriggerDTO saveTriggerDTO) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(saveTriggerDTO.getTriggerName(), saveTriggerDTO.getTriggerGroup());
        JobDetail jobDetail = wrapJobDetail(saveTriggerDTO);
        Trigger trigger = wrapTrigger(saveTriggerDTO, jobDetail);
        scheduler.unscheduleJob(triggerKey);
        scheduler.scheduleJob(jobDetail, trigger);
//        scheduler.rescheduleJob(triggerKey, trigger); 会导致任务类无法修改
        log.info("定时任务triggerName：【" + saveTriggerDTO.getTriggerName() + "】修改成功");
    }

    @Override
    public void deleteTrigger(HandlerTriggerDTO handlerTriggerDTO) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(handlerTriggerDTO.getTriggerName(), handlerTriggerDTO.getTriggerGroup()));
        scheduler.unscheduleJob(TriggerKey.triggerKey(handlerTriggerDTO.getTriggerName(), handlerTriggerDTO.getTriggerGroup()));
        scheduler.deleteJob(JobKey.jobKey(handlerTriggerDTO.getJobName(), handlerTriggerDTO.getJobGroup()));
        log.info("定时任务triggerName：【" + handlerTriggerDTO.getTriggerName() + "】删除成功");
    }
}
