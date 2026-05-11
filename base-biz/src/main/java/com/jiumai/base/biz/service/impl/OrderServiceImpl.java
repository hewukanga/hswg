package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OrderDTO;
import com.jiumai.base.biz.entity.Order;
import com.jiumai.base.biz.mapper.OrderMapper;
import com.jiumai.base.biz.query.OrderQuery;
import com.jiumai.base.biz.service.OrderService;
import com.jiumai.base.biz.vo.OrderVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public Page<OrderVO> findOrderPage(OrderQuery query) {
        Page<OrderVO> page = new Page<>(query.getPage(), query.getSize());
        List<OrderVO> list = orderMapper.findOrderPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateOrder(OrderDTO orderDTO) {
        Order order = new Order();

        BeanUtils.copyProperties(orderDTO, order);

        this.saveOrUpdate(order);

        return order.getId();
    }

    @Override
    public OrderVO getOrderById(Long id) {
        Order order = this.getById(id);
        if (CommonFuntions.isEmptyObject(order)) {
            throw new BizException("查询失败，订单表不存在");
        }

        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        return orderVO;
    }
}
