package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.Order;
import com.jiumai.base.biz.query.OrderQuery;
import com.jiumai.base.biz.vo.OrderVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 分页查询订单表
     * @param page
     * @param query
     * @return
     */
    List<OrderVO> findOrderPage(Page<OrderVO> page, @Param("query") OrderQuery query);
}
