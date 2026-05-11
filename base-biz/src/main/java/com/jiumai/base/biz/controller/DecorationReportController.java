package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.DecorationReportDTO;
import com.jiumai.base.biz.query.DecorationReportQuery;
import com.jiumai.base.biz.service.DecorationReportService;
import com.jiumai.base.biz.vo.DecorationReportVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 装修报备表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/decoration-report")
@Api(tags = {"装修报备表管理"})
public class DecorationReportController {

    @Resource
    private DecorationReportService decorationReportService;

    @PostMapping("findDecorationReportPage")
    @ApiOperation("分页查询装修报备表")
    public ResultDTO<Page<DecorationReportVO>> findDecorationReportPage(HttpServletRequest request, @RequestBody DecorationReportQuery query) {
        ResultDTO<Page<DecorationReportVO>> result = new ResultDTO<>();
        Page<DecorationReportVO> page = decorationReportService.findDecorationReportPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateDecorationReport")
    @ApiOperation("保存或更新装修报备表")
    @OpLog(title = "保存或更新装修报备表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateDecorationReport(HttpServletRequest request, @RequestBody DecorationReportDTO decorationReportDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = decorationReportService.saveOrUpdateDecorationReport(decorationReportDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getDecorationReportById")
    @ApiOperation("根据ID获取装修报备表")
    public ResultDTO<DecorationReportVO> getDecorationReportById(Long id) {
        ResultDTO<DecorationReportVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        DecorationReportVO decorationReportVO = decorationReportService.getDecorationReportById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", decorationReportVO);
    }

    @PostMapping("batchRemoveDecorationReportByIds")
    @ApiOperation("根据ID批量删除装修报备表")
    @OpLog(title = "根据ID批量删除装修报备表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveDecorationReportByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        decorationReportService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
