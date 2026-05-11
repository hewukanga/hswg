package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.IntegralConfigDTO;
import com.jiumai.base.biz.query.IntegralConfigQuery;
import com.jiumai.base.biz.service.IntegralConfigService;
import com.jiumai.base.biz.vo.IntegralConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 积分配置表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/integral-config")
@Api(tags = {"积分配置表管理"})
public class IntegralConfigController {

    @Resource
    private IntegralConfigService integralConfigService;

    @PostMapping("findIntegralConfigPage")
    @ApiOperation("分页查询积分配置表")
    public ResultDTO<Page<IntegralConfigVO>> findIntegralConfigPage(HttpServletRequest request, @RequestBody IntegralConfigQuery query) {
        ResultDTO<Page<IntegralConfigVO>> result = new ResultDTO<>();
        Page<IntegralConfigVO> page = integralConfigService.findIntegralConfigPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateIntegralConfig")
    @ApiOperation("保存或更新积分配置表")
    @OpLog(title = "保存或更新积分配置表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateIntegralConfig(HttpServletRequest request, @RequestBody IntegralConfigDTO integralConfigDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = integralConfigService.saveOrUpdateIntegralConfig(integralConfigDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getIntegralConfigById")
    @ApiOperation("根据ID获取积分配置表")
    public ResultDTO<IntegralConfigVO> getIntegralConfigById(Long id) {
        ResultDTO<IntegralConfigVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        IntegralConfigVO integralConfigVO = integralConfigService.getIntegralConfigById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", integralConfigVO);
    }

    @PostMapping("batchRemoveIntegralConfigByIds")
    @ApiOperation("根据ID批量删除积分配置表")
    @OpLog(title = "根据ID批量删除积分配置表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveIntegralConfigByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        integralConfigService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
