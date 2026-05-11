package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ProcessAuditConfig;
import com.jiumai.base.biz.query.ProcessAuditConfigQuery;
import com.jiumai.base.biz.vo.ProcessAuditConfigVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 流程审批配置表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ProcessAuditConfigMapper extends BaseMapper<ProcessAuditConfig> {

    /**
     * 分页查询流程审批配置表
     * @param page
     * @param query
     * @return
     */
    List<ProcessAuditConfigVO> findProcessAuditConfigPage(Page<ProcessAuditConfigVO> page, @Param("query") ProcessAuditConfigQuery query);
}
