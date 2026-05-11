package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ExamineTeamDTO;
import com.jiumai.base.biz.query.ExamineTeamQuery;
import com.jiumai.base.biz.service.ExamineTeamService;
import com.jiumai.base.biz.vo.ExamineTeamVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 考核组 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/examine-team")
@Api(tags = {"考核组管理"})
public class ExamineTeamController {

    @Resource
    private ExamineTeamService examineTeamService;

    @PostMapping("findExamineTeamPage")
    @ApiOperation("分页查询考核组")
    public ResultDTO<Page<ExamineTeamVO>> findExamineTeamPage(HttpServletRequest request, @RequestBody ExamineTeamQuery query) {
        ResultDTO<Page<ExamineTeamVO>> result = new ResultDTO<>();
        Page<ExamineTeamVO> page = examineTeamService.findExamineTeamPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateExamineTeam")
    @ApiOperation("保存或更新考核组")
    @OpLog(title = "保存或更新考核组", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateExamineTeam(HttpServletRequest request, @RequestBody ExamineTeamDTO examineTeamDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = examineTeamService.saveOrUpdateExamineTeam(examineTeamDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getExamineTeamById")
    @ApiOperation("根据ID获取考核组")
    public ResultDTO<ExamineTeamVO> getExamineTeamById(Long id) {
        ResultDTO<ExamineTeamVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ExamineTeamVO examineTeamVO = examineTeamService.getExamineTeamById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", examineTeamVO);
    }

    @PostMapping("batchRemoveExamineTeamByIds")
    @ApiOperation("根据ID批量删除考核组")
    @OpLog(title = "根据ID批量删除考核组", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveExamineTeamByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        examineTeamService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
