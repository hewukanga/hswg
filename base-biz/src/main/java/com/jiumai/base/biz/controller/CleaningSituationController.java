package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CleaningSituationDTO;
import com.jiumai.base.biz.query.CleaningSituationQuery;
import com.jiumai.base.biz.service.CleaningSituationService;
import com.jiumai.base.biz.vo.CleaningSituationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 保洁情况 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/cleaning-situation")
@Api(tags = {"保洁情况管理"})
public class CleaningSituationController {

    @Resource
    private CleaningSituationService cleaningSituationService;

    @PostMapping("findCleaningSituationPage")
    @ApiOperation("分页查询保洁情况")
    public ResultDTO<Page<CleaningSituationVO>> findCleaningSituationPage(HttpServletRequest request, @RequestBody CleaningSituationQuery query) {
        ResultDTO<Page<CleaningSituationVO>> result = new ResultDTO<>();
        Page<CleaningSituationVO> page = cleaningSituationService.findCleaningSituationPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateCleaningSituation")
    @ApiOperation("保存或更新保洁情况")
    @OpLog(title = "保存或更新保洁情况", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateCleaningSituation(HttpServletRequest request, @RequestBody CleaningSituationDTO cleaningSituationDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = cleaningSituationService.saveOrUpdateCleaningSituation(cleaningSituationDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getCleaningSituationById")
    @ApiOperation("根据ID获取保洁情况")
    public ResultDTO<CleaningSituationVO> getCleaningSituationById(Long id) {
        ResultDTO<CleaningSituationVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        CleaningSituationVO cleaningSituationVO = cleaningSituationService.getCleaningSituationById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", cleaningSituationVO);
    }

    @PostMapping("batchRemoveCleaningSituationByIds")
    @ApiOperation("根据ID批量删除保洁情况")
    @OpLog(title = "根据ID批量删除保洁情况", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveCleaningSituationByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        cleaningSituationService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
