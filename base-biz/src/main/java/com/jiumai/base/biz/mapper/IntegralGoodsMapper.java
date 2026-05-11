package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.IntegralGoods;
import com.jiumai.base.biz.query.IntegralGoodsQuery;
import com.jiumai.base.biz.vo.IntegralGoodsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 积分商品 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface IntegralGoodsMapper extends BaseMapper<IntegralGoods> {

    /**
     * 分页查询积分商品
     * @param page
     * @param query
     * @return
     */
    List<IntegralGoodsVO> findIntegralGoodsPage(Page<IntegralGoodsVO> page, @Param("query") IntegralGoodsQuery query);
}
