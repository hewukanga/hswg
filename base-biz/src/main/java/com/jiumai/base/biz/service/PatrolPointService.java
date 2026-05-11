package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PatrolPointDTO;
import com.jiumai.base.biz.entity.PatrolPoint;
import com.jiumai.base.biz.query.PatrolPointQuery;
import com.jiumai.base.biz.vo.PatrolPointVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 巡查点位表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolPointService extends IService<PatrolPoint> {

    /**
     * 分页查询巡查点位表
     * @param query
     * @return
     */
    Page<PatrolPointVO> findPatrolPointPage(PatrolPointQuery query);

    /**
     * 添加或更新巡查点位表
     * @param patrolPointDTO
     * @return
     */
    Long saveOrUpdatePatrolPoint(PatrolPointDTO patrolPointDTO);

    /**
     * 通过id查询成功巡查点位表详情
     * @param id
     * @return
     */
    PatrolPointVO getPatrolPointById(Long id);
}
