package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ServiceOrderEvaluateDTO;
import com.jiumai.base.biz.entity.ServiceOrderEvaluate;
import com.jiumai.base.biz.query.ServiceOrderEvaluateQuery;
import com.jiumai.base.biz.vo.ServiceOrderEvaluateVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务工单评价表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ServiceOrderEvaluateService extends IService<ServiceOrderEvaluate> {

    /**
     * 分页查询服务工单评价表
     * @param query
     * @return
     */
    Page<ServiceOrderEvaluateVO> findServiceOrderEvaluatePage(ServiceOrderEvaluateQuery query);

    /**
     * 添加或更新服务工单评价表
     * @param serviceOrderEvaluateDTO
     * @return
     */
    Long saveOrUpdateServiceOrderEvaluate(ServiceOrderEvaluateDTO serviceOrderEvaluateDTO);

    /**
     * 通过id查询成功服务工单评价表详情
     * @param id
     * @return
     */
    ServiceOrderEvaluateVO getServiceOrderEvaluateById(Long id);
}
