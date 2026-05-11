package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.NeighborhoodGoodsDTO;
import com.jiumai.base.biz.entity.NeighborhoodGoods;
import com.jiumai.base.biz.query.NeighborhoodGoodsQuery;
import com.jiumai.base.biz.vo.NeighborhoodGoodsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 邻里商品表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface NeighborhoodGoodsService extends IService<NeighborhoodGoods> {

    /**
     * 分页查询邻里商品表
     * @param query
     * @return
     */
    Page<NeighborhoodGoodsVO> findNeighborhoodGoodsPage(NeighborhoodGoodsQuery query);

    /**
     * 添加或更新邻里商品表
     * @param neighborhoodGoodsDTO
     * @return
     */
    Long saveOrUpdateNeighborhoodGoods(NeighborhoodGoodsDTO neighborhoodGoodsDTO);

    /**
     * 通过id查询成功邻里商品表详情
     * @param id
     * @return
     */
    NeighborhoodGoodsVO getNeighborhoodGoodsById(Long id);
}
