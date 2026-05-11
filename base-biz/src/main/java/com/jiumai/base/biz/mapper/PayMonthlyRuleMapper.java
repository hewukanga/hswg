package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PayMonthlyRule;
import com.jiumai.base.biz.query.PayMonthlyRuleQuery;
import com.jiumai.base.biz.vo.PayMonthlyRuleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 包月规则(数泊推送) Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PayMonthlyRuleMapper extends BaseMapper<PayMonthlyRule> {

    /**
     * 分页查询包月规则(数泊推送)
     * @param page
     * @param query
     * @return
     */
    List<PayMonthlyRuleVO> findPayMonthlyRulePage(Page<PayMonthlyRuleVO> page, @Param("query") PayMonthlyRuleQuery query);
}
