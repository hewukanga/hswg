package com.jiumai.base.common.core.quartz.service;

import com.jiumai.base.common.core.quartz.dto.HandlerTriggerDTO;
import com.jiumai.base.common.core.quartz.dto.SaveTriggerDTO;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * 定时任务服务类
 */
public interface TriggerService {

    /**
     * 创建定时任务
     * @param saveTriggerDTO
     */
    void createTrigger(SaveTriggerDTO saveTriggerDTO) throws SchedulerException;

    /**
     * 判断定时任务是否存在
     * @param triggerName
     * @param triggerGroup
     * @return false不存在 true存在
     * @throws SchedulerException
     */
    Boolean checkTriggerExist(String triggerName, String triggerGroup) throws SchedulerException;

    /**
     * 暂停任务
     * @param handlerTriggerDTO
     * @throws SchedulerException
     */
    void pauseTrigger(HandlerTriggerDTO handlerTriggerDTO) throws SchedulerException;

    /**
     * 恢复任务
     * @param handlerTriggerDTO
     * @throws SchedulerException
     */
    void resumeTrigger(HandlerTriggerDTO handlerTriggerDTO) throws SchedulerException;

    /**
     * 重启任务
     * @param handlerTriggerDTO
     * @throws SchedulerException
     */
    void rescheduleTrigger(HandlerTriggerDTO handlerTriggerDTO) throws SchedulerException;

    /**
     * 修改任务
     * @param saveTriggerDTO
     */
    void modifyTrigger(SaveTriggerDTO saveTriggerDTO) throws SchedulerException;

    /**
     * 删除任务
     * @param handlerTriggerDTO
     * @throws SchedulerException
     */
    void deleteTrigger(HandlerTriggerDTO handlerTriggerDTO) throws SchedulerException;

}
