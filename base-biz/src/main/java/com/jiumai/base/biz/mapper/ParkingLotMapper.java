package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ParkingLot;
import com.jiumai.base.biz.query.ParkingLotQuery;
import com.jiumai.base.biz.vo.ParkingLotVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 停车场(数泊推送) Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ParkingLotMapper extends BaseMapper<ParkingLot> {

    /**
     * 分页查询停车场(数泊推送)
     * @param page
     * @param query
     * @return
     */
    List<ParkingLotVO> findParkingLotPage(Page<ParkingLotVO> page, @Param("query") ParkingLotQuery query);
}
