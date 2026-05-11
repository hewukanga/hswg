package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MiniClickStatisticsDTO;
import com.jiumai.base.biz.query.MiniClickStatisticsQuery;
import com.jiumai.base.biz.service.MiniClickStatisticsService;
import com.jiumai.base.biz.vo.MiniClickStatisticsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 小程序点击统计表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/mini-click-statistics")
@Api(tags = {"小程序点击统计表管理"})
public class MiniClickStatisticsController {

    @Resource
    private MiniClickStatisticsService miniClickStatisticsService;

    @PostMapping("findMiniClickStatisticsPage")
    @ApiOperation("分页查询小程序点击统计表")
    public ResultDTO<Page<MiniClickStatisticsVO>> findMiniClickStatisticsPage(HttpServletRequest request, @RequestBody MiniClickStatisticsQuery query) {
        ResultDTO<Page<MiniClickStatisticsVO>> result = new ResultDTO<>();
        Page<MiniClickStatisticsVO> page = miniClickStatisticsService.findMiniClickStatisticsPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateMiniClickStatistics")
    @ApiOperation("保存或更新小程序点击统计表")
    @OpLog(title = "保存或更新小程序点击统计表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateMiniClickStatistics(HttpServletRequest request, @RequestBody MiniClickStatisticsDTO miniClickStatisticsDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = miniClickStatisticsService.saveOrUpdateMiniClickStatistics(miniClickStatisticsDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getMiniClickStatisticsById")
    @ApiOperation("根据ID获取小程序点击统计表")
    public ResultDTO<MiniClickStatisticsVO> getMiniClickStatisticsById(Long id) {
        ResultDTO<MiniClickStatisticsVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        MiniClickStatisticsVO miniClickStatisticsVO = miniClickStatisticsService.getMiniClickStatisticsById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", miniClickStatisticsVO);
    }

    @PostMapping("batchRemoveMiniClickStatisticsByIds")
    @ApiOperation("根据ID批量删除小程序点击统计表")
    @OpLog(title = "根据ID批量删除小程序点击统计表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveMiniClickStatisticsByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        miniClickStatisticsService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
