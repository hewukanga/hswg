package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ParkingLotDTO;
import com.jiumai.base.biz.query.ParkingLotQuery;
import com.jiumai.base.biz.service.ParkingLotService;
import com.jiumai.base.biz.vo.ParkingLotVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 停车场(数泊推送) 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/parking-lot")
@Api(tags = {"停车场(数泊推送)管理"})
public class ParkingLotController {

    @Resource
    private ParkingLotService parkingLotService;

    @PostMapping("findParkingLotPage")
    @ApiOperation("分页查询停车场(数泊推送)")
    public ResultDTO<Page<ParkingLotVO>> findParkingLotPage(HttpServletRequest request, @RequestBody ParkingLotQuery query) {
        ResultDTO<Page<ParkingLotVO>> result = new ResultDTO<>();
        Page<ParkingLotVO> page = parkingLotService.findParkingLotPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateParkingLot")
    @ApiOperation("保存或更新停车场(数泊推送)")
    @OpLog(title = "保存或更新停车场(数泊推送)", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateParkingLot(HttpServletRequest request, @RequestBody ParkingLotDTO parkingLotDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = parkingLotService.saveOrUpdateParkingLot(parkingLotDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getParkingLotById")
    @ApiOperation("根据ID获取停车场(数泊推送)")
    public ResultDTO<ParkingLotVO> getParkingLotById(Long id) {
        ResultDTO<ParkingLotVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ParkingLotVO parkingLotVO = parkingLotService.getParkingLotById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", parkingLotVO);
    }

    @PostMapping("batchRemoveParkingLotByIds")
    @ApiOperation("根据ID批量删除停车场(数泊推送)")
    @OpLog(title = "根据ID批量删除停车场(数泊推送)", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveParkingLotByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        parkingLotService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
