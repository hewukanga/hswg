package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ExamineRule;
import com.jiumai.base.biz.query.ExamineRuleQuery;
import com.jiumai.base.biz.vo.ExamineRuleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考核规则 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ExamineRuleMapper extends BaseMapper<ExamineRule> {

    /**
     * 分页查询考核规则
     * @param page
     * @param query
     * @return
     */
    List<ExamineRuleVO> findExamineRulePage(Page<ExamineRuleVO> page, @Param("query") ExamineRuleQuery query);
}
