package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.HousePropertyDTO;
import com.jiumai.base.biz.entity.HouseProperty;
import com.jiumai.base.biz.mapper.HousePropertyMapper;
import com.jiumai.base.biz.query.HousePropertyQuery;
import com.jiumai.base.biz.service.HousePropertyService;
import com.jiumai.base.biz.vo.HousePropertyVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 房产管理 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class HousePropertyServiceImpl extends ServiceImpl<HousePropertyMapper, HouseProperty> implements HousePropertyService {

    @Resource
    private HousePropertyMapper housePropertyMapper;

    @Override
    public Page<HousePropertyVO> findHousePropertyPage(HousePropertyQuery query) {
        Page<HousePropertyVO> page = new Page<>(query.getPage(), query.getSize());
        List<HousePropertyVO> list = housePropertyMapper.findHousePropertyPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateHouseProperty(HousePropertyDTO housePropertyDTO) {
        HouseProperty houseProperty = new HouseProperty();

        BeanUtils.copyProperties(housePropertyDTO, houseProperty);

        this.saveOrUpdate(houseProperty);

        return houseProperty.getId();
    }

    @Override
    public HousePropertyVO getHousePropertyById(Long id) {
        HouseProperty houseProperty = this.getById(id);
        if (CommonFuntions.isEmptyObject(houseProperty)) {
            throw new BizException("查询失败，房产管理不存在");
        }

        HousePropertyVO housePropertyVO = new HousePropertyVO();
        BeanUtils.copyProperties(houseProperty, housePropertyVO);
        return housePropertyVO;
    }
}
