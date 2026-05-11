package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.MiniClickStatistics;
import com.jiumai.base.biz.query.MiniClickStatisticsQuery;
import com.jiumai.base.biz.vo.MiniClickStatisticsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 小程序点击统计表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MiniClickStatisticsMapper extends BaseMapper<MiniClickStatistics> {

    /**
     * 分页查询小程序点击统计表
     * @param page
     * @param query
     * @return
     */
    List<MiniClickStatisticsVO> findMiniClickStatisticsPage(Page<MiniClickStatisticsVO> page, @Param("query") MiniClickStatisticsQuery query);
}
