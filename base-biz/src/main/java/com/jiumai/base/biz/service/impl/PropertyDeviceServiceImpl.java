package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PropertyDeviceDTO;
import com.jiumai.base.biz.entity.PropertyDevice;
import com.jiumai.base.biz.mapper.PropertyDeviceMapper;
import com.jiumai.base.biz.query.PropertyDeviceQuery;
import com.jiumai.base.biz.service.PropertyDeviceService;
import com.jiumai.base.biz.vo.PropertyDeviceVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 物业设备表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PropertyDeviceServiceImpl extends ServiceImpl<PropertyDeviceMapper, PropertyDevice> implements PropertyDeviceService {

    @Resource
    private PropertyDeviceMapper propertyDeviceMapper;

    @Override
    public Page<PropertyDeviceVO> findPropertyDevicePage(PropertyDeviceQuery query) {
        Page<PropertyDeviceVO> page = new Page<>(query.getPage(), query.getSize());
        List<PropertyDeviceVO> list = propertyDeviceMapper.findPropertyDevicePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePropertyDevice(PropertyDeviceDTO propertyDeviceDTO) {
        PropertyDevice propertyDevice = new PropertyDevice();

        BeanUtils.copyProperties(propertyDeviceDTO, propertyDevice);

        this.saveOrUpdate(propertyDevice);

        return propertyDevice.getId();
    }

    @Override
    public PropertyDeviceVO getPropertyDeviceById(Long id) {
        PropertyDevice propertyDevice = this.getById(id);
        if (CommonFuntions.isEmptyObject(propertyDevice)) {
            throw new BizException("查询失败，物业设备表不存在");
        }

        PropertyDeviceVO propertyDeviceVO = new PropertyDeviceVO();
        BeanUtils.copyProperties(propertyDevice, propertyDeviceVO);
        return propertyDeviceVO;
    }
}
