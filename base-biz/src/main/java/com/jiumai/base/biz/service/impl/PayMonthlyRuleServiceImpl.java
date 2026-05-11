package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PayMonthlyRuleDTO;
import com.jiumai.base.biz.entity.PayMonthlyRule;
import com.jiumai.base.biz.mapper.PayMonthlyRuleMapper;
import com.jiumai.base.biz.query.PayMonthlyRuleQuery;
import com.jiumai.base.biz.service.PayMonthlyRuleService;
import com.jiumai.base.biz.vo.PayMonthlyRuleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 包月规则(数泊推送) 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PayMonthlyRuleServiceImpl extends ServiceImpl<PayMonthlyRuleMapper, PayMonthlyRule> implements PayMonthlyRuleService {

    @Resource
    private PayMonthlyRuleMapper payMonthlyRuleMapper;

    @Override
    public Page<PayMonthlyRuleVO> findPayMonthlyRulePage(PayMonthlyRuleQuery query) {
        Page<PayMonthlyRuleVO> page = new Page<>(query.getPage(), query.getSize());
        List<PayMonthlyRuleVO> list = payMonthlyRuleMapper.findPayMonthlyRulePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePayMonthlyRule(PayMonthlyRuleDTO payMonthlyRuleDTO) {
        PayMonthlyRule payMonthlyRule = new PayMonthlyRule();

        BeanUtils.copyProperties(payMonthlyRuleDTO, payMonthlyRule);

        this.saveOrUpdate(payMonthlyRule);

        return payMonthlyRule.getId();
    }

    @Override
    public PayMonthlyRuleVO getPayMonthlyRuleById(Long id) {
        PayMonthlyRule payMonthlyRule = this.getById(id);
        if (CommonFuntions.isEmptyObject(payMonthlyRule)) {
            throw new BizException("查询失败，包月规则(数泊推送)不存在");
        }

        PayMonthlyRuleVO payMonthlyRuleVO = new PayMonthlyRuleVO();
        BeanUtils.copyProperties(payMonthlyRule, payMonthlyRuleVO);
        return payMonthlyRuleVO;
    }
}
