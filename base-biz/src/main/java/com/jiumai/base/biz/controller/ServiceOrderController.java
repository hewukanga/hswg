package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ServiceOrderDTO;
import com.jiumai.base.biz.query.ServiceOrderQuery;
import com.jiumai.base.biz.service.ServiceOrderService;
import com.jiumai.base.biz.vo.ServiceOrderVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务工单表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/service-order")
@Api(tags = {"服务工单表管理"})
public class ServiceOrderController {

    @Resource
    private ServiceOrderService serviceOrderService;

    @PostMapping("findServiceOrderPage")
    @ApiOperation("分页查询服务工单表")
    public ResultDTO<Page<ServiceOrderVO>> findServiceOrderPage(HttpServletRequest request, @RequestBody ServiceOrderQuery query) {
        ResultDTO<Page<ServiceOrderVO>> result = new ResultDTO<>();
        Page<ServiceOrderVO> page = serviceOrderService.findServiceOrderPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateServiceOrder")
    @ApiOperation("保存或更新服务工单表")
    @OpLog(title = "保存或更新服务工单表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateServiceOrder(HttpServletRequest request, @RequestBody ServiceOrderDTO serviceOrderDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = serviceOrderService.saveOrUpdateServiceOrder(serviceOrderDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getServiceOrderById")
    @ApiOperation("根据ID获取服务工单表")
    public ResultDTO<ServiceOrderVO> getServiceOrderById(Long id) {
        ResultDTO<ServiceOrderVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ServiceOrderVO serviceOrderVO = serviceOrderService.getServiceOrderById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", serviceOrderVO);
    }

    @PostMapping("batchRemoveServiceOrderByIds")
    @ApiOperation("根据ID批量删除服务工单表")
    @OpLog(title = "根据ID批量删除服务工单表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveServiceOrderByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        serviceOrderService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
