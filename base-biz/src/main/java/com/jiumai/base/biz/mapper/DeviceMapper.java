package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.Device;
import com.jiumai.base.biz.query.DeviceQuery;
import com.jiumai.base.biz.vo.DeviceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 设备表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface DeviceMapper extends BaseMapper<Device> {

    /**
     * 分页查询设备表
     * @param page
     * @param query
     * @return
     */
    List<DeviceVO> findDevicePage(Page<DeviceVO> page, @Param("query") DeviceQuery query);
}
