package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PropertyDeviceDTO;
import com.jiumai.base.biz.query.PropertyDeviceQuery;
import com.jiumai.base.biz.service.PropertyDeviceService;
import com.jiumai.base.biz.vo.PropertyDeviceVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 物业设备表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/property-device")
@Api(tags = {"物业设备表管理"})
public class PropertyDeviceController {

    @Resource
    private PropertyDeviceService propertyDeviceService;

    @PostMapping("findPropertyDevicePage")
    @ApiOperation("分页查询物业设备表")
    public ResultDTO<Page<PropertyDeviceVO>> findPropertyDevicePage(HttpServletRequest request, @RequestBody PropertyDeviceQuery query) {
        ResultDTO<Page<PropertyDeviceVO>> result = new ResultDTO<>();
        Page<PropertyDeviceVO> page = propertyDeviceService.findPropertyDevicePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePropertyDevice")
    @ApiOperation("保存或更新物业设备表")
    @OpLog(title = "保存或更新物业设备表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePropertyDevice(HttpServletRequest request, @RequestBody PropertyDeviceDTO propertyDeviceDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = propertyDeviceService.saveOrUpdatePropertyDevice(propertyDeviceDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPropertyDeviceById")
    @ApiOperation("根据ID获取物业设备表")
    public ResultDTO<PropertyDeviceVO> getPropertyDeviceById(Long id) {
        ResultDTO<PropertyDeviceVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PropertyDeviceVO propertyDeviceVO = propertyDeviceService.getPropertyDeviceById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", propertyDeviceVO);
    }

    @PostMapping("batchRemovePropertyDeviceByIds")
    @ApiOperation("根据ID批量删除物业设备表")
    @OpLog(title = "根据ID批量删除物业设备表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePropertyDeviceByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        propertyDeviceService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
