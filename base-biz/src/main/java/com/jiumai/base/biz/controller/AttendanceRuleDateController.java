package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.AttendanceRuleDateDTO;
import com.jiumai.base.biz.query.AttendanceRuleDateQuery;
import com.jiumai.base.biz.service.AttendanceRuleDateService;
import com.jiumai.base.biz.vo.AttendanceRuleDateVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 考勤规则日期表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/attendance-rule-date")
@Api(tags = {"考勤规则日期表管理"})
public class AttendanceRuleDateController {

    @Resource
    private AttendanceRuleDateService attendanceRuleDateService;

    @PostMapping("findAttendanceRuleDatePage")
    @ApiOperation("分页查询考勤规则日期表")
    public ResultDTO<Page<AttendanceRuleDateVO>> findAttendanceRuleDatePage(HttpServletRequest request, @RequestBody AttendanceRuleDateQuery query) {
        ResultDTO<Page<AttendanceRuleDateVO>> result = new ResultDTO<>();
        Page<AttendanceRuleDateVO> page = attendanceRuleDateService.findAttendanceRuleDatePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateAttendanceRuleDate")
    @ApiOperation("保存或更新考勤规则日期表")
    @OpLog(title = "保存或更新考勤规则日期表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateAttendanceRuleDate(HttpServletRequest request, @RequestBody AttendanceRuleDateDTO attendanceRuleDateDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = attendanceRuleDateService.saveOrUpdateAttendanceRuleDate(attendanceRuleDateDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getAttendanceRuleDateById")
    @ApiOperation("根据ID获取考勤规则日期表")
    public ResultDTO<AttendanceRuleDateVO> getAttendanceRuleDateById(Long id) {
        ResultDTO<AttendanceRuleDateVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        AttendanceRuleDateVO attendanceRuleDateVO = attendanceRuleDateService.getAttendanceRuleDateById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", attendanceRuleDateVO);
    }

    @PostMapping("batchRemoveAttendanceRuleDateByIds")
    @ApiOperation("根据ID批量删除考勤规则日期表")
    @OpLog(title = "根据ID批量删除考勤规则日期表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveAttendanceRuleDateByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        attendanceRuleDateService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
