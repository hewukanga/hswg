package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.OrderDTO;
import com.jiumai.base.biz.entity.Order;
import com.jiumai.base.biz.query.OrderQuery;
import com.jiumai.base.biz.vo.OrderVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OrderService extends IService<Order> {

    /**
     * 分页查询订单表
     * @param query
     * @return
     */
    Page<OrderVO> findOrderPage(OrderQuery query);

    /**
     * 添加或更新订单表
     * @param orderDTO
     * @return
     */
    Long saveOrUpdateOrder(OrderDTO orderDTO);

    /**
     * 通过id查询成功订单表详情
     * @param id
     * @return
     */
    OrderVO getOrderById(Long id);
}
