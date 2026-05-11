package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OvertimeProcessFlowDTO;
import com.jiumai.base.biz.query.OvertimeProcessFlowQuery;
import com.jiumai.base.biz.service.OvertimeProcessFlowService;
import com.jiumai.base.biz.vo.OvertimeProcessFlowVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 加班流程表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/overtime-process-flow")
@Api(tags = {"加班流程表管理"})
public class OvertimeProcessFlowController {

    @Resource
    private OvertimeProcessFlowService overtimeProcessFlowService;

    @PostMapping("findOvertimeProcessFlowPage")
    @ApiOperation("分页查询加班流程表")
    public ResultDTO<Page<OvertimeProcessFlowVO>> findOvertimeProcessFlowPage(HttpServletRequest request, @RequestBody OvertimeProcessFlowQuery query) {
        ResultDTO<Page<OvertimeProcessFlowVO>> result = new ResultDTO<>();
        Page<OvertimeProcessFlowVO> page = overtimeProcessFlowService.findOvertimeProcessFlowPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateOvertimeProcessFlow")
    @ApiOperation("保存或更新加班流程表")
    @OpLog(title = "保存或更新加班流程表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateOvertimeProcessFlow(HttpServletRequest request, @RequestBody OvertimeProcessFlowDTO overtimeProcessFlowDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = overtimeProcessFlowService.saveOrUpdateOvertimeProcessFlow(overtimeProcessFlowDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getOvertimeProcessFlowById")
    @ApiOperation("根据ID获取加班流程表")
    public ResultDTO<OvertimeProcessFlowVO> getOvertimeProcessFlowById(Long id) {
        ResultDTO<OvertimeProcessFlowVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        OvertimeProcessFlowVO overtimeProcessFlowVO = overtimeProcessFlowService.getOvertimeProcessFlowById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", overtimeProcessFlowVO);
    }

    @PostMapping("batchRemoveOvertimeProcessFlowByIds")
    @ApiOperation("根据ID批量删除加班流程表")
    @OpLog(title = "根据ID批量删除加班流程表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveOvertimeProcessFlowByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        overtimeProcessFlowService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
