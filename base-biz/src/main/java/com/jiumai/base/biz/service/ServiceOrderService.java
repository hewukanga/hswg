package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ServiceOrderDTO;
import com.jiumai.base.biz.entity.ServiceOrder;
import com.jiumai.base.biz.query.ServiceOrderQuery;
import com.jiumai.base.biz.vo.ServiceOrderVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务工单表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ServiceOrderService extends IService<ServiceOrder> {

    /**
     * 分页查询服务工单表
     * @param query
     * @return
     */
    Page<ServiceOrderVO> findServiceOrderPage(ServiceOrderQuery query);

    /**
     * 添加或更新服务工单表
     * @param serviceOrderDTO
     * @return
     */
    Long saveOrUpdateServiceOrder(ServiceOrderDTO serviceOrderDTO);

    /**
     * 通过id查询成功服务工单表详情
     * @param id
     * @return
     */
    ServiceOrderVO getServiceOrderById(Long id);
}
