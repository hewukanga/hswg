package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.AttendanceRuleDTO;
import com.jiumai.base.biz.query.AttendanceRuleQuery;
import com.jiumai.base.biz.service.AttendanceRuleService;
import com.jiumai.base.biz.vo.AttendanceRuleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 考勤规则表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/attendance-rule")
@Api(tags = {"考勤规则表管理"})
public class AttendanceRuleController {

    @Resource
    private AttendanceRuleService attendanceRuleService;

    @PostMapping("findAttendanceRulePage")
    @ApiOperation("分页查询考勤规则表")
    public ResultDTO<Page<AttendanceRuleVO>> findAttendanceRulePage(HttpServletRequest request, @RequestBody AttendanceRuleQuery query) {
        ResultDTO<Page<AttendanceRuleVO>> result = new ResultDTO<>();
        Page<AttendanceRuleVO> page = attendanceRuleService.findAttendanceRulePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateAttendanceRule")
    @ApiOperation("保存或更新考勤规则表")
    @OpLog(title = "保存或更新考勤规则表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateAttendanceRule(HttpServletRequest request, @RequestBody AttendanceRuleDTO attendanceRuleDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = attendanceRuleService.saveOrUpdateAttendanceRule(attendanceRuleDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getAttendanceRuleById")
    @ApiOperation("根据ID获取考勤规则表")
    public ResultDTO<AttendanceRuleVO> getAttendanceRuleById(Long id) {
        ResultDTO<AttendanceRuleVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        AttendanceRuleVO attendanceRuleVO = attendanceRuleService.getAttendanceRuleById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", attendanceRuleVO);
    }

    @PostMapping("batchRemoveAttendanceRuleByIds")
    @ApiOperation("根据ID批量删除考勤规则表")
    @OpLog(title = "根据ID批量删除考勤规则表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveAttendanceRuleByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        attendanceRuleService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
