package com.jiumai.base.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.PageUtils;
import com.jiumai.base.sm.entity.SmQuartzRunRecord;
import com.jiumai.base.sm.mapper.SmQuartzRunRecordMapper;
import com.jiumai.base.sm.query.QuartzRecordQuery;
import com.jiumai.base.sm.service.SmQuartzRunRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 定时任务执行记录 服务实现类
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-03
 */
@Service
@Slf4j
public class SmQuartzRunRecordServiceImpl extends ServiceImpl<SmQuartzRunRecordMapper, SmQuartzRunRecord> implements SmQuartzRunRecordService {

    @Resource
    private SmQuartzRunRecordMapper smQuartzRunRecordMapper;

    @Override
    public IPage<SmQuartzRunRecord> quartzRunRecordPaging(QuartzRecordQuery quartzRecordQuery) {
        PageUtils<SmQuartzRunRecord> pageUtils = new PageUtils<>();
        Page<SmQuartzRunRecord> page = pageUtils.getPageByPageParam(quartzRecordQuery);
        smQuartzRunRecordMapper.selectPage(page, new QueryWrapper<SmQuartzRunRecord>()
                .lambda()
                .eq(SmQuartzRunRecord::getQuartzId, quartzRecordQuery.getQuartzId())
                .eq(SmQuartzRunRecord::getRunResult, quartzRecordQuery.getRunResult())
                .between(CommonFuntions.isNotEmptyObject(quartzRecordQuery.getStartTime()) && CommonFuntions.isNotEmptyObject(quartzRecordQuery.getEndTime()),
                        SmQuartzRunRecord::getCreateDate, quartzRecordQuery.getStartTime(), quartzRecordQuery.getEndTime())
                .orderByDesc(SmQuartzRunRecord::getCreateDate)
        );
        return page;
    }
}
