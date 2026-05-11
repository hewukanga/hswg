package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PropertyAreaDTO;
import com.jiumai.base.biz.entity.PropertyArea;
import com.jiumai.base.biz.mapper.PropertyAreaMapper;
import com.jiumai.base.biz.query.PropertyAreaQuery;
import com.jiumai.base.biz.service.PropertyAreaService;
import com.jiumai.base.biz.vo.PropertyAreaVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 物业区域表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class PropertyAreaServiceImpl extends ServiceImpl<PropertyAreaMapper, PropertyArea> implements PropertyAreaService {

    @Resource
    private PropertyAreaMapper propertyAreaMapper;

    @Override
    public Page<PropertyAreaVO> findPropertyAreaPage(PropertyAreaQuery query) {
        Page<PropertyAreaVO> page = new Page<>(query.getPage(), query.getSize());
        List<PropertyAreaVO> list = propertyAreaMapper.findPropertyAreaPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdatePropertyArea(PropertyAreaDTO propertyAreaDTO) {
        PropertyArea propertyArea = new PropertyArea();

        BeanUtils.copyProperties(propertyAreaDTO, propertyArea);

        this.saveOrUpdate(propertyArea);

        return propertyArea.getId();
    }

    @Override
    public PropertyAreaVO getPropertyAreaById(Long id) {
        PropertyArea propertyArea = this.getById(id);
        if (CommonFuntions.isEmptyObject(propertyArea)) {
            throw new BizException("查询失败，物业区域表不存在");
        }

        PropertyAreaVO propertyAreaVO = new PropertyAreaVO();
        BeanUtils.copyProperties(propertyArea, propertyAreaVO);
        return propertyAreaVO;
    }
}
