package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PropertyDevice;
import com.jiumai.base.biz.query.PropertyDeviceQuery;
import com.jiumai.base.biz.vo.PropertyDeviceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 物业设备表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PropertyDeviceMapper extends BaseMapper<PropertyDevice> {

    /**
     * 分页查询物业设备表
     * @param page
     * @param query
     * @return
     */
    List<PropertyDeviceVO> findPropertyDevicePage(Page<PropertyDeviceVO> page, @Param("query") PropertyDeviceQuery query);
}
