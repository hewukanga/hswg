package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ServiceOrderDTO;
import com.jiumai.base.biz.entity.ServiceOrder;
import com.jiumai.base.biz.mapper.ServiceOrderMapper;
import com.jiumai.base.biz.query.ServiceOrderQuery;
import com.jiumai.base.biz.service.ServiceOrderService;
import com.jiumai.base.biz.vo.ServiceOrderVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务工单表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ServiceOrderServiceImpl extends ServiceImpl<ServiceOrderMapper, ServiceOrder> implements ServiceOrderService {

    @Resource
    private ServiceOrderMapper serviceOrderMapper;

    @Override
    public Page<ServiceOrderVO> findServiceOrderPage(ServiceOrderQuery query) {
        Page<ServiceOrderVO> page = new Page<>(query.getPage(), query.getSize());
        List<ServiceOrderVO> list = serviceOrderMapper.findServiceOrderPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateServiceOrder(ServiceOrderDTO serviceOrderDTO) {
        ServiceOrder serviceOrder = new ServiceOrder();

        BeanUtils.copyProperties(serviceOrderDTO, serviceOrder);

        this.saveOrUpdate(serviceOrder);

        return serviceOrder.getId();
    }

    @Override
    public ServiceOrderVO getServiceOrderById(Long id) {
        ServiceOrder serviceOrder = this.getById(id);
        if (CommonFuntions.isEmptyObject(serviceOrder)) {
            throw new BizException("查询失败，服务工单表不存在");
        }

        ServiceOrderVO serviceOrderVO = new ServiceOrderVO();
        BeanUtils.copyProperties(serviceOrder, serviceOrderVO);
        return serviceOrderVO;
    }
}
