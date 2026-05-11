package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PatrolRecordDTO;
import com.jiumai.base.biz.entity.PatrolRecord;
import com.jiumai.base.biz.query.PatrolRecordQuery;
import com.jiumai.base.biz.vo.PatrolRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 巡查记录表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolRecordService extends IService<PatrolRecord> {

    /**
     * 分页查询巡查记录表
     * @param query
     * @return
     */
    Page<PatrolRecordVO> findPatrolRecordPage(PatrolRecordQuery query);

    /**
     * 添加或更新巡查记录表
     * @param patrolRecordDTO
     * @return
     */
    Long saveOrUpdatePatrolRecord(PatrolRecordDTO patrolRecordDTO);

    /**
     * 通过id查询成功巡查记录表详情
     * @param id
     * @return
     */
    PatrolRecordVO getPatrolRecordById(Long id);
}
