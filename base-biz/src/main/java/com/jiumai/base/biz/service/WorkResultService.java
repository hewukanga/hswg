package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.WorkDispatchDTO;
import com.jiumai.base.biz.dto.WorkResultDTO;
import com.jiumai.base.biz.entity.WorkResult;
import com.jiumai.base.biz.query.WorkResultQuery;
import com.jiumai.base.biz.vo.WorkResultVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工作成果 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-15
 */
public interface WorkResultService extends IService<WorkResult> {

    /**
     * 分页查询工作成果
     * @param query
     * @return
     */
    Page<WorkResultVO> findWorkResultPage(WorkResultQuery query);

    /**
     * 添加或更新工作成果
     * @param workResultDTO
     * @return
     */
    Long saveOrUpdateWorkResult(WorkResultDTO workResultDTO);

    /**
     * 通过id查询工作成果详情
     * @param id
     * @return
     */
    WorkResultVO getWorkResultById(Long id);

    /**
     * 工作调度，仅更改工作的所属执行人
     * @param dispatchDTO 调度入参（id + executorId + executorName）
     * @return 更新成功返回true
     */
    Boolean dispatchWork(WorkDispatchDTO dispatchDTO);
}