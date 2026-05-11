package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.OvertimeProcessFlowDTO;
import com.jiumai.base.biz.entity.OvertimeProcessFlow;
import com.jiumai.base.biz.query.OvertimeProcessFlowQuery;
import com.jiumai.base.biz.vo.OvertimeProcessFlowVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 加班流程表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OvertimeProcessFlowService extends IService<OvertimeProcessFlow> {

    /**
     * 分页查询加班流程表
     * @param query
     * @return
     */
    Page<OvertimeProcessFlowVO> findOvertimeProcessFlowPage(OvertimeProcessFlowQuery query);

    /**
     * 添加或更新加班流程表
     * @param overtimeProcessFlowDTO
     * @return
     */
    Long saveOrUpdateOvertimeProcessFlow(OvertimeProcessFlowDTO overtimeProcessFlowDTO);

    /**
     * 通过id查询成功加班流程表详情
     * @param id
     * @return
     */
    OvertimeProcessFlowVO getOvertimeProcessFlowById(Long id);
}
