package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ExamineTeamDetails;
import com.jiumai.base.biz.query.ExamineTeamDetailsQuery;
import com.jiumai.base.biz.vo.ExamineTeamDetailsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考核组详情 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ExamineTeamDetailsMapper extends BaseMapper<ExamineTeamDetails> {

    /**
     * 分页查询考核组详情
     * @param page
     * @param query
     * @return
     */
    List<ExamineTeamDetailsVO> findExamineTeamDetailsPage(Page<ExamineTeamDetailsVO> page, @Param("query") ExamineTeamDetailsQuery query);
}
