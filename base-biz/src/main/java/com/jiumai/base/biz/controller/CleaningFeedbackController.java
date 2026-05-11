package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CleaningFeedbackDTO;
import com.jiumai.base.biz.query.CleaningFeedbackQuery;
import com.jiumai.base.biz.service.CleaningFeedbackService;
import com.jiumai.base.biz.vo.CleaningFeedbackVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 保洁反馈 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/cleaning-feedback")
@Api(tags = {"保洁反馈管理"})
public class CleaningFeedbackController {

    @Resource
    private CleaningFeedbackService cleaningFeedbackService;

    @PostMapping("findCleaningFeedbackPage")
    @ApiOperation("分页查询保洁反馈")
    public ResultDTO<Page<CleaningFeedbackVO>> findCleaningFeedbackPage(HttpServletRequest request, @RequestBody CleaningFeedbackQuery query) {
        ResultDTO<Page<CleaningFeedbackVO>> result = new ResultDTO<>();
        Page<CleaningFeedbackVO> page = cleaningFeedbackService.findCleaningFeedbackPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateCleaningFeedback")
    @ApiOperation("保存或更新保洁反馈")
    @OpLog(title = "保存或更新保洁反馈", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateCleaningFeedback(HttpServletRequest request, @RequestBody CleaningFeedbackDTO cleaningFeedbackDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = cleaningFeedbackService.saveOrUpdateCleaningFeedback(cleaningFeedbackDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getCleaningFeedbackById")
    @ApiOperation("根据ID获取保洁反馈")
    public ResultDTO<CleaningFeedbackVO> getCleaningFeedbackById(Long id) {
        ResultDTO<CleaningFeedbackVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        CleaningFeedbackVO cleaningFeedbackVO = cleaningFeedbackService.getCleaningFeedbackById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", cleaningFeedbackVO);
    }

    @PostMapping("batchRemoveCleaningFeedbackByIds")
    @ApiOperation("根据ID批量删除保洁反馈")
    @OpLog(title = "根据ID批量删除保洁反馈", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveCleaningFeedbackByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        cleaningFeedbackService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
