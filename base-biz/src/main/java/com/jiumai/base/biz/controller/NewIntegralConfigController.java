package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.NewIntegralConfigDTO;
import com.jiumai.base.biz.query.NewIntegralConfigQuery;
import com.jiumai.base.biz.service.NewIntegralConfigService;
import com.jiumai.base.biz.vo.NewIntegralConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 新积分配置表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/new-integral-config")
@Api(tags = {"新积分配置表管理"})
public class NewIntegralConfigController {

    @Resource
    private NewIntegralConfigService newIntegralConfigService;

    @PostMapping("findNewIntegralConfigPage")
    @ApiOperation("分页查询新积分配置表")
    public ResultDTO<Page<NewIntegralConfigVO>> findNewIntegralConfigPage(HttpServletRequest request, @RequestBody NewIntegralConfigQuery query) {
        ResultDTO<Page<NewIntegralConfigVO>> result = new ResultDTO<>();
        Page<NewIntegralConfigVO> page = newIntegralConfigService.findNewIntegralConfigPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateNewIntegralConfig")
    @ApiOperation("保存或更新新积分配置表")
    @OpLog(title = "保存或更新新积分配置表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateNewIntegralConfig(HttpServletRequest request, @RequestBody NewIntegralConfigDTO newIntegralConfigDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = newIntegralConfigService.saveOrUpdateNewIntegralConfig(newIntegralConfigDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getNewIntegralConfigById")
    @ApiOperation("根据ID获取新积分配置表")
    public ResultDTO<NewIntegralConfigVO> getNewIntegralConfigById(Long id) {
        ResultDTO<NewIntegralConfigVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        NewIntegralConfigVO newIntegralConfigVO = newIntegralConfigService.getNewIntegralConfigById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", newIntegralConfigVO);
    }

    @PostMapping("batchRemoveNewIntegralConfigByIds")
    @ApiOperation("根据ID批量删除新积分配置表")
    @OpLog(title = "根据ID批量删除新积分配置表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveNewIntegralConfigByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        newIntegralConfigService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
