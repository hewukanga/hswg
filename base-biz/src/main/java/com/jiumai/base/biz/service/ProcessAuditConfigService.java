package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ProcessAuditConfigDTO;
import com.jiumai.base.biz.entity.ProcessAuditConfig;
import com.jiumai.base.biz.query.ProcessAuditConfigQuery;
import com.jiumai.base.biz.vo.ProcessAuditConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 流程审批配置表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ProcessAuditConfigService extends IService<ProcessAuditConfig> {

    /**
     * 分页查询流程审批配置表
     * @param query
     * @return
     */
    Page<ProcessAuditConfigVO> findProcessAuditConfigPage(ProcessAuditConfigQuery query);

    /**
     * 添加或更新流程审批配置表
     * @param processAuditConfigDTO
     * @return
     */
    Long saveOrUpdateProcessAuditConfig(ProcessAuditConfigDTO processAuditConfigDTO);

    /**
     * 通过id查询成功流程审批配置表详情
     * @param id
     * @return
     */
    ProcessAuditConfigVO getProcessAuditConfigById(Long id);
}
