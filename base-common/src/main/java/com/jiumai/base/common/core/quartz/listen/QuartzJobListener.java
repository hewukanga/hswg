package com.jiumai.base.common.core.quartz.listen;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiumai.base.common.core.annotation.Quartz;
import com.jiumai.base.common.core.entity.SmQuartz;
import com.jiumai.base.common.core.entity.SmQuartzRunRecord;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.mapper.QuartzMapper;
import com.jiumai.base.common.core.mapper.QuartzRunRecordMapper;
import com.jiumai.base.common.core.utils.CommonFuntions;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

import javax.management.monitor.CounterMonitor;

@Component
@Slf4j
public class QuartzJobListener implements JobListener {

    private final QuartzRunRecordMapper quartzRunRecordMapper;

    private final QuartzMapper quartzMapper;

    public QuartzJobListener(QuartzRunRecordMapper quartzRunRecordMapper, QuartzMapper quartzMapper) {
        this.quartzRunRecordMapper = quartzRunRecordMapper;
        this.quartzMapper = quartzMapper;
    }
    @Override
    public String getName() {
        return "QuartzJobListener";
    }

    /**
     *
     *Scheduler在JobDetail将要被执行时调用这个方法
     **/
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        //获取到jobGroup，jobName 根据这两个参数查询数据库中定时任务id
        String group = context.getJobDetail().getKey().getGroup();
        String name = context.getJobDetail().getKey().getName();
        SmQuartz smQuartz = quartzMapper.selectOne(new QueryWrapper<SmQuartz>()
                .lambda()
                .eq(SmQuartz::getJobGroup, group)
                .eq(SmQuartz::getJobName, name)
        );
        SmQuartzRunRecord smQuartzRunRecord = new SmQuartzRunRecord();
        smQuartzRunRecord.setJobName(name);
        if(CommonFuntions.isEmptyObject(smQuartz)){
            log.error("jobToBeExecuted方法报错：job组：{}，job名称：{}，定时任务异常，执行过程中，数据库中未找到此定时任务", group, name);
            context.setResult(false);
            smQuartzRunRecord.setExceptionMessage("jobToBeExecuted方法报错：job组：" + group + "，job名称：" + name +"，定时任务异常，执行过程中，数据库中未找到此定时任务");
        }else{
            smQuartzRunRecord.setQuartzId(smQuartz.getId());
            context.setResult(true);
        }
        quartzRunRecordMapper.insert(smQuartzRunRecord);
        context.put("smQuartzRunRecordId", smQuartzRunRecord.getId());
    }
    /**
     *
     *Scheduler在JobDetail即将被执行，但又被TriggerListerner否决时会调用该方法
     **/
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        //获取到jobGroup，jobName 根据这两个参数查询数据库中定时任务id
        String group = jobExecutionContext.getJobDetail().getKey().getGroup();
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        SmQuartz smQuartz = quartzMapper.selectOne(new QueryWrapper<SmQuartz>()
                .lambda()
                .eq(SmQuartz::getJobGroup, group)
                .eq(SmQuartz::getJobName, name)
        );
        if(CommonFuntions.isEmptyObject(smQuartz)){
            log.error("jobExecutionVetoed方法报错：job组：{}，job名称：{}，定时任务异常，执行过程中，数据库中未找到此定时任务", group, name);
        }
        if(CommonFuntions.isNotEmptyObject(jobExecutionContext.get("smQuartzRunRecordId"))){
            SmQuartzRunRecord smQuartzRunRecord = quartzRunRecordMapper.selectById(Long.parseLong(String.valueOf(jobExecutionContext.get("smQuartzRunRecordId"))));
            if(CommonFuntions.isNotEmptyObject(smQuartzRunRecord)) {
                smQuartzRunRecord.setExceptionMessage("job组：" + group + "，job名称：" + name + "，JobDetail即将被执行，但被TriggerListerner否决");
                smQuartzRunRecord.setRunResult(false);
                quartzRunRecordMapper.updateById(smQuartzRunRecord);
            }
        }

    }

    public static void main(String[] args) {
        Object o = 1;
        System.out.println(Long.parseLong(String.valueOf(o)));
        Object s = false;
        System.out.println(Boolean.parseBoolean(String.valueOf(s)));
    }
    /**
     *
     *Scheduler在JobDetail被执行之后调用这个方法
     **/
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String group = context.getJobDetail().getKey().getGroup();
        String name = context.getJobDetail().getKey().getName();
        //校验记录id是否存在
        if(CommonFuntions.isEmptyObject(context.get("smQuartzRunRecordId"))){
            log.error("记录id不存在，job组:{}，job名称:{}", group, name);
            return;
        }
        Long recordId = Long.parseLong(String.valueOf(context.get("smQuartzRunRecordId")));
        SmQuartzRunRecord smQuartzRunRecord = quartzRunRecordMapper.selectById(recordId);
        if(CommonFuntions.isNotEmptyObject(smQuartzRunRecord)) {
            //定时任务执行前未遇到异常情况
            if (Boolean.parseBoolean(String.valueOf(context.getResult()))) {
                //定时器执行过程中未报错
                if(CommonFuntions.isEmptyObject(jobException)){
                    smQuartzRunRecord.setRunResult(true);
                }else{
                    //定时器执行过程中报错
                    smQuartzRunRecord.setRunResult(false);
                    smQuartzRunRecord.setExceptionMessage(jobException.getMessage());
                }
            } else {
                //执行前遇到了异常情况 定时器执行过程中并未报错 正常处理
                if (CommonFuntions.isEmptyObject(jobException)) {
                    smQuartzRunRecord.setRunResult(true);
                } else {
                    //执行前遇到了异常情况 定时器执行过程中也报错
                    smQuartzRunRecord.setRunResult(false);
                    //拼上定时器执行过程中的报错
                    smQuartzRunRecord.setExceptionMessage(smQuartzRunRecord.getExceptionMessage() + "。" + jobException.getMessage());
                }
            }
            quartzRunRecordMapper.updateById(smQuartzRunRecord);
        }else{
            log.error("定时器执行完成，记录未找到，job组:{}，job名称:{}", group, name);
        }
    }
}
