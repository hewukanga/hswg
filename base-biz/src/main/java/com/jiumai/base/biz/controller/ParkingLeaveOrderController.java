package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ParkingLeaveOrderDTO;
import com.jiumai.base.biz.query.ParkingLeaveOrderQuery;
import com.jiumai.base.biz.service.ParkingLeaveOrderService;
import com.jiumai.base.biz.vo.ParkingLeaveOrderVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 停车离场订单表(数泊) 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/parking-leave-order")
@Api(tags = {"停车离场订单表(数泊)管理"})
public class ParkingLeaveOrderController {

    @Resource
    private ParkingLeaveOrderService parkingLeaveOrderService;

    @PostMapping("findParkingLeaveOrderPage")
    @ApiOperation("分页查询停车离场订单表(数泊)")
    public ResultDTO<Page<ParkingLeaveOrderVO>> findParkingLeaveOrderPage(HttpServletRequest request, @RequestBody ParkingLeaveOrderQuery query) {
        ResultDTO<Page<ParkingLeaveOrderVO>> result = new ResultDTO<>();
        Page<ParkingLeaveOrderVO> page = parkingLeaveOrderService.findParkingLeaveOrderPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateParkingLeaveOrder")
    @ApiOperation("保存或更新停车离场订单表(数泊)")
    @OpLog(title = "保存或更新停车离场订单表(数泊)", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateParkingLeaveOrder(HttpServletRequest request, @RequestBody ParkingLeaveOrderDTO parkingLeaveOrderDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = parkingLeaveOrderService.saveOrUpdateParkingLeaveOrder(parkingLeaveOrderDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getParkingLeaveOrderById")
    @ApiOperation("根据ID获取停车离场订单表(数泊)")
    public ResultDTO<ParkingLeaveOrderVO> getParkingLeaveOrderById(Long id) {
        ResultDTO<ParkingLeaveOrderVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ParkingLeaveOrderVO parkingLeaveOrderVO = parkingLeaveOrderService.getParkingLeaveOrderById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", parkingLeaveOrderVO);
    }

    @PostMapping("batchRemoveParkingLeaveOrderByIds")
    @ApiOperation("根据ID批量删除停车离场订单表(数泊)")
    @OpLog(title = "根据ID批量删除停车离场订单表(数泊)", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveParkingLeaveOrderByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        parkingLeaveOrderService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
