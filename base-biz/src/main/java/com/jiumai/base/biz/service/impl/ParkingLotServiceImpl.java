package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ParkingLotDTO;
import com.jiumai.base.biz.entity.ParkingLot;
import com.jiumai.base.biz.mapper.ParkingLotMapper;
import com.jiumai.base.biz.query.ParkingLotQuery;
import com.jiumai.base.biz.service.ParkingLotService;
import com.jiumai.base.biz.vo.ParkingLotVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 停车场(数泊推送) 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ParkingLotServiceImpl extends ServiceImpl<ParkingLotMapper, ParkingLot> implements ParkingLotService {

    @Resource
    private ParkingLotMapper parkingLotMapper;

    @Override
    public Page<ParkingLotVO> findParkingLotPage(ParkingLotQuery query) {
        Page<ParkingLotVO> page = new Page<>(query.getPage(), query.getSize());
        List<ParkingLotVO> list = parkingLotMapper.findParkingLotPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateParkingLot(ParkingLotDTO parkingLotDTO) {
        ParkingLot parkingLot = new ParkingLot();

        BeanUtils.copyProperties(parkingLotDTO, parkingLot);

        this.saveOrUpdate(parkingLot);

        return parkingLot.getId();
    }

    @Override
    public ParkingLotVO getParkingLotById(Long id) {
        ParkingLot parkingLot = this.getById(id);
        if (CommonFuntions.isEmptyObject(parkingLot)) {
            throw new BizException("查询失败，停车场(数泊推送)不存在");
        }

        ParkingLotVO parkingLotVO = new ParkingLotVO();
        BeanUtils.copyProperties(parkingLot, parkingLotVO);
        return parkingLotVO;
    }
}
