package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PatrolListDTO;
import com.jiumai.base.biz.entity.PatrolList;
import com.jiumai.base.biz.query.PatrolListQuery;
import com.jiumai.base.biz.vo.PatrolListVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 巡查单表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolListService extends IService<PatrolList> {

    /**
     * 分页查询巡查单表
     * @param query
     * @return
     */
    Page<PatrolListVO> findPatrolListPage(PatrolListQuery query);

    /**
     * 添加或更新巡查单表
     * @param patrolListDTO
     * @return
     */
    Long saveOrUpdatePatrolList(PatrolListDTO patrolListDTO);

    /**
     * 通过id查询成功巡查单表详情
     * @param id
     * @return
     */
    PatrolListVO getPatrolListById(Long id);
}
