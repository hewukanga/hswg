package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.AttendanceRule;
import com.jiumai.base.biz.query.AttendanceRuleQuery;
import com.jiumai.base.biz.vo.AttendanceRuleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考勤规则表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface AttendanceRuleMapper extends BaseMapper<AttendanceRule> {

    /**
     * 分页查询考勤规则表
     * @param page
     * @param query
     * @return
     */
    List<AttendanceRuleVO> findAttendanceRulePage(Page<AttendanceRuleVO> page, @Param("query") AttendanceRuleQuery query);
}
