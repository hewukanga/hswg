package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.IntegralGoodsDTO;
import com.jiumai.base.biz.entity.IntegralGoods;
import com.jiumai.base.biz.query.IntegralGoodsQuery;
import com.jiumai.base.biz.vo.IntegralGoodsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 积分商品 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface IntegralGoodsService extends IService<IntegralGoods> {

    /**
     * 分页查询积分商品
     * @param query
     * @return
     */
    Page<IntegralGoodsVO> findIntegralGoodsPage(IntegralGoodsQuery query);

    /**
     * 添加或更新积分商品
     * @param integralGoodsDTO
     * @return
     */
    Long saveOrUpdateIntegralGoods(IntegralGoodsDTO integralGoodsDTO);

    /**
     * 通过id查询成功积分商品详情
     * @param id
     * @return
     */
    IntegralGoodsVO getIntegralGoodsById(Long id);
}
