package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ExamineRuleDTO;
import com.jiumai.base.biz.entity.ExamineRule;
import com.jiumai.base.biz.mapper.ExamineRuleMapper;
import com.jiumai.base.biz.query.ExamineRuleQuery;
import com.jiumai.base.biz.service.ExamineRuleService;
import com.jiumai.base.biz.vo.ExamineRuleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 考核规则 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ExamineRuleServiceImpl extends ServiceImpl<ExamineRuleMapper, ExamineRule> implements ExamineRuleService {

    @Resource
    private ExamineRuleMapper examineRuleMapper;

    @Override
    public Page<ExamineRuleVO> findExamineRulePage(ExamineRuleQuery query) {
        Page<ExamineRuleVO> page = new Page<>(query.getPage(), query.getSize());
        List<ExamineRuleVO> list = examineRuleMapper.findExamineRulePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateExamineRule(ExamineRuleDTO examineRuleDTO) {
        ExamineRule examineRule = new ExamineRule();

        BeanUtils.copyProperties(examineRuleDTO, examineRule);

        this.saveOrUpdate(examineRule);

        return examineRule.getId();
    }

    @Override
    public ExamineRuleVO getExamineRuleById(Long id) {
        ExamineRule examineRule = this.getById(id);
        if (CommonFuntions.isEmptyObject(examineRule)) {
            throw new BizException("查询失败，考核规则不存在");
        }

        ExamineRuleVO examineRuleVO = new ExamineRuleVO();
        BeanUtils.copyProperties(examineRule, examineRuleVO);
        return examineRuleVO;
    }
}
