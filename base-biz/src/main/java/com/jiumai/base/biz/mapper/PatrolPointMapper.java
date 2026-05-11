package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PatrolPoint;
import com.jiumai.base.biz.query.PatrolPointQuery;
import com.jiumai.base.biz.vo.PatrolPointVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 巡查点位表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolPointMapper extends BaseMapper<PatrolPoint> {

    /**
     * 分页查询巡查点位表
     * @param page
     * @param query
     * @return
     */
    List<PatrolPointVO> findPatrolPointPage(Page<PatrolPointVO> page, @Param("query") PatrolPointQuery query);
}
