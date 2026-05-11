package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.DecorationReport;
import com.jiumai.base.biz.query.DecorationReportQuery;
import com.jiumai.base.biz.vo.DecorationReportVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 装修报备表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface DecorationReportMapper extends BaseMapper<DecorationReport> {

    /**
     * 分页查询装修报备表
     * @param page
     * @param query
     * @return
     */
    List<DecorationReportVO> findDecorationReportPage(Page<DecorationReportVO> page, @Param("query") DecorationReportQuery query);
}
