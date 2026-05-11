package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.DecorationReportDTO;
import com.jiumai.base.biz.entity.DecorationReport;
import com.jiumai.base.biz.query.DecorationReportQuery;
import com.jiumai.base.biz.vo.DecorationReportVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 装修报备表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface DecorationReportService extends IService<DecorationReport> {

    /**
     * 分页查询装修报备表
     * @param query
     * @return
     */
    Page<DecorationReportVO> findDecorationReportPage(DecorationReportQuery query);

    /**
     * 添加或更新装修报备表
     * @param decorationReportDTO
     * @return
     */
    Long saveOrUpdateDecorationReport(DecorationReportDTO decorationReportDTO);

    /**
     * 通过id查询成功装修报备表详情
     * @param id
     * @return
     */
    DecorationReportVO getDecorationReportById(Long id);
}
