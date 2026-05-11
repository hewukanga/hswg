package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ProcessRecordDTO;
import com.jiumai.base.biz.entity.ProcessRecord;
import com.jiumai.base.biz.query.ProcessRecordQuery;
import com.jiumai.base.biz.vo.ProcessRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 流程记录表(流程创建时生成全部记录) 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ProcessRecordService extends IService<ProcessRecord> {

    /**
     * 分页查询流程记录表(流程创建时生成全部记录)
     * @param query
     * @return
     */
    Page<ProcessRecordVO> findProcessRecordPage(ProcessRecordQuery query);

    /**
     * 添加或更新流程记录表(流程创建时生成全部记录)
     * @param processRecordDTO
     * @return
     */
    Long saveOrUpdateProcessRecord(ProcessRecordDTO processRecordDTO);

    /**
     * 通过id查询成功流程记录表(流程创建时生成全部记录)详情
     * @param id
     * @return
     */
    ProcessRecordVO getProcessRecordById(Long id);
}
