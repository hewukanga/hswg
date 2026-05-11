package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PatrolListPointDTO;
import com.jiumai.base.biz.entity.PatrolListPoint;
import com.jiumai.base.biz.query.PatrolListPointQuery;
import com.jiumai.base.biz.vo.PatrolListPointVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 巡查单点位表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolListPointService extends IService<PatrolListPoint> {

    /**
     * 分页查询巡查单点位表
     * @param query
     * @return
     */
    Page<PatrolListPointVO> findPatrolListPointPage(PatrolListPointQuery query);

    /**
     * 添加或更新巡查单点位表
     * @param patrolListPointDTO
     * @return
     */
    Long saveOrUpdatePatrolListPoint(PatrolListPointDTO patrolListPointDTO);

    /**
     * 通过id查询成功巡查单点位表详情
     * @param id
     * @return
     */
    PatrolListPointVO getPatrolListPointById(Long id);
}
