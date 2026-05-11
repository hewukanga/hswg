package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CleaningAreaOpDTO;
import com.jiumai.base.biz.query.CleaningAreaOpQuery;
import com.jiumai.base.biz.service.CleaningAreaOpService;
import com.jiumai.base.biz.vo.CleaningAreaOpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 保洁区域人员 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/cleaning-area-op")
@Api(tags = {"保洁区域人员管理"})
public class CleaningAreaOpController {

    @Resource
    private CleaningAreaOpService cleaningAreaOpService;

    @PostMapping("findCleaningAreaOpPage")
    @ApiOperation("分页查询保洁区域人员")
    public ResultDTO<Page<CleaningAreaOpVO>> findCleaningAreaOpPage(HttpServletRequest request, @RequestBody CleaningAreaOpQuery query) {
        ResultDTO<Page<CleaningAreaOpVO>> result = new ResultDTO<>();
        Page<CleaningAreaOpVO> page = cleaningAreaOpService.findCleaningAreaOpPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateCleaningAreaOp")
    @ApiOperation("保存或更新保洁区域人员")
    @OpLog(title = "保存或更新保洁区域人员", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateCleaningAreaOp(HttpServletRequest request, @RequestBody CleaningAreaOpDTO cleaningAreaOpDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = cleaningAreaOpService.saveOrUpdateCleaningAreaOp(cleaningAreaOpDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getCleaningAreaOpById")
    @ApiOperation("根据ID获取保洁区域人员")
    public ResultDTO<CleaningAreaOpVO> getCleaningAreaOpById(Long id) {
        ResultDTO<CleaningAreaOpVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        CleaningAreaOpVO cleaningAreaOpVO = cleaningAreaOpService.getCleaningAreaOpById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", cleaningAreaOpVO);
    }

    @PostMapping("batchRemoveCleaningAreaOpByIds")
    @ApiOperation("根据ID批量删除保洁区域人员")
    @OpLog(title = "根据ID批量删除保洁区域人员", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveCleaningAreaOpByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        cleaningAreaOpService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
