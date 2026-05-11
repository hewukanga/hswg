package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PropertyDeviceRecordDTO;
import com.jiumai.base.biz.entity.PropertyDeviceRecord;
import com.jiumai.base.biz.mapper.PropertyDeviceRecordMapper;
import com.jiumai.base.biz.query.PropertyDeviceRecordQuery;
import com.jiumai.base.biz.service.PropertyDeviceRecordService;
import com.jiumai.base.biz.vo.PropertyDeviceRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 物业设备领用记录 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PropertyDeviceRecordServiceImpl extends ServiceImpl<PropertyDeviceRecordMapper, PropertyDeviceRecord> implements PropertyDeviceRecordService {

    @Resource
    private PropertyDeviceRecordMapper propertyDeviceRecordMapper;

    @Override
    public Page<PropertyDeviceRecordVO> findPropertyDeviceRecordPage(PropertyDeviceRecordQuery query) {
        Page<PropertyDeviceRecordVO> page = new Page<>(query.getPage(), query.getSize());
        List<PropertyDeviceRecordVO> list = propertyDeviceRecordMapper.findPropertyDeviceRecordPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePropertyDeviceRecord(PropertyDeviceRecordDTO propertyDeviceRecordDTO) {
        PropertyDeviceRecord propertyDeviceRecord = new PropertyDeviceRecord();

        BeanUtils.copyProperties(propertyDeviceRecordDTO, propertyDeviceRecord);

        this.saveOrUpdate(propertyDeviceRecord);

        return propertyDeviceRecord.getId();
    }

    @Override
    public PropertyDeviceRecordVO getPropertyDeviceRecordById(Long id) {
        PropertyDeviceRecord propertyDeviceRecord = this.getById(id);
        if (CommonFuntions.isEmptyObject(propertyDeviceRecord)) {
            throw new BizException("查询失败，物业设备领用记录不存在");
        }

        PropertyDeviceRecordVO propertyDeviceRecordVO = new PropertyDeviceRecordVO();
        BeanUtils.copyProperties(propertyDeviceRecord, propertyDeviceRecordVO);
        return propertyDeviceRecordVO;
    }
}
