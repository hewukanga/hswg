package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.IntegralGoodsExchangeDTO;
import com.jiumai.base.biz.entity.IntegralGoodsExchange;
import com.jiumai.base.biz.query.IntegralGoodsExchangeQuery;
import com.jiumai.base.biz.vo.IntegralGoodsExchangeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 积分商品兑换记录 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface IntegralGoodsExchangeService extends IService<IntegralGoodsExchange> {

    /**
     * 分页查询积分商品兑换记录
     * @param query
     * @return
     */
    Page<IntegralGoodsExchangeVO> findIntegralGoodsExchangePage(IntegralGoodsExchangeQuery query);

    /**
     * 添加或更新积分商品兑换记录
     * @param integralGoodsExchangeDTO
     * @return
     */
    Long saveOrUpdateIntegralGoodsExchange(IntegralGoodsExchangeDTO integralGoodsExchangeDTO);

    /**
     * 通过id查询成功积分商品兑换记录详情
     * @param id
     * @return
     */
    IntegralGoodsExchangeVO getIntegralGoodsExchangeById(Long id);
}
