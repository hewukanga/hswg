package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CleaningAreaDTO;
import com.jiumai.base.biz.query.CleaningAreaQuery;
import com.jiumai.base.biz.service.CleaningAreaService;
import com.jiumai.base.biz.vo.CleaningAreaVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 保洁区域管理 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/cleaning-area")
@Api(tags = {"保洁区域管理管理"})
public class CleaningAreaController {

    @Resource
    private CleaningAreaService cleaningAreaService;

    @PostMapping("findCleaningAreaPage")
    @ApiOperation("分页查询保洁区域管理")
    public ResultDTO<Page<CleaningAreaVO>> findCleaningAreaPage(HttpServletRequest request, @RequestBody CleaningAreaQuery query) {
        ResultDTO<Page<CleaningAreaVO>> result = new ResultDTO<>();
        Page<CleaningAreaVO> page = cleaningAreaService.findCleaningAreaPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateCleaningArea")
    @ApiOperation("保存或更新保洁区域管理")
    @OpLog(title = "保存或更新保洁区域管理", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateCleaningArea(HttpServletRequest request, @RequestBody CleaningAreaDTO cleaningAreaDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = cleaningAreaService.saveOrUpdateCleaningArea(cleaningAreaDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getCleaningAreaById")
    @ApiOperation("根据ID获取保洁区域管理")
    public ResultDTO<CleaningAreaVO> getCleaningAreaById(Long id) {
        ResultDTO<CleaningAreaVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        CleaningAreaVO cleaningAreaVO = cleaningAreaService.getCleaningAreaById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", cleaningAreaVO);
    }

    @PostMapping("batchRemoveCleaningAreaByIds")
    @ApiOperation("根据ID批量删除保洁区域管理")
    @OpLog(title = "根据ID批量删除保洁区域管理", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveCleaningAreaByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        cleaningAreaService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
