package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.AttendanceRecord;
import com.jiumai.base.biz.query.AttendanceRecordQuery;
import com.jiumai.base.biz.vo.AttendanceRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考勤记录表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface AttendanceRecordMapper extends BaseMapper<AttendanceRecord> {

    /**
     * 分页查询考勤记录表
     * @param page
     * @param query
     * @return
     */
    List<AttendanceRecordVO> findAttendanceRecordPage(Page<AttendanceRecordVO> page, @Param("query") AttendanceRecordQuery query);
}
