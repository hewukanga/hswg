package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.MiniClickStatisticsDTO;
import com.jiumai.base.biz.entity.MiniClickStatistics;
import com.jiumai.base.biz.query.MiniClickStatisticsQuery;
import com.jiumai.base.biz.vo.MiniClickStatisticsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 小程序点击统计表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MiniClickStatisticsService extends IService<MiniClickStatistics> {

    /**
     * 分页查询小程序点击统计表
     * @param query
     * @return
     */
    Page<MiniClickStatisticsVO> findMiniClickStatisticsPage(MiniClickStatisticsQuery query);

    /**
     * 添加或更新小程序点击统计表
     * @param miniClickStatisticsDTO
     * @return
     */
    Long saveOrUpdateMiniClickStatistics(MiniClickStatisticsDTO miniClickStatisticsDTO);

    /**
     * 通过id查询成功小程序点击统计表详情
     * @param id
     * @return
     */
    MiniClickStatisticsVO getMiniClickStatisticsById(Long id);
}
