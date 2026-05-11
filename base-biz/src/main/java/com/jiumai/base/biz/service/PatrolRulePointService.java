package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PatrolRulePointDTO;
import com.jiumai.base.biz.entity.PatrolRulePoint;
import com.jiumai.base.biz.query.PatrolRulePointQuery;
import com.jiumai.base.biz.vo.PatrolRulePointVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 巡查单规则点位表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolRulePointService extends IService<PatrolRulePoint> {

    /**
     * 分页查询巡查单规则点位表
     * @param query
     * @return
     */
    Page<PatrolRulePointVO> findPatrolRulePointPage(PatrolRulePointQuery query);

    /**
     * 添加或更新巡查单规则点位表
     * @param patrolRulePointDTO
     * @return
     */
    Long saveOrUpdatePatrolRulePoint(PatrolRulePointDTO patrolRulePointDTO);

    /**
     * 通过id查询成功巡查单规则点位表详情
     * @param id
     * @return
     */
    PatrolRulePointVO getPatrolRulePointById(Long id);
}
