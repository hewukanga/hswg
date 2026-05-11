package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ProcessDTO;
import com.jiumai.base.biz.entity.Process;
import com.jiumai.base.biz.query.ProcessQuery;
import com.jiumai.base.biz.vo.ProcessVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 流程表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ProcessService extends IService<Process> {

    /**
     * 分页查询流程表
     * @param query
     * @return
     */
    Page<ProcessVO> findProcessPage(ProcessQuery query);

    /**
     * 添加或更新流程表
     * @param processDTO
     * @return
     */
    Long saveOrUpdateProcess(ProcessDTO processDTO);

    /**
     * 通过id查询成功流程表详情
     * @param id
     * @return
     */
    ProcessVO getProcessById(Long id);
}
