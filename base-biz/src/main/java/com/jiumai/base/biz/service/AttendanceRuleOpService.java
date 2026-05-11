package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.AttendanceRuleOpDTO;
import com.jiumai.base.biz.entity.AttendanceRuleOp;
import com.jiumai.base.biz.query.AttendanceRuleOpQuery;
import com.jiumai.base.biz.vo.AttendanceRuleOpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 考勤规则人员表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface AttendanceRuleOpService extends IService<AttendanceRuleOp> {

    /**
     * 分页查询考勤规则人员表
     * @param query
     * @return
     */
    Page<AttendanceRuleOpVO> findAttendanceRuleOpPage(AttendanceRuleOpQuery query);

    /**
     * 添加或更新考勤规则人员表
     * @param attendanceRuleOpDTO
     * @return
     */
    Long saveOrUpdateAttendanceRuleOp(AttendanceRuleOpDTO attendanceRuleOpDTO);

    /**
     * 通过id查询成功考勤规则人员表详情
     * @param id
     * @return
     */
    AttendanceRuleOpVO getAttendanceRuleOpById(Long id);
}
