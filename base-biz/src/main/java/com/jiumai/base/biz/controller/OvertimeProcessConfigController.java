package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OvertimeProcessConfigDTO;
import com.jiumai.base.biz.query.OvertimeProcessConfigQuery;
import com.jiumai.base.biz.service.OvertimeProcessConfigService;
import com.jiumai.base.biz.vo.OvertimeProcessConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 加班配置表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/overtime-process-config")
@Api(tags = {"加班配置表管理"})
public class OvertimeProcessConfigController {

    @Resource
    private OvertimeProcessConfigService overtimeProcessConfigService;

    @PostMapping("findOvertimeProcessConfigPage")
    @ApiOperation("分页查询加班配置表")
    public ResultDTO<Page<OvertimeProcessConfigVO>> findOvertimeProcessConfigPage(HttpServletRequest request, @RequestBody OvertimeProcessConfigQuery query) {
        ResultDTO<Page<OvertimeProcessConfigVO>> result = new ResultDTO<>();
        Page<OvertimeProcessConfigVO> page = overtimeProcessConfigService.findOvertimeProcessConfigPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateOvertimeProcessConfig")
    @ApiOperation("保存或更新加班配置表")
    @OpLog(title = "保存或更新加班配置表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateOvertimeProcessConfig(HttpServletRequest request, @RequestBody OvertimeProcessConfigDTO overtimeProcessConfigDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = overtimeProcessConfigService.saveOrUpdateOvertimeProcessConfig(overtimeProcessConfigDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getOvertimeProcessConfigById")
    @ApiOperation("根据ID获取加班配置表")
    public ResultDTO<OvertimeProcessConfigVO> getOvertimeProcessConfigById(Long id) {
        ResultDTO<OvertimeProcessConfigVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        OvertimeProcessConfigVO overtimeProcessConfigVO = overtimeProcessConfigService.getOvertimeProcessConfigById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", overtimeProcessConfigVO);
    }

    @PostMapping("batchRemoveOvertimeProcessConfigByIds")
    @ApiOperation("根据ID批量删除加班配置表")
    @OpLog(title = "根据ID批量删除加班配置表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveOvertimeProcessConfigByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        overtimeProcessConfigService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
