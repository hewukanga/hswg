package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CleaningClockInRecordDTO;
import com.jiumai.base.biz.query.CleaningClockInRecordQuery;
import com.jiumai.base.biz.service.CleaningClockInRecordService;
import com.jiumai.base.biz.vo.CleaningClockInRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 保洁打卡记录 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/cleaning-clock-in-record")
@Api(tags = {"保洁打卡记录管理"})
public class CleaningClockInRecordController {

    @Resource
    private CleaningClockInRecordService cleaningClockInRecordService;

    @PostMapping("findCleaningClockInRecordPage")
    @ApiOperation("分页查询保洁打卡记录")
    public ResultDTO<Page<CleaningClockInRecordVO>> findCleaningClockInRecordPage(HttpServletRequest request, @RequestBody CleaningClockInRecordQuery query) {
        ResultDTO<Page<CleaningClockInRecordVO>> result = new ResultDTO<>();
        Page<CleaningClockInRecordVO> page = cleaningClockInRecordService.findCleaningClockInRecordPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateCleaningClockInRecord")
    @ApiOperation("保存或更新保洁打卡记录")
    @OpLog(title = "保存或更新保洁打卡记录", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateCleaningClockInRecord(HttpServletRequest request, @RequestBody CleaningClockInRecordDTO cleaningClockInRecordDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = cleaningClockInRecordService.saveOrUpdateCleaningClockInRecord(cleaningClockInRecordDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getCleaningClockInRecordById")
    @ApiOperation("根据ID获取保洁打卡记录")
    public ResultDTO<CleaningClockInRecordVO> getCleaningClockInRecordById(Long id) {
        ResultDTO<CleaningClockInRecordVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        CleaningClockInRecordVO cleaningClockInRecordVO = cleaningClockInRecordService.getCleaningClockInRecordById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", cleaningClockInRecordVO);
    }

    @PostMapping("batchRemoveCleaningClockInRecordByIds")
    @ApiOperation("根据ID批量删除保洁打卡记录")
    @OpLog(title = "根据ID批量删除保洁打卡记录", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveCleaningClockInRecordByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        cleaningClockInRecordService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
