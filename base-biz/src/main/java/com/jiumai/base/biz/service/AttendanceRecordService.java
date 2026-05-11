package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.AttendanceRecordDTO;
import com.jiumai.base.biz.entity.AttendanceRecord;
import com.jiumai.base.biz.query.AttendanceRecordQuery;
import com.jiumai.base.biz.vo.AttendanceRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 考勤记录表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface AttendanceRecordService extends IService<AttendanceRecord> {

    /**
     * 分页查询考勤记录表
     * @param query
     * @return
     */
    Page<AttendanceRecordVO> findAttendanceRecordPage(AttendanceRecordQuery query);

    /**
     * 添加或更新考勤记录表
     * @param attendanceRecordDTO
     * @return
     */
    Long saveOrUpdateAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO);

    /**
     * 通过id查询成功考勤记录表详情
     * @param id
     * @return
     */
    AttendanceRecordVO getAttendanceRecordById(Long id);
}
