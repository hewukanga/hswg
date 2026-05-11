package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ParkingLeaveOrder;
import com.jiumai.base.biz.query.ParkingLeaveOrderQuery;
import com.jiumai.base.biz.vo.ParkingLeaveOrderVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 停车离场订单表(数泊) Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ParkingLeaveOrderMapper extends BaseMapper<ParkingLeaveOrder> {

    /**
     * 分页查询停车离场订单表(数泊)
     * @param page
     * @param query
     * @return
     */
    List<ParkingLeaveOrderVO> findParkingLeaveOrderPage(Page<ParkingLeaveOrderVO> page, @Param("query") ParkingLeaveOrderQuery query);
}
