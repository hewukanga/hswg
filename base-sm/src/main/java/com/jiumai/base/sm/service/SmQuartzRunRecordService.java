package com.jiumai.base.sm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.sm.entity.SmQuartzRunRecord;
import com.jiumai.base.sm.query.QuartzRecordQuery;

/**
 * <p>
 * 定时任务执行记录 服务类
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-03
 */
public interface SmQuartzRunRecordService extends IService<SmQuartzRunRecord> {

    /**
     * 定时任务执行记录分页查询
     * @param quartzRecordQuery
     * @return
     */
    IPage<SmQuartzRunRecord> quartzRunRecordPaging(QuartzRecordQuery quartzRecordQuery);
}
