package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.AttendanceRuleOp;
import com.jiumai.base.biz.query.AttendanceRuleOpQuery;
import com.jiumai.base.biz.vo.AttendanceRuleOpVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考勤规则人员表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface AttendanceRuleOpMapper extends BaseMapper<AttendanceRuleOp> {

    /**
     * 分页查询考勤规则人员表
     * @param page
     * @param query
     * @return
     */
    List<AttendanceRuleOpVO> findAttendanceRuleOpPage(Page<AttendanceRuleOpVO> page, @Param("query") AttendanceRuleOpQuery query);
}
