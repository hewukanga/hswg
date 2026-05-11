package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ExamineTeam;
import com.jiumai.base.biz.query.ExamineTeamQuery;
import com.jiumai.base.biz.vo.ExamineTeamVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考核组 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ExamineTeamMapper extends BaseMapper<ExamineTeam> {

    /**
     * 分页查询考核组
     * @param page
     * @param query
     * @return
     */
    List<ExamineTeamVO> findExamineTeamPage(Page<ExamineTeamVO> page, @Param("query") ExamineTeamQuery query);
}
