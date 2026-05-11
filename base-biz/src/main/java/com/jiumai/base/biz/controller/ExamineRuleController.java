package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ExamineRuleDTO;
import com.jiumai.base.biz.query.ExamineRuleQuery;
import com.jiumai.base.biz.service.ExamineRuleService;
import com.jiumai.base.biz.vo.ExamineRuleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 考核规则 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/examine-rule")
@Api(tags = {"考核规则管理"})
public class ExamineRuleController {

    @Resource
    private ExamineRuleService examineRuleService;

    @PostMapping("findExamineRulePage")
    @ApiOperation("分页查询考核规则")
    public ResultDTO<Page<ExamineRuleVO>> findExamineRulePage(HttpServletRequest request, @RequestBody ExamineRuleQuery query) {
        ResultDTO<Page<ExamineRuleVO>> result = new ResultDTO<>();
        Page<ExamineRuleVO> page = examineRuleService.findExamineRulePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateExamineRule")
    @ApiOperation("保存或更新考核规则")
    @OpLog(title = "保存或更新考核规则", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateExamineRule(HttpServletRequest request, @RequestBody ExamineRuleDTO examineRuleDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = examineRuleService.saveOrUpdateExamineRule(examineRuleDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getExamineRuleById")
    @ApiOperation("根据ID获取考核规则")
    public ResultDTO<ExamineRuleVO> getExamineRuleById(Long id) {
        ResultDTO<ExamineRuleVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ExamineRuleVO examineRuleVO = examineRuleService.getExamineRuleById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", examineRuleVO);
    }

    @PostMapping("batchRemoveExamineRuleByIds")
    @ApiOperation("根据ID批量删除考核规则")
    @OpLog(title = "根据ID批量删除考核规则", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveExamineRuleByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        examineRuleService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
