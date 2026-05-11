package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.IntegralGoodsExchange;
import com.jiumai.base.biz.query.IntegralGoodsExchangeQuery;
import com.jiumai.base.biz.vo.IntegralGoodsExchangeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 积分商品兑换记录 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface IntegralGoodsExchangeMapper extends BaseMapper<IntegralGoodsExchange> {

    /**
     * 分页查询积分商品兑换记录
     * @param page
     * @param query
     * @return
     */
    List<IntegralGoodsExchangeVO> findIntegralGoodsExchangePage(Page<IntegralGoodsExchangeVO> page, @Param("query") IntegralGoodsExchangeQuery query);
}
