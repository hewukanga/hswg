package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ParkingLotDTO;
import com.jiumai.base.biz.entity.ParkingLot;
import com.jiumai.base.biz.query.ParkingLotQuery;
import com.jiumai.base.biz.vo.ParkingLotVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 停车场(数泊推送) 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ParkingLotService extends IService<ParkingLot> {

    /**
     * 分页查询停车场(数泊推送)
     * @param query
     * @return
     */
    Page<ParkingLotVO> findParkingLotPage(ParkingLotQuery query);

    /**
     * 添加或更新停车场(数泊推送)
     * @param parkingLotDTO
     * @return
     */
    Long saveOrUpdateParkingLot(ParkingLotDTO parkingLotDTO);

    /**
     * 通过id查询成功停车场(数泊推送)详情
     * @param id
     * @return
     */
    ParkingLotVO getParkingLotById(Long id);
}
