package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.DeviceDTO;
import com.jiumai.base.biz.entity.Device;
import com.jiumai.base.biz.query.DeviceQuery;
import com.jiumai.base.biz.vo.DeviceVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 设备表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface DeviceService extends IService<Device> {

    /**
     * 分页查询设备表
     * @param query
     * @return
     */
    Page<DeviceVO> findDevicePage(DeviceQuery query);

    /**
     * 添加或更新设备表
     * @param deviceDTO
     * @return
     */
    Long saveOrUpdateDevice(DeviceDTO deviceDTO);

    /**
     * 通过id查询成功设备表详情
     * @param id
     * @return
     */
    DeviceVO getDeviceById(Long id);
}
