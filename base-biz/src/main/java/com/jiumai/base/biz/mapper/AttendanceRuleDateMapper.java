package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.AttendanceRuleDate;
import com.jiumai.base.biz.query.AttendanceRuleDateQuery;
import com.jiumai.base.biz.vo.AttendanceRuleDateVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考勤规则日期表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface AttendanceRuleDateMapper extends BaseMapper<AttendanceRuleDate> {

    /**
     * 分页查询考勤规则日期表
     * @param page
     * @param query
     * @return
     */
    List<AttendanceRuleDateVO> findAttendanceRuleDatePage(Page<AttendanceRuleDateVO> page, @Param("query") AttendanceRuleDateQuery query);
}
