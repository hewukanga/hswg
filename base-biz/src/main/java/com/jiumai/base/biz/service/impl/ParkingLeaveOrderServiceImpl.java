package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ParkingLeaveOrderDTO;
import com.jiumai.base.biz.entity.ParkingLeaveOrder;
import com.jiumai.base.biz.mapper.ParkingLeaveOrderMapper;
import com.jiumai.base.biz.query.ParkingLeaveOrderQuery;
import com.jiumai.base.biz.service.ParkingLeaveOrderService;
import com.jiumai.base.biz.vo.ParkingLeaveOrderVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 停车离场订单表(数泊) 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ParkingLeaveOrderServiceImpl extends ServiceImpl<ParkingLeaveOrderMapper, ParkingLeaveOrder> implements ParkingLeaveOrderService {

    @Resource
    private ParkingLeaveOrderMapper parkingLeaveOrderMapper;

    @Override
    public Page<ParkingLeaveOrderVO> findParkingLeaveOrderPage(ParkingLeaveOrderQuery query) {
        Page<ParkingLeaveOrderVO> page = new Page<>(query.getPage(), query.getSize());
        List<ParkingLeaveOrderVO> list = parkingLeaveOrderMapper.findParkingLeaveOrderPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateParkingLeaveOrder(ParkingLeaveOrderDTO parkingLeaveOrderDTO) {
        ParkingLeaveOrder parkingLeaveOrder = new ParkingLeaveOrder();

        BeanUtils.copyProperties(parkingLeaveOrderDTO, parkingLeaveOrder);

        this.saveOrUpdate(parkingLeaveOrder);

        return parkingLeaveOrder.getId();
    }

    @Override
    public ParkingLeaveOrderVO getParkingLeaveOrderById(Long id) {
        ParkingLeaveOrder parkingLeaveOrder = this.getById(id);
        if (CommonFuntions.isEmptyObject(parkingLeaveOrder)) {
            throw new BizException("查询失败，停车离场订单表(数泊)不存在");
        }

        ParkingLeaveOrderVO parkingLeaveOrderVO = new ParkingLeaveOrderVO();
        BeanUtils.copyProperties(parkingLeaveOrder, parkingLeaveOrderVO);
        return parkingLeaveOrderVO;
    }
}
