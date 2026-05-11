package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PropertyDeviceDTO;
import com.jiumai.base.biz.entity.PropertyDevice;
import com.jiumai.base.biz.query.PropertyDeviceQuery;
import com.jiumai.base.biz.vo.PropertyDeviceVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 物业设备表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PropertyDeviceService extends IService<PropertyDevice> {

    /**
     * 分页查询物业设备表
     * @param query
     * @return
     */
    Page<PropertyDeviceVO> findPropertyDevicePage(PropertyDeviceQuery query);

    /**
     * 添加或更新物业设备表
     * @param propertyDeviceDTO
     * @return
     */
    Long saveOrUpdatePropertyDevice(PropertyDeviceDTO propertyDeviceDTO);

    /**
     * 通过id查询成功物业设备表详情
     * @param id
     * @return
     */
    PropertyDeviceVO getPropertyDeviceById(Long id);
}
