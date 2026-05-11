package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PropertyWorkstationDTO;
import com.jiumai.base.biz.entity.PropertyWorkstation;
import com.jiumai.base.biz.mapper.PropertyWorkstationMapper;
import com.jiumai.base.biz.query.PropertyWorkstationQuery;
import com.jiumai.base.biz.service.PropertyWorkstationService;
import com.jiumai.base.biz.vo.PropertyWorkstationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 物业工作站 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PropertyWorkstationServiceImpl extends ServiceImpl<PropertyWorkstationMapper, PropertyWorkstation> implements PropertyWorkstationService {

    @Resource
    private PropertyWorkstationMapper propertyWorkstationMapper;

    @Override
    public Page<PropertyWorkstationVO> findPropertyWorkstationPage(PropertyWorkstationQuery query) {
        Page<PropertyWorkstationVO> page = new Page<>(query.getPage(), query.getSize());
        List<PropertyWorkstationVO> list = propertyWorkstationMapper.findPropertyWorkstationPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePropertyWorkstation(PropertyWorkstationDTO propertyWorkstationDTO) {
        PropertyWorkstation propertyWorkstation = new PropertyWorkstation();

        BeanUtils.copyProperties(propertyWorkstationDTO, propertyWorkstation);

        this.saveOrUpdate(propertyWorkstation);

        return propertyWorkstation.getId();
    }

    @Override
    public PropertyWorkstationVO getPropertyWorkstationById(Long id) {
        PropertyWorkstation propertyWorkstation = this.getById(id);
        if (CommonFuntions.isEmptyObject(propertyWorkstation)) {
            throw new BizException("查询失败，物业工作站不存在");
        }

        PropertyWorkstationVO propertyWorkstationVO = new PropertyWorkstationVO();
        BeanUtils.copyProperties(propertyWorkstation, propertyWorkstationVO);
        return propertyWorkstationVO;
    }
}
