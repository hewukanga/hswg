package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ScheduleRecordDTO;
import com.jiumai.base.biz.entity.ScheduleRecord;
import com.jiumai.base.biz.query.ScheduleRecordQuery;
import com.jiumai.base.biz.vo.ScheduleRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 排班记录表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ScheduleRecordService extends IService<ScheduleRecord> {

    /**
     * 分页查询排班记录表
     * @param query
     * @return
     */
    Page<ScheduleRecordVO> findScheduleRecordPage(ScheduleRecordQuery query);

    /**
     * 添加或更新排班记录表
     * @param scheduleRecordDTO
     * @return
     */
    Long saveOrUpdateScheduleRecord(ScheduleRecordDTO scheduleRecordDTO);

    /**
     * 通过id查询成功排班记录表详情
     * @param id
     * @return
     */
    ScheduleRecordVO getScheduleRecordById(Long id);

    /**
     * 根据用户ID及月份查询排班信息列表
     * @param opId
     * @param month yyyy-MM
     * @return
     */
    List<ScheduleRecordVO> findScheduleRecordListByOpIdAndMonth(Long opId, String month);
}
