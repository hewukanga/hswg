package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.TemporaryParking;
import com.jiumai.base.biz.query.TemporaryParkingQuery;
import com.jiumai.base.biz.vo.TemporaryParkingVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 临停车(数泊推送) Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface TemporaryParkingMapper extends BaseMapper<TemporaryParking> {

    /**
     * 分页查询临停车(数泊推送)
     * @param page
     * @param query
     * @return
     */
    List<TemporaryParkingVO> findTemporaryParkingPage(Page<TemporaryParkingVO> page, @Param("query") TemporaryParkingQuery query);
}
