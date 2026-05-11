package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ProcessRecordDTO;
import com.jiumai.base.biz.query.ProcessRecordQuery;
import com.jiumai.base.biz.service.ProcessRecordService;
import com.jiumai.base.biz.vo.ProcessRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 流程记录表(流程创建时生成全部记录) 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/process-record")
@Api(tags = {"流程记录表(流程创建时生成全部记录)管理"})
public class ProcessRecordController {

    @Resource
    private ProcessRecordService processRecordService;

    @PostMapping("findProcessRecordPage")
    @ApiOperation("分页查询流程记录表(流程创建时生成全部记录)")
    public ResultDTO<Page<ProcessRecordVO>> findProcessRecordPage(HttpServletRequest request, @RequestBody ProcessRecordQuery query) {
        ResultDTO<Page<ProcessRecordVO>> result = new ResultDTO<>();
        Page<ProcessRecordVO> page = processRecordService.findProcessRecordPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateProcessRecord")
    @ApiOperation("保存或更新流程记录表(流程创建时生成全部记录)")
    @OpLog(title = "保存或更新流程记录表(流程创建时生成全部记录)", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateProcessRecord(HttpServletRequest request, @RequestBody ProcessRecordDTO processRecordDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = processRecordService.saveOrUpdateProcessRecord(processRecordDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getProcessRecordById")
    @ApiOperation("根据ID获取流程记录表(流程创建时生成全部记录)")
    public ResultDTO<ProcessRecordVO> getProcessRecordById(Long id) {
        ResultDTO<ProcessRecordVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ProcessRecordVO processRecordVO = processRecordService.getProcessRecordById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", processRecordVO);
    }

    @PostMapping("batchRemoveProcessRecordByIds")
    @ApiOperation("根据ID批量删除流程记录表(流程创建时生成全部记录)")
    @OpLog(title = "根据ID批量删除流程记录表(流程创建时生成全部记录)", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveProcessRecordByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        processRecordService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
