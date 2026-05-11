package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ExamineRuleDTO;
import com.jiumai.base.biz.entity.ExamineRule;
import com.jiumai.base.biz.query.ExamineRuleQuery;
import com.jiumai.base.biz.vo.ExamineRuleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 考核规则 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ExamineRuleService extends IService<ExamineRule> {

    /**
     * 分页查询考核规则
     * @param query
     * @return
     */
    Page<ExamineRuleVO> findExamineRulePage(ExamineRuleQuery query);

    /**
     * 添加或更新考核规则
     * @param examineRuleDTO
     * @return
     */
    Long saveOrUpdateExamineRule(ExamineRuleDTO examineRuleDTO);

    /**
     * 通过id查询成功考核规则详情
     * @param id
     * @return
     */
    ExamineRuleVO getExamineRuleById(Long id);
}
