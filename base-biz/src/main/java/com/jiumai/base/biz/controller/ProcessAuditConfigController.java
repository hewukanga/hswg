package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ProcessAuditConfigDTO;
import com.jiumai.base.biz.query.ProcessAuditConfigQuery;
import com.jiumai.base.biz.service.ProcessAuditConfigService;
import com.jiumai.base.biz.vo.ProcessAuditConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 流程审批配置表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/process-audit-config")
@Api(tags = {"流程审批配置表管理"})
public class ProcessAuditConfigController {

    @Resource
    private ProcessAuditConfigService processAuditConfigService;

    @PostMapping("findProcessAuditConfigPage")
    @ApiOperation("分页查询流程审批配置表")
    public ResultDTO<Page<ProcessAuditConfigVO>> findProcessAuditConfigPage(HttpServletRequest request, @RequestBody ProcessAuditConfigQuery query) {
        ResultDTO<Page<ProcessAuditConfigVO>> result = new ResultDTO<>();
        Page<ProcessAuditConfigVO> page = processAuditConfigService.findProcessAuditConfigPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateProcessAuditConfig")
    @ApiOperation("保存或更新流程审批配置表")
    @OpLog(title = "保存或更新流程审批配置表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateProcessAuditConfig(HttpServletRequest request, @RequestBody ProcessAuditConfigDTO processAuditConfigDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = processAuditConfigService.saveOrUpdateProcessAuditConfig(processAuditConfigDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getProcessAuditConfigById")
    @ApiOperation("根据ID获取流程审批配置表")
    public ResultDTO<ProcessAuditConfigVO> getProcessAuditConfigById(Long id) {
        ResultDTO<ProcessAuditConfigVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ProcessAuditConfigVO processAuditConfigVO = processAuditConfigService.getProcessAuditConfigById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", processAuditConfigVO);
    }

    @PostMapping("batchRemoveProcessAuditConfigByIds")
    @ApiOperation("根据ID批量删除流程审批配置表")
    @OpLog(title = "根据ID批量删除流程审批配置表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveProcessAuditConfigByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        processAuditConfigService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
