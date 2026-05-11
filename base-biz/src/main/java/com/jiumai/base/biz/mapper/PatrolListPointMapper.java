package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PatrolListPoint;
import com.jiumai.base.biz.query.PatrolListPointQuery;
import com.jiumai.base.biz.vo.PatrolListPointVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 巡查单点位表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolListPointMapper extends BaseMapper<PatrolListPoint> {

    /**
     * 分页查询巡查单点位表
     * @param page
     * @param query
     * @return
     */
    List<PatrolListPointVO> findPatrolListPointPage(Page<PatrolListPointVO> page, @Param("query") PatrolListPointQuery query);
}
