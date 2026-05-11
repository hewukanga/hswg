package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MiniClickStatisticsDetailsDTO;
import com.jiumai.base.biz.query.MiniClickStatisticsDetailsQuery;
import com.jiumai.base.biz.service.MiniClickStatisticsDetailsService;
import com.jiumai.base.biz.vo.MiniClickStatisticsDetailsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 小程序点击统计详情表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/mini-click-statistics-details")
@Api(tags = {"小程序点击统计详情表管理"})
public class MiniClickStatisticsDetailsController {

    @Resource
    private MiniClickStatisticsDetailsService miniClickStatisticsDetailsService;

    @PostMapping("findMiniClickStatisticsDetailsPage")
    @ApiOperation("分页查询小程序点击统计详情表")
    public ResultDTO<Page<MiniClickStatisticsDetailsVO>> findMiniClickStatisticsDetailsPage(HttpServletRequest request, @RequestBody MiniClickStatisticsDetailsQuery query) {
        ResultDTO<Page<MiniClickStatisticsDetailsVO>> result = new ResultDTO<>();
        Page<MiniClickStatisticsDetailsVO> page = miniClickStatisticsDetailsService.findMiniClickStatisticsDetailsPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateMiniClickStatisticsDetails")
    @ApiOperation("保存或更新小程序点击统计详情表")
    @OpLog(title = "保存或更新小程序点击统计详情表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateMiniClickStatisticsDetails(HttpServletRequest request, @RequestBody MiniClickStatisticsDetailsDTO miniClickStatisticsDetailsDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = miniClickStatisticsDetailsService.saveOrUpdateMiniClickStatisticsDetails(miniClickStatisticsDetailsDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getMiniClickStatisticsDetailsById")
    @ApiOperation("根据ID获取小程序点击统计详情表")
    public ResultDTO<MiniClickStatisticsDetailsVO> getMiniClickStatisticsDetailsById(Long id) {
        ResultDTO<MiniClickStatisticsDetailsVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        MiniClickStatisticsDetailsVO miniClickStatisticsDetailsVO = miniClickStatisticsDetailsService.getMiniClickStatisticsDetailsById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", miniClickStatisticsDetailsVO);
    }

    @PostMapping("batchRemoveMiniClickStatisticsDetailsByIds")
    @ApiOperation("根据ID批量删除小程序点击统计详情表")
    @OpLog(title = "根据ID批量删除小程序点击统计详情表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveMiniClickStatisticsDetailsByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        miniClickStatisticsDetailsService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
