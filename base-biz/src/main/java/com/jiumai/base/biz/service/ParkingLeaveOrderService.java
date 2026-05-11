package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ParkingLeaveOrderDTO;
import com.jiumai.base.biz.entity.ParkingLeaveOrder;
import com.jiumai.base.biz.query.ParkingLeaveOrderQuery;
import com.jiumai.base.biz.vo.ParkingLeaveOrderVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 停车离场订单表(数泊) 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ParkingLeaveOrderService extends IService<ParkingLeaveOrder> {

    /**
     * 分页查询停车离场订单表(数泊)
     * @param query
     * @return
     */
    Page<ParkingLeaveOrderVO> findParkingLeaveOrderPage(ParkingLeaveOrderQuery query);

    /**
     * 添加或更新停车离场订单表(数泊)
     * @param parkingLeaveOrderDTO
     * @return
     */
    Long saveOrUpdateParkingLeaveOrder(ParkingLeaveOrderDTO parkingLeaveOrderDTO);

    /**
     * 通过id查询成功停车离场订单表(数泊)详情
     * @param id
     * @return
     */
    ParkingLeaveOrderVO getParkingLeaveOrderById(Long id);
}
