package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PatrolRuleSuitUserDTO;
import com.jiumai.base.biz.entity.PatrolRuleSuitUser;
import com.jiumai.base.biz.query.PatrolRuleSuitUserQuery;
import com.jiumai.base.biz.vo.PatrolRuleSuitUserVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 巡查单规则适用人员表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolRuleSuitUserService extends IService<PatrolRuleSuitUser> {

    /**
     * 分页查询巡查单规则适用人员表
     * @param query
     * @return
     */
    Page<PatrolRuleSuitUserVO> findPatrolRuleSuitUserPage(PatrolRuleSuitUserQuery query);

    /**
     * 添加或更新巡查单规则适用人员表
     * @param patrolRuleSuitUserDTO
     * @return
     */
    Long saveOrUpdatePatrolRuleSuitUser(PatrolRuleSuitUserDTO patrolRuleSuitUserDTO);

    /**
     * 通过id查询成功巡查单规则适用人员表详情
     * @param id
     * @return
     */
    PatrolRuleSuitUserVO getPatrolRuleSuitUserById(Long id);
}
