package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.NeighborhoodGoods;
import com.jiumai.base.biz.query.NeighborhoodGoodsQuery;
import com.jiumai.base.biz.vo.NeighborhoodGoodsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 邻里商品表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface NeighborhoodGoodsMapper extends BaseMapper<NeighborhoodGoods> {

    /**
     * 分页查询邻里商品表
     * @param page
     * @param query
     * @return
     */
    List<NeighborhoodGoodsVO> findNeighborhoodGoodsPage(Page<NeighborhoodGoodsVO> page, @Param("query") NeighborhoodGoodsQuery query);
}
