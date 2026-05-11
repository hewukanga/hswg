package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PropertyDeviceRecordDTO;
import com.jiumai.base.biz.entity.PropertyDeviceRecord;
import com.jiumai.base.biz.query.PropertyDeviceRecordQuery;
import com.jiumai.base.biz.vo.PropertyDeviceRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 物业设备领用记录 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PropertyDeviceRecordService extends IService<PropertyDeviceRecord> {

    /**
     * 分页查询物业设备领用记录
     * @param query
     * @return
     */
    Page<PropertyDeviceRecordVO> findPropertyDeviceRecordPage(PropertyDeviceRecordQuery query);

    /**
     * 添加或更新物业设备领用记录
     * @param propertyDeviceRecordDTO
     * @return
     */
    Long saveOrUpdatePropertyDeviceRecord(PropertyDeviceRecordDTO propertyDeviceRecordDTO);

    /**
     * 通过id查询成功物业设备领用记录详情
     * @param id
     * @return
     */
    PropertyDeviceRecordVO getPropertyDeviceRecordById(Long id);
}
