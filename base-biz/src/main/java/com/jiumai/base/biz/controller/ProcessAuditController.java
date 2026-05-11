package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ProcessAuditDTO;
import com.jiumai.base.biz.query.ProcessAuditQuery;
import com.jiumai.base.biz.service.ProcessAuditService;
import com.jiumai.base.biz.vo.ProcessAuditVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 流程审批表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/process-audit")
@Api(tags = {"流程审批表管理"})
public class ProcessAuditController {

    @Resource
    private ProcessAuditService processAuditService;

    @PostMapping("findProcessAuditPage")
    @ApiOperation("分页查询流程审批表")
    public ResultDTO<Page<ProcessAuditVO>> findProcessAuditPage(HttpServletRequest request, @RequestBody ProcessAuditQuery query) {
        ResultDTO<Page<ProcessAuditVO>> result = new ResultDTO<>();
        Page<ProcessAuditVO> page = processAuditService.findProcessAuditPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateProcessAudit")
    @ApiOperation("保存或更新流程审批表")
    @OpLog(title = "保存或更新流程审批表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateProcessAudit(HttpServletRequest request, @RequestBody ProcessAuditDTO processAuditDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = processAuditService.saveOrUpdateProcessAudit(processAuditDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getProcessAuditById")
    @ApiOperation("根据ID获取流程审批表")
    public ResultDTO<ProcessAuditVO> getProcessAuditById(Long id) {
        ResultDTO<ProcessAuditVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ProcessAuditVO processAuditVO = processAuditService.getProcessAuditById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", processAuditVO);
    }

    @PostMapping("batchRemoveProcessAuditByIds")
    @ApiOperation("根据ID批量删除流程审批表")
    @OpLog(title = "根据ID批量删除流程审批表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveProcessAuditByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        processAuditService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
