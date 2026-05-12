package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ScheduleRecordDTO;
import com.jiumai.base.biz.entity.ScheduleRecord;
import com.jiumai.base.biz.mapper.ScheduleRecordMapper;
import com.jiumai.base.biz.query.ScheduleRecordQuery;
import com.jiumai.base.biz.service.ScheduleRecordService;
import com.jiumai.base.biz.vo.ScheduleRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 排班记录表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ScheduleRecordServiceImpl extends ServiceImpl<ScheduleRecordMapper, ScheduleRecord> implements ScheduleRecordService {

    @Resource
    private ScheduleRecordMapper scheduleRecordMapper;

    @Override
    public Page<ScheduleRecordVO> findScheduleRecordPage(ScheduleRecordQuery query) {
        Page<ScheduleRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<ScheduleRecordVO> list = scheduleRecordMapper.findScheduleRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateScheduleRecord(ScheduleRecordDTO scheduleRecordDTO) {
        ScheduleRecord scheduleRecord = new ScheduleRecord();

        BeanUtils.copyProperties(scheduleRecordDTO, scheduleRecord);

        this.saveOrUpdate(scheduleRecord);

        return scheduleRecord.getId();
    }

    @Override
    public ScheduleRecordVO getScheduleRecordById(Long id) {
        ScheduleRecord scheduleRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(scheduleRecord)) {
            throw new BizException("查询失败，排班记录表不存在");
        }

        ScheduleRecordVO scheduleRecordVO = new ScheduleRecordVO();
        BeanUtils.copyProperties(scheduleRecord, scheduleRecordVO);
        return scheduleRecordVO;
    }

    @Override
    public List<ScheduleRecordVO> findScheduleRecordListByOpIdAndMonth(Long opId, String month) {
        YearMonth yearMonth;
        try {
            yearMonth = YearMonth.parse(month);
        } catch (DateTimeParseException e) {
            throw new BizException("查询失败，月份格式错误，格式为yyyy-MM");
        }

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.plusMonths(1).atDay(1);
        List<ScheduleRecord> scheduleRecordList = this.lambdaQuery()
                .eq(ScheduleRecord::getOpId, opId)
                .ge(ScheduleRecord::getScheduleDate, startDate)
                .lt(ScheduleRecord::getScheduleDate, endDate)
                .orderByAsc(ScheduleRecord::getScheduleDate)
                .list();

        return scheduleRecordList.stream().map(scheduleRecord -> {
            ScheduleRecordVO scheduleRecordVO = new ScheduleRecordVO();
            BeanUtils.copyProperties(scheduleRecord, scheduleRecordVO);
            return scheduleRecordVO;
        }).collect(Collectors.toList());
    }
}
