package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PropertyDeviceRecordDTO;
import com.jiumai.base.biz.query.PropertyDeviceRecordQuery;
import com.jiumai.base.biz.service.PropertyDeviceRecordService;
import com.jiumai.base.biz.vo.PropertyDeviceRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 物业设备领用记录 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/property-device-record")
@Api(tags = {"物业设备领用记录管理"})
public class PropertyDeviceRecordController {

    @Resource
    private PropertyDeviceRecordService propertyDeviceRecordService;

    @PostMapping("findPropertyDeviceRecordPage")
    @ApiOperation("分页查询物业设备领用记录")
    public ResultDTO<Page<PropertyDeviceRecordVO>> findPropertyDeviceRecordPage(HttpServletRequest request, @RequestBody PropertyDeviceRecordQuery query) {
        ResultDTO<Page<PropertyDeviceRecordVO>> result = new ResultDTO<>();
        Page<PropertyDeviceRecordVO> page = propertyDeviceRecordService.findPropertyDeviceRecordPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePropertyDeviceRecord")
    @ApiOperation("保存或更新物业设备领用记录")
    @OpLog(title = "保存或更新物业设备领用记录", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePropertyDeviceRecord(HttpServletRequest request, @RequestBody PropertyDeviceRecordDTO propertyDeviceRecordDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = propertyDeviceRecordService.saveOrUpdatePropertyDeviceRecord(propertyDeviceRecordDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPropertyDeviceRecordById")
    @ApiOperation("根据ID获取物业设备领用记录")
    public ResultDTO<PropertyDeviceRecordVO> getPropertyDeviceRecordById(Long id) {
        ResultDTO<PropertyDeviceRecordVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PropertyDeviceRecordVO propertyDeviceRecordVO = propertyDeviceRecordService.getPropertyDeviceRecordById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", propertyDeviceRecordVO);
    }

    @PostMapping("batchRemovePropertyDeviceRecordByIds")
    @ApiOperation("根据ID批量删除物业设备领用记录")
    @OpLog(title = "根据ID批量删除物业设备领用记录", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePropertyDeviceRecordByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        propertyDeviceRecordService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
