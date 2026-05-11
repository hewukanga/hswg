package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.MiniClickStatisticsDetails;
import com.jiumai.base.biz.query.MiniClickStatisticsDetailsQuery;
import com.jiumai.base.biz.vo.MiniClickStatisticsDetailsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 小程序点击统计详情表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MiniClickStatisticsDetailsMapper extends BaseMapper<MiniClickStatisticsDetails> {

    /**
     * 分页查询小程序点击统计详情表
     * @param page
     * @param query
     * @return
     */
    List<MiniClickStatisticsDetailsVO> findMiniClickStatisticsDetailsPage(Page<MiniClickStatisticsDetailsVO> page, @Param("query") MiniClickStatisticsDetailsQuery query);
}
