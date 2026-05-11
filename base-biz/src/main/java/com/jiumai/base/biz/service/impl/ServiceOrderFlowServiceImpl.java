package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ServiceOrderFlowDTO;
import com.jiumai.base.biz.entity.ServiceOrderFlow;
import com.jiumai.base.biz.mapper.ServiceOrderFlowMapper;
import com.jiumai.base.biz.query.ServiceOrderFlowQuery;
import com.jiumai.base.biz.service.ServiceOrderFlowService;
import com.jiumai.base.biz.vo.ServiceOrderFlowVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务工单处理流程表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class ServiceOrderFlowServiceImpl extends ServiceImpl<ServiceOrderFlowMapper, ServiceOrderFlow> implements ServiceOrderFlowService {

    @Resource
    private ServiceOrderFlowMapper serviceOrderFlowMapper;

    @Override
    public Page<ServiceOrderFlowVO> findServiceOrderFlowPage(ServiceOrderFlowQuery query) {
        Page<ServiceOrderFlowVO> page = new Page<>(query.getPage(), query.getSize());
        List<ServiceOrderFlowVO> list = serviceOrderFlowMapper.findServiceOrderFlowPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateServiceOrderFlow(ServiceOrderFlowDTO serviceOrderFlowDTO) {
        ServiceOrderFlow serviceOrderFlow = new ServiceOrderFlow();

        BeanUtils.copyProperties(serviceOrderFlowDTO, serviceOrderFlow);

        this.saveOrUpdate(serviceOrderFlow);

        return serviceOrderFlow.getId();
    }

    @Override
    public ServiceOrderFlowVO getServiceOrderFlowById(Long id) {
        ServiceOrderFlow serviceOrderFlow = this.getById(id);
        if (CommonFuntions.isEmptyObject(serviceOrderFlow)) {
            throw new BizException("查询失败，服务工单处理流程表不存在");
        }

        ServiceOrderFlowVO serviceOrderFlowVO = new ServiceOrderFlowVO();
        BeanUtils.copyProperties(serviceOrderFlow, serviceOrderFlowVO);
        return serviceOrderFlowVO;
    }
}
