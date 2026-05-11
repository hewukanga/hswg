package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PatrolList;
import com.jiumai.base.biz.query.PatrolListQuery;
import com.jiumai.base.biz.vo.PatrolListVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 巡查单表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PatrolListMapper extends BaseMapper<PatrolList> {

    /**
     * 分页查询巡查单表
     * @param page
     * @param query
     * @return
     */
    List<PatrolListVO> findPatrolListPage(Page<PatrolListVO> page, @Param("query") PatrolListQuery query);
}
