package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.AttendanceRecordDTO;
import com.jiumai.base.biz.query.AttendanceRecordQuery;
import com.jiumai.base.biz.service.AttendanceRecordService;
import com.jiumai.base.biz.vo.AttendanceRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 考勤记录表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/attendance-record")
@Api(tags = {"考勤记录表管理"})
public class AttendanceRecordController {

    @Resource
    private AttendanceRecordService attendanceRecordService;

    @PostMapping("findAttendanceRecordPage")
    @ApiOperation("分页查询考勤记录表")
    public ResultDTO<Page<AttendanceRecordVO>> findAttendanceRecordPage(HttpServletRequest request, @RequestBody AttendanceRecordQuery query) {
        ResultDTO<Page<AttendanceRecordVO>> result = new ResultDTO<>();
        Page<AttendanceRecordVO> page = attendanceRecordService.findAttendanceRecordPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateAttendanceRecord")
    @ApiOperation("保存或更新考勤记录表")
    @OpLog(title = "保存或更新考勤记录表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateAttendanceRecord(HttpServletRequest request, @RequestBody AttendanceRecordDTO attendanceRecordDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = attendanceRecordService.saveOrUpdateAttendanceRecord(attendanceRecordDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getAttendanceRecordById")
    @ApiOperation("根据ID获取考勤记录表")
    public ResultDTO<AttendanceRecordVO> getAttendanceRecordById(Long id) {
        ResultDTO<AttendanceRecordVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        AttendanceRecordVO attendanceRecordVO = attendanceRecordService.getAttendanceRecordById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", attendanceRecordVO);
    }

    @PostMapping("batchRemoveAttendanceRecordByIds")
    @ApiOperation("根据ID批量删除考勤记录表")
    @OpLog(title = "根据ID批量删除考勤记录表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveAttendanceRecordByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        attendanceRecordService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
