package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.AttendanceRuleDateDTO;
import com.jiumai.base.biz.entity.AttendanceRuleDate;
import com.jiumai.base.biz.query.AttendanceRuleDateQuery;
import com.jiumai.base.biz.vo.AttendanceRuleDateVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 考勤规则日期表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface AttendanceRuleDateService extends IService<AttendanceRuleDate> {

    /**
     * 分页查询考勤规则日期表
     * @param query
     * @return
     */
    Page<AttendanceRuleDateVO> findAttendanceRuleDatePage(AttendanceRuleDateQuery query);

    /**
     * 添加或更新考勤规则日期表
     * @param attendanceRuleDateDTO
     * @return
     */
    Long saveOrUpdateAttendanceRuleDate(AttendanceRuleDateDTO attendanceRuleDateDTO);

    /**
     * 通过id查询成功考勤规则日期表详情
     * @param id
     * @return
     */
    AttendanceRuleDateVO getAttendanceRuleDateById(Long id);
}
