package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CamerasLocationDTO;
import com.jiumai.base.biz.entity.CamerasLocation;
import com.jiumai.base.biz.mapper.CamerasLocationMapper;
import com.jiumai.base.biz.query.CamerasLocationQuery;
import com.jiumai.base.biz.service.CamerasLocationService;
import com.jiumai.base.biz.vo.CamerasLocationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 执法仪经纬度记录表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class CamerasLocationServiceImpl extends ServiceImpl<CamerasLocationMapper, CamerasLocation> implements CamerasLocationService {

    @Resource
    private CamerasLocationMapper camerasLocationMapper;

    @Override
    public Page<CamerasLocationVO> findCamerasLocationPage(CamerasLocationQuery query) {
        Page<CamerasLocationVO> page = new Page<>(query.getPage(), query.getSize());
        List<CamerasLocationVO> list = camerasLocationMapper.findCamerasLocationPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public String saveOrUpdateCamerasLocation(CamerasLocationDTO camerasLocationDTO) {
        CamerasLocation camerasLocation = new CamerasLocation();

        BeanUtils.copyProperties(camerasLocationDTO, camerasLocation);

        this.saveOrUpdate(camerasLocation);

        return camerasLocation.getId();
    }

    @Override
    public CamerasLocationVO getCamerasLocationById(String id) {
        CamerasLocation camerasLocation = this.getById(id);
        if (CommonFuntions.isEmptyObject(camerasLocation)) {
            throw new BizException("查询失败，执法仪经纬度记录表不存在");
        }

        CamerasLocationVO camerasLocationVO = new CamerasLocationVO();
        BeanUtils.copyProperties(camerasLocation, camerasLocationVO);
        return camerasLocationVO;
    }
}
