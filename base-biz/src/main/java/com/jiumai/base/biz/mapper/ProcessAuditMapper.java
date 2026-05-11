package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ProcessAudit;
import com.jiumai.base.biz.query.ProcessAuditQuery;
import com.jiumai.base.biz.vo.ProcessAuditVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 流程审批表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ProcessAuditMapper extends BaseMapper<ProcessAudit> {

    /**
     * 分页查询流程审批表
     * @param page
     * @param query
     * @return
     */
    List<ProcessAuditVO> findProcessAuditPage(Page<ProcessAuditVO> page, @Param("query") ProcessAuditQuery query);
}
