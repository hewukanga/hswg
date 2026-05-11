package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PropertyAreaDTO;
import com.jiumai.base.biz.query.PropertyAreaQuery;
import com.jiumai.base.biz.service.PropertyAreaService;
import com.jiumai.base.biz.vo.PropertyAreaVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 物业区域表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/property-area")
@Api(tags = {"物业区域表管理"})
public class PropertyAreaController {

    @Resource
    private PropertyAreaService propertyAreaService;

    @PostMapping("findPropertyAreaPage")
    @ApiOperation("分页查询物业区域表")
    public ResultDTO<Page<PropertyAreaVO>> findPropertyAreaPage(HttpServletRequest request, @RequestBody PropertyAreaQuery query) {
        ResultDTO<Page<PropertyAreaVO>> result = new ResultDTO<>();
        Page<PropertyAreaVO> page = propertyAreaService.findPropertyAreaPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePropertyArea")
    @ApiOperation("保存或更新物业区域表")
    @OpLog(title = "保存或更新物业区域表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePropertyArea(HttpServletRequest request, @RequestBody PropertyAreaDTO propertyAreaDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = propertyAreaService.saveOrUpdatePropertyArea(propertyAreaDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPropertyAreaById")
    @ApiOperation("根据ID获取物业区域表")
    public ResultDTO<PropertyAreaVO> getPropertyAreaById(Long id) {
        ResultDTO<PropertyAreaVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PropertyAreaVO propertyAreaVO = propertyAreaService.getPropertyAreaById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", propertyAreaVO);
    }

    @PostMapping("batchRemovePropertyAreaByIds")
    @ApiOperation("根据ID批量删除物业区域表")
    @OpLog(title = "根据ID批量删除物业区域表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePropertyAreaByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        propertyAreaService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
