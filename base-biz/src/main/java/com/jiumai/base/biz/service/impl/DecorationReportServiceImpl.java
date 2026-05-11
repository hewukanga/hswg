package com.jiumai.base.biz.service.impl;

import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.DecorationReportDTO;
import com.jiumai.base.biz.entity.DecorationReport;
import com.jiumai.base.biz.mapper.DecorationReportMapper;
import com.jiumai.base.biz.query.DecorationReportQuery;
import com.jiumai.base.biz.service.DecorationReportService;
import com.jiumai.base.biz.vo.DecorationReportVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 装修报备表 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Service
public class DecorationReportServiceImpl extends ServiceImpl<DecorationReportMapper, DecorationReport> implements DecorationReportService {

    @Resource
    private DecorationReportMapper decorationReportMapper;

    @Override
    public Page<DecorationReportVO> findDecorationReportPage(DecorationReportQuery query) {
        Page<DecorationReportVO> page = new Page<>(query.getPage(), query.getSize());
        List<DecorationReportVO> list = decorationReportMapper.findDecorationReportPage(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdateDecorationReport(DecorationReportDTO decorationReportDTO) {
        DecorationReport decorationReport = new DecorationReport();

        BeanUtils.copyProperties(decorationReportDTO, decorationReport);

        this.saveOrUpdate(decorationReport);

        return decorationReport.getId();
    }

    @Override
    public DecorationReportVO getDecorationReportById(Long id) {
        DecorationReport decorationReport = this.getById(id);
        if (CommonFuntions.isEmptyObject(decorationReport)) {
            throw new BizException("查询失败，装修报备表不存在");
        }

        DecorationReportVO decorationReportVO = new DecorationReportVO();
        BeanUtils.copyProperties(decorationReport, decorationReportVO);
        return decorationReportVO;
    }
}
