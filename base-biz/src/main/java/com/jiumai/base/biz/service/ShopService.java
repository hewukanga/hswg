package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ShopDTO;
import com.jiumai.base.biz.entity.Shop;
import com.jiumai.base.biz.query.ShopQuery;
import com.jiumai.base.biz.vo.ShopVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 店铺信息表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ShopService extends IService<Shop> {

    /**
     * 分页查询店铺信息表
     * @param query
     * @return
     */
    Page<ShopVO> findShopPage(ShopQuery query);

    /**
     * 添加或更新店铺信息表
     * @param shopDTO
     * @return
     */
    Long saveOrUpdateShop(ShopDTO shopDTO);

    /**
     * 通过id查询成功店铺信息表详情
     * @param id
     * @return
     */
    ShopVO getShopById(Long id);
}
