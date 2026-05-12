package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ScheduleRecordDTO;
import com.jiumai.base.biz.query.ScheduleRecordQuery;
import com.jiumai.base.biz.service.ScheduleRecordService;
import com.jiumai.base.biz.vo.ScheduleRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 排班记录表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/schedule-record")
@Api(tags = {"排班记录表管理"})
public class ScheduleRecordController {

    @Resource
    private ScheduleRecordService scheduleRecordService;

    @PostMapping("findScheduleRecordPage")
    @ApiOperation("分页查询排班记录表")
    public ResultDTO<Page<ScheduleRecordVO>> findScheduleRecordPage(HttpServletRequest request, @RequestBody ScheduleRecordQuery query) {
        ResultDTO<Page<ScheduleRecordVO>> result = new ResultDTO<>();
        Page<ScheduleRecordVO> page = scheduleRecordService.findScheduleRecordPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateScheduleRecord")
    @ApiOperation("保存或更新排班记录表")
    @OpLog(title = "保存或更新排班记录表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateScheduleRecord(HttpServletRequest request, @RequestBody ScheduleRecordDTO scheduleRecordDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = scheduleRecordService.saveOrUpdateScheduleRecord(scheduleRecordDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getScheduleRecordById")
    @ApiOperation("根据ID获取排班记录表")
    public ResultDTO<ScheduleRecordVO> getScheduleRecordById(Long id) {
        ResultDTO<ScheduleRecordVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ScheduleRecordVO scheduleRecordVO = scheduleRecordService.getScheduleRecordById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", scheduleRecordVO);
    }

    @GetMapping("findScheduleRecordListByOpIdAndMonth")
    @ApiOperation("根据用户ID及月份查询排班信息列表")
    public ResultDTO<List<ScheduleRecordVO>> findScheduleRecordListByOpIdAndMonth(Long opId, String month) {
        ResultDTO<List<ScheduleRecordVO>> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(opId)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,opId为空");
        }
        if (CommonFuntions.isEmptyObject(month)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,month为空");
        }
        List<ScheduleRecordVO> list = scheduleRecordService.findScheduleRecordListByOpIdAndMonth(opId, month);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", list);
    }

    @PostMapping("batchRemoveScheduleRecordByIds")
    @ApiOperation("根据ID批量删除排班记录表")
    @OpLog(title = "根据ID批量删除排班记录表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveScheduleRecordByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        scheduleRecordService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
