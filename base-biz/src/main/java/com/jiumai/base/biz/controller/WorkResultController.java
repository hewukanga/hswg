package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.WorkDispatchDTO;
import com.jiumai.base.biz.dto.WorkResultDTO;
import com.jiumai.base.biz.query.WorkResultQuery;
import com.jiumai.base.biz.service.WorkResultService;
import com.jiumai.base.biz.vo.WorkResultVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 工作成果 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-15
 */
@RestController
@RequestMapping("/d-admin/workResult")
@Api(tags = {"工作成果管理"})
public class WorkResultController {

    @Resource
    private WorkResultService workResultService;

    @PostMapping("findWorkResultPage")
    @ApiOperation("分页查询工作成果")
    public ResultDTO<Page<WorkResultVO>> findWorkResultPage(HttpServletRequest request, @RequestBody WorkResultQuery query) {
        ResultDTO<Page<WorkResultVO>> result = new ResultDTO<>();
        Page<WorkResultVO> page = workResultService.findWorkResultPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateWorkResult")
    @ApiOperation("保存或更新工作成果")
    @OpLog(title = "保存或更新工作成果", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateWorkResult(HttpServletRequest request, @RequestBody WorkResultDTO workResultDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = workResultService.saveOrUpdateWorkResult(workResultDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getWorkResultById")
    @ApiOperation("根据ID获取工作成果")
    public ResultDTO<WorkResultVO> getWorkResultById(Long id) {
        ResultDTO<WorkResultVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        WorkResultVO workResultVO = workResultService.getWorkResultById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", workResultVO);
    }

    @PostMapping("batchRemoveWorkResultByIds")
    @ApiOperation("根据ID批量删除工作成果")
    @OpLog(title = "根据ID批量删除工作成果", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveWorkResultByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        workResultService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }

    @PostMapping("dispatchWork")
    @ApiOperation("工作调度，更改工作所属执行人")
    @OpLog(title = "工作调度", businessType = BusinessTypeEnum.UPDATE, isSaveRequestData = true)
    public ResultDTO<String> dispatchWork(HttpServletRequest request, @RequestBody WorkDispatchDTO dispatchDTO) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(dispatchDTO) || CommonFuntions.isEmptyObject(dispatchDTO.getId())) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "调度失败,id为空");
        }
        Boolean success = workResultService.dispatchWork(dispatchDTO);
        if (success) {
            return result.set(ResultCodeEnum.SUCCESS, "调度成功");
        }
        return result.set(ResultCodeEnum.FAIL, "调度失败");
    }
}