package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.MiniClickStatisticsDetailsDTO;
import com.jiumai.base.biz.entity.MiniClickStatisticsDetails;
import com.jiumai.base.biz.query.MiniClickStatisticsDetailsQuery;
import com.jiumai.base.biz.vo.MiniClickStatisticsDetailsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 小程序点击统计详情表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface MiniClickStatisticsDetailsService extends IService<MiniClickStatisticsDetails> {

    /**
     * 分页查询小程序点击统计详情表
     * @param query
     * @return
     */
    Page<MiniClickStatisticsDetailsVO> findMiniClickStatisticsDetailsPage(MiniClickStatisticsDetailsQuery query);

    /**
     * 添加或更新小程序点击统计详情表
     * @param miniClickStatisticsDetailsDTO
     * @return
     */
    Long saveOrUpdateMiniClickStatisticsDetails(MiniClickStatisticsDetailsDTO miniClickStatisticsDetailsDTO);

    /**
     * 通过id查询成功小程序点击统计详情表详情
     * @param id
     * @return
     */
    MiniClickStatisticsDetailsVO getMiniClickStatisticsDetailsById(Long id);
}
