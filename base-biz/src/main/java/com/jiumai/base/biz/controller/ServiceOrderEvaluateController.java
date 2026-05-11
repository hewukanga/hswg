package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ServiceOrderEvaluateDTO;
import com.jiumai.base.biz.query.ServiceOrderEvaluateQuery;
import com.jiumai.base.biz.service.ServiceOrderEvaluateService;
import com.jiumai.base.biz.vo.ServiceOrderEvaluateVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务工单评价表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/service-order-evaluate")
@Api(tags = {"服务工单评价表管理"})
public class ServiceOrderEvaluateController {

    @Resource
    private ServiceOrderEvaluateService serviceOrderEvaluateService;

    @PostMapping("findServiceOrderEvaluatePage")
    @ApiOperation("分页查询服务工单评价表")
    public ResultDTO<Page<ServiceOrderEvaluateVO>> findServiceOrderEvaluatePage(HttpServletRequest request, @RequestBody ServiceOrderEvaluateQuery query) {
        ResultDTO<Page<ServiceOrderEvaluateVO>> result = new ResultDTO<>();
        Page<ServiceOrderEvaluateVO> page = serviceOrderEvaluateService.findServiceOrderEvaluatePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateServiceOrderEvaluate")
    @ApiOperation("保存或更新服务工单评价表")
    @OpLog(title = "保存或更新服务工单评价表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateServiceOrderEvaluate(HttpServletRequest request, @RequestBody ServiceOrderEvaluateDTO serviceOrderEvaluateDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = serviceOrderEvaluateService.saveOrUpdateServiceOrderEvaluate(serviceOrderEvaluateDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getServiceOrderEvaluateById")
    @ApiOperation("根据ID获取服务工单评价表")
    public ResultDTO<ServiceOrderEvaluateVO> getServiceOrderEvaluateById(Long id) {
        ResultDTO<ServiceOrderEvaluateVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ServiceOrderEvaluateVO serviceOrderEvaluateVO = serviceOrderEvaluateService.getServiceOrderEvaluateById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", serviceOrderEvaluateVO);
    }

    @PostMapping("batchRemoveServiceOrderEvaluateByIds")
    @ApiOperation("根据ID批量删除服务工单评价表")
    @OpLog(title = "根据ID批量删除服务工单评价表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveServiceOrderEvaluateByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        serviceOrderEvaluateService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
