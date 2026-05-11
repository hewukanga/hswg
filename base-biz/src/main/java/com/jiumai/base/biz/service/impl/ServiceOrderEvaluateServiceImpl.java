package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ServiceOrderEvaluateDTO;
import com.jiumai.base.biz.entity.ServiceOrderEvaluate;
import com.jiumai.base.biz.mapper.ServiceOrderEvaluateMapper;
import com.jiumai.base.biz.query.ServiceOrderEvaluateQuery;
import com.jiumai.base.biz.service.ServiceOrderEvaluateService;
import com.jiumai.base.biz.vo.ServiceOrderEvaluateVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务工单评价表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ServiceOrderEvaluateServiceImpl extends ServiceImpl<ServiceOrderEvaluateMapper, ServiceOrderEvaluate> implements ServiceOrderEvaluateService {

    @Resource
    private ServiceOrderEvaluateMapper serviceOrderEvaluateMapper;

    @Override
    public Page<ServiceOrderEvaluateVO> findServiceOrderEvaluatePage(ServiceOrderEvaluateQuery query) {
        Page<ServiceOrderEvaluateVO> page = new Page<>(query.getPage(), query.getSize());
        List<ServiceOrderEvaluateVO> list = serviceOrderEvaluateMapper.findServiceOrderEvaluatePage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateServiceOrderEvaluate(ServiceOrderEvaluateDTO serviceOrderEvaluateDTO) {
        ServiceOrderEvaluate serviceOrderEvaluate = new ServiceOrderEvaluate();

        BeanUtils.copyProperties(serviceOrderEvaluateDTO, serviceOrderEvaluate);

        this.saveOrUpdate(serviceOrderEvaluate);

        return serviceOrderEvaluate.getId();
    }

    @Override
    public ServiceOrderEvaluateVO getServiceOrderEvaluateById(Long id) {
        ServiceOrderEvaluate serviceOrderEvaluate = this.getById(id);
        if (CommonFuntions.isEmptyObject(serviceOrderEvaluate)) {
            throw new BizException("查询失败，服务工单评价表不存在");
        }

        ServiceOrderEvaluateVO serviceOrderEvaluateVO = new ServiceOrderEvaluateVO();
        BeanUtils.copyProperties(serviceOrderEvaluate, serviceOrderEvaluateVO);
        return serviceOrderEvaluateVO;
    }
}
