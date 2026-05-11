package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PatrolRuleListDTO;
import com.jiumai.base.biz.entity.PatrolRuleList;
import com.jiumai.base.biz.query.PatrolRuleListQuery;
import com.jiumai.base.biz.vo.PatrolRuleListVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 巡查单规则表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolRuleListService extends IService<PatrolRuleList> {

    /**
     * 分页查询巡查单规则表
     * @param query
     * @return
     */
    Page<PatrolRuleListVO> findPatrolRuleListPage(PatrolRuleListQuery query);

    /**
     * 添加或更新巡查单规则表
     * @param patrolRuleListDTO
     * @return
     */
    Long saveOrUpdatePatrolRuleList(PatrolRuleListDTO patrolRuleListDTO);

    /**
     * 通过id查询成功巡查单规则表详情
     * @param id
     * @return
     */
    PatrolRuleListVO getPatrolRuleListById(Long id);
}
