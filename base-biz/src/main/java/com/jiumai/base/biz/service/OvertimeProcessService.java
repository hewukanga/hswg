package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.OvertimeProcessDTO;
import com.jiumai.base.biz.entity.OvertimeProcess;
import com.jiumai.base.biz.query.OvertimeProcessQuery;
import com.jiumai.base.biz.vo.OvertimeProcessVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 加班记录表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OvertimeProcessService extends IService<OvertimeProcess> {

    /**
     * 分页查询加班记录表
     * @param query
     * @return
     */
    Page<OvertimeProcessVO> findOvertimeProcessPage(OvertimeProcessQuery query);

    /**
     * 添加或更新加班记录表
     * @param overtimeProcessDTO
     * @return
     */
    Long saveOrUpdateOvertimeProcess(OvertimeProcessDTO overtimeProcessDTO);

    /**
     * 通过id查询成功加班记录表详情
     * @param id
     * @return
     */
    OvertimeProcessVO getOvertimeProcessById(Long id);
}
