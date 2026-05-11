package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PayMonthlyRuleDTO;
import com.jiumai.base.biz.entity.PayMonthlyRule;
import com.jiumai.base.biz.query.PayMonthlyRuleQuery;
import com.jiumai.base.biz.vo.PayMonthlyRuleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 包月规则(数泊推送) 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PayMonthlyRuleService extends IService<PayMonthlyRule> {

    /**
     * 分页查询包月规则(数泊推送)
     * @param query
     * @return
     */
    Page<PayMonthlyRuleVO> findPayMonthlyRulePage(PayMonthlyRuleQuery query);

    /**
     * 添加或更新包月规则(数泊推送)
     * @param payMonthlyRuleDTO
     * @return
     */
    Long saveOrUpdatePayMonthlyRule(PayMonthlyRuleDTO payMonthlyRuleDTO);

    /**
     * 通过id查询成功包月规则(数泊推送)详情
     * @param id
     * @return
     */
    PayMonthlyRuleVO getPayMonthlyRuleById(Long id);
}
