package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ModuleOpenAreaConfigDTO;
import com.jiumai.base.biz.query.ModuleOpenAreaConfigQuery;
import com.jiumai.base.biz.service.ModuleOpenAreaConfigService;
import com.jiumai.base.biz.vo.ModuleOpenAreaConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 模块开放区域配置表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/module-open-area-config")
@Api(tags = {"模块开放区域配置表管理"})
public class ModuleOpenAreaConfigController {

    @Resource
    private ModuleOpenAreaConfigService moduleOpenAreaConfigService;

    @PostMapping("findModuleOpenAreaConfigPage")
    @ApiOperation("分页查询模块开放区域配置表")
    public ResultDTO<Page<ModuleOpenAreaConfigVO>> findModuleOpenAreaConfigPage(HttpServletRequest request, @RequestBody ModuleOpenAreaConfigQuery query) {
        ResultDTO<Page<ModuleOpenAreaConfigVO>> result = new ResultDTO<>();
        Page<ModuleOpenAreaConfigVO> page = moduleOpenAreaConfigService.findModuleOpenAreaConfigPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateModuleOpenAreaConfig")
    @ApiOperation("保存或更新模块开放区域配置表")
    @OpLog(title = "保存或更新模块开放区域配置表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateModuleOpenAreaConfig(HttpServletRequest request, @RequestBody ModuleOpenAreaConfigDTO moduleOpenAreaConfigDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = moduleOpenAreaConfigService.saveOrUpdateModuleOpenAreaConfig(moduleOpenAreaConfigDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getModuleOpenAreaConfigById")
    @ApiOperation("根据ID获取模块开放区域配置表")
    public ResultDTO<ModuleOpenAreaConfigVO> getModuleOpenAreaConfigById(Long id) {
        ResultDTO<ModuleOpenAreaConfigVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ModuleOpenAreaConfigVO moduleOpenAreaConfigVO = moduleOpenAreaConfigService.getModuleOpenAreaConfigById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", moduleOpenAreaConfigVO);
    }

    @PostMapping("batchRemoveModuleOpenAreaConfigByIds")
    @ApiOperation("根据ID批量删除模块开放区域配置表")
    @OpLog(title = "根据ID批量删除模块开放区域配置表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveModuleOpenAreaConfigByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        moduleOpenAreaConfigService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
