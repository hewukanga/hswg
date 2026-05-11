package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OrderDTO;
import com.jiumai.base.biz.query.OrderQuery;
import com.jiumai.base.biz.service.OrderService;
import com.jiumai.base.biz.vo.OrderVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 订单表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/order")
@Api(tags = {"订单表管理"})
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("findOrderPage")
    @ApiOperation("分页查询订单表")
    public ResultDTO<Page<OrderVO>> findOrderPage(HttpServletRequest request, @RequestBody OrderQuery query) {
        ResultDTO<Page<OrderVO>> result = new ResultDTO<>();
        Page<OrderVO> page = orderService.findOrderPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateOrder")
    @ApiOperation("保存或更新订单表")
    @OpLog(title = "保存或更新订单表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateOrder(HttpServletRequest request, @RequestBody OrderDTO orderDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = orderService.saveOrUpdateOrder(orderDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getOrderById")
    @ApiOperation("根据ID获取订单表")
    public ResultDTO<OrderVO> getOrderById(Long id) {
        ResultDTO<OrderVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        OrderVO orderVO = orderService.getOrderById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", orderVO);
    }

    @PostMapping("batchRemoveOrderByIds")
    @ApiOperation("根据ID批量删除订单表")
    @OpLog(title = "根据ID批量删除订单表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveOrderByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        orderService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
