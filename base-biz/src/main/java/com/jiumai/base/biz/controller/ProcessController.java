package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ProcessDTO;
import com.jiumai.base.biz.query.ProcessQuery;
import com.jiumai.base.biz.service.ProcessService;
import com.jiumai.base.biz.vo.ProcessVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 流程表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/process")
@Api(tags = {"流程表管理"})
public class ProcessController {

    @Resource
    private ProcessService processService;

    @PostMapping("findProcessPage")
    @ApiOperation("分页查询流程表")
    public ResultDTO<Page<ProcessVO>> findProcessPage(HttpServletRequest request, @RequestBody ProcessQuery query) {
        ResultDTO<Page<ProcessVO>> result = new ResultDTO<>();
        Page<ProcessVO> page = processService.findProcessPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateProcess")
    @ApiOperation("保存或更新流程表")
    @OpLog(title = "保存或更新流程表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateProcess(HttpServletRequest request, @RequestBody ProcessDTO processDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = processService.saveOrUpdateProcess(processDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getProcessById")
    @ApiOperation("根据ID获取流程表")
    public ResultDTO<ProcessVO> getProcessById(Long id) {
        ResultDTO<ProcessVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ProcessVO processVO = processService.getProcessById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", processVO);
    }

    @PostMapping("batchRemoveProcessByIds")
    @ApiOperation("根据ID批量删除流程表")
    @OpLog(title = "根据ID批量删除流程表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveProcessByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        processService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
