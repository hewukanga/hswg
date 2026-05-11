package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.AttendanceRuleOpDTO;
import com.jiumai.base.biz.query.AttendanceRuleOpQuery;
import com.jiumai.base.biz.service.AttendanceRuleOpService;
import com.jiumai.base.biz.vo.AttendanceRuleOpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 考勤规则人员表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/attendance-rule-op")
@Api(tags = {"考勤规则人员表管理"})
public class AttendanceRuleOpController {

    @Resource
    private AttendanceRuleOpService attendanceRuleOpService;

    @PostMapping("findAttendanceRuleOpPage")
    @ApiOperation("分页查询考勤规则人员表")
    public ResultDTO<Page<AttendanceRuleOpVO>> findAttendanceRuleOpPage(HttpServletRequest request, @RequestBody AttendanceRuleOpQuery query) {
        ResultDTO<Page<AttendanceRuleOpVO>> result = new ResultDTO<>();
        Page<AttendanceRuleOpVO> page = attendanceRuleOpService.findAttendanceRuleOpPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateAttendanceRuleOp")
    @ApiOperation("保存或更新考勤规则人员表")
    @OpLog(title = "保存或更新考勤规则人员表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateAttendanceRuleOp(HttpServletRequest request, @RequestBody AttendanceRuleOpDTO attendanceRuleOpDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = attendanceRuleOpService.saveOrUpdateAttendanceRuleOp(attendanceRuleOpDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getAttendanceRuleOpById")
    @ApiOperation("根据ID获取考勤规则人员表")
    public ResultDTO<AttendanceRuleOpVO> getAttendanceRuleOpById(Long id) {
        ResultDTO<AttendanceRuleOpVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        AttendanceRuleOpVO attendanceRuleOpVO = attendanceRuleOpService.getAttendanceRuleOpById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", attendanceRuleOpVO);
    }

    @PostMapping("batchRemoveAttendanceRuleOpByIds")
    @ApiOperation("根据ID批量删除考勤规则人员表")
    @OpLog(title = "根据ID批量删除考勤规则人员表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveAttendanceRuleOpByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        attendanceRuleOpService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
