package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ProcessAuditDTO;
import com.jiumai.base.biz.entity.ProcessAudit;
import com.jiumai.base.biz.query.ProcessAuditQuery;
import com.jiumai.base.biz.vo.ProcessAuditVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 流程审批表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ProcessAuditService extends IService<ProcessAudit> {

    /**
     * 分页查询流程审批表
     * @param query
     * @return
     */
    Page<ProcessAuditVO> findProcessAuditPage(ProcessAuditQuery query);

    /**
     * 添加或更新流程审批表
     * @param processAuditDTO
     * @return
     */
    Long saveOrUpdateProcessAudit(ProcessAuditDTO processAuditDTO);

    /**
     * 通过id查询成功流程审批表详情
     * @param id
     * @return
     */
    ProcessAuditVO getProcessAuditById(Long id);
}
