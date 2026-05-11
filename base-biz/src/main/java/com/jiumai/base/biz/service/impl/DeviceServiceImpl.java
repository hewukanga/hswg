package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.DeviceDTO;
import com.jiumai.base.biz.entity.Device;
import com.jiumai.base.biz.mapper.DeviceMapper;
import com.jiumai.base.biz.query.DeviceQuery;
import com.jiumai.base.biz.service.DeviceService;
import com.jiumai.base.biz.vo.DeviceVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 设备表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public Page<DeviceVO> findDevicePage(DeviceQuery query) {
        Page<DeviceVO> page = new Page<>(query.getPage(), query.getSize());
        List<DeviceVO> list = deviceMapper.findDevicePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateDevice(DeviceDTO deviceDTO) {
        Device device = new Device();

        BeanUtils.copyProperties(deviceDTO, device);

        this.saveOrUpdate(device);

        return device.getId();
    }

    @Override
    public DeviceVO getDeviceById(Long id) {
        Device device = this.getById(id);
        if (CommonFuntions.isEmptyObject(device)) {
            throw new BizException("查询失败，设备表不存在");
        }

        DeviceVO deviceVO = new DeviceVO();
        BeanUtils.copyProperties(device, deviceVO);
        return deviceVO;
    }
}
