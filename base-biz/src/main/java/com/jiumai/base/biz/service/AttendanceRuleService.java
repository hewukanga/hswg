package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.AttendanceRuleDTO;
import com.jiumai.base.biz.entity.AttendanceRule;
import com.jiumai.base.biz.query.AttendanceRuleQuery;
import com.jiumai.base.biz.vo.AttendanceRuleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 考勤规则表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface AttendanceRuleService extends IService<AttendanceRule> {

    /**
     * 分页查询考勤规则表
     * @param query
     * @return
     */
    Page<AttendanceRuleVO> findAttendanceRulePage(AttendanceRuleQuery query);

    /**
     * 添加或更新考勤规则表
     * @param attendanceRuleDTO
     * @return
     */
    Long saveOrUpdateAttendanceRule(AttendanceRuleDTO attendanceRuleDTO);

    /**
     * 通过id查询成功考勤规则表详情
     * @param id
     * @return
     */
    AttendanceRuleVO getAttendanceRuleById(Long id);
}
