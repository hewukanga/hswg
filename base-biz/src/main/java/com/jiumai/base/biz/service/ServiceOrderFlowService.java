package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ServiceOrderFlowDTO;
import com.jiumai.base.biz.entity.ServiceOrderFlow;
import com.jiumai.base.biz.query.ServiceOrderFlowQuery;
import com.jiumai.base.biz.vo.ServiceOrderFlowVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务工单处理流程表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ServiceOrderFlowService extends IService<ServiceOrderFlow> {

    /**
     * 分页查询服务工单处理流程表
     * @param query
     * @return
     */
    Page<ServiceOrderFlowVO> findServiceOrderFlowPage(ServiceOrderFlowQuery query);

    /**
     * 添加或更新服务工单处理流程表
     * @param serviceOrderFlowDTO
     * @return
     */
    Long saveOrUpdateServiceOrderFlow(ServiceOrderFlowDTO serviceOrderFlowDTO);

    /**
     * 通过id查询成功服务工单处理流程表详情
     * @param id
     * @return
     */
    ServiceOrderFlowVO getServiceOrderFlowById(Long id);
}
