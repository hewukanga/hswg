package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ExamineTeamDetailsDTO;
import com.jiumai.base.biz.query.ExamineTeamDetailsQuery;
import com.jiumai.base.biz.service.ExamineTeamDetailsService;
import com.jiumai.base.biz.vo.ExamineTeamDetailsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 考核组详情 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/examine-team-details")
@Api(tags = {"考核组详情管理"})
public class ExamineTeamDetailsController {

    @Resource
    private ExamineTeamDetailsService examineTeamDetailsService;

    @PostMapping("findExamineTeamDetailsPage")
    @ApiOperation("分页查询考核组详情")
    public ResultDTO<Page<ExamineTeamDetailsVO>> findExamineTeamDetailsPage(HttpServletRequest request, @RequestBody ExamineTeamDetailsQuery query) {
        ResultDTO<Page<ExamineTeamDetailsVO>> result = new ResultDTO<>();
        Page<ExamineTeamDetailsVO> page = examineTeamDetailsService.findExamineTeamDetailsPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateExamineTeamDetails")
    @ApiOperation("保存或更新考核组详情")
    @OpLog(title = "保存或更新考核组详情", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateExamineTeamDetails(HttpServletRequest request, @RequestBody ExamineTeamDetailsDTO examineTeamDetailsDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = examineTeamDetailsService.saveOrUpdateExamineTeamDetails(examineTeamDetailsDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getExamineTeamDetailsById")
    @ApiOperation("根据ID获取考核组详情")
    public ResultDTO<ExamineTeamDetailsVO> getExamineTeamDetailsById(Long id) {
        ResultDTO<ExamineTeamDetailsVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ExamineTeamDetailsVO examineTeamDetailsVO = examineTeamDetailsService.getExamineTeamDetailsById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", examineTeamDetailsVO);
    }

    @PostMapping("batchRemoveExamineTeamDetailsByIds")
    @ApiOperation("根据ID批量删除考核组详情")
    @OpLog(title = "根据ID批量删除考核组详情", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveExamineTeamDetailsByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        examineTeamDetailsService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
