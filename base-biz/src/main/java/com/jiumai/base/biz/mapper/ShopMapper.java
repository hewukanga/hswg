package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.Shop;
import com.jiumai.base.biz.query.ShopQuery;
import com.jiumai.base.biz.vo.ShopVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 店铺信息表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ShopMapper extends BaseMapper<Shop> {

    /**
     * 分页查询店铺信息表
     * @param page
     * @param query
     * @return
     */
    List<ShopVO> findShopPage(Page<ShopVO> page, @Param("query") ShopQuery query);
}
