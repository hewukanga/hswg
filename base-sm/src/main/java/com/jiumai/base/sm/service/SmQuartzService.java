package com.jiumai.base.sm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.sm.dto.SmQuartzDTO;
import com.jiumai.base.sm.entity.SmOpLog;
import com.jiumai.base.sm.entity.SmQuartz;
import com.jiumai.base.sm.query.QuartzQuery;
import org.quartz.SchedulerException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-02
 */
public interface SmQuartzService extends IService<SmQuartz> {

    /**
     * 定时任务分页列表
     * @param quartzQuery
     * @return
     */
    IPage<SmQuartzDTO> quartzPaging(QuartzQuery quartzQuery);

    /**
     * 通用分页查询定时任务
     * @param query
     * @return
     */
    Page<SmQuartz> pageQuartzByQuery(QuartzQuery query);

    /**
     * 保存定时任务
     * @param smQuartzDTO
     */
    void saveQuartz(SmQuartzDTO smQuartzDTO) throws ClassNotFoundException, SchedulerException;

    /**
     * 判断定时任务是否存在
     * @param triggerName
     * @param triggerGroup
     */
    Boolean checkTriggerExist(String triggerName, String triggerGroup) throws SchedulerException;

    /**
     * 暂停定时任务
     * @param smQuartzDTO
     */
    void pauseQuartz(SmQuartzDTO smQuartzDTO) throws SchedulerException;

    /**
     * 恢复定时任务
     * @param smQuartzDTO
     */
    void resumeQuartz(SmQuartzDTO smQuartzDTO) throws SchedulerException;

    /**
     * 修改定时任务
     * @param smQuartzDTO
     */
    void updateQuartz(SmQuartzDTO smQuartzDTO) throws SchedulerException, ClassNotFoundException;

    /**
     * 删除定时任务
     * @param smQuartzDTO
     */
    void deleteQuartz(SmQuartzDTO smQuartzDTO) throws SchedulerException;
}
