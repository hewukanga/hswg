package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PropertyWorkstationDTO;
import com.jiumai.base.biz.query.PropertyWorkstationQuery;
import com.jiumai.base.biz.service.PropertyWorkstationService;
import com.jiumai.base.biz.vo.PropertyWorkstationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 物业工作站 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/property-workstation")
@Api(tags = {"物业工作站管理"})
public class PropertyWorkstationController {

    @Resource
    private PropertyWorkstationService propertyWorkstationService;

    @PostMapping("findPropertyWorkstationPage")
    @ApiOperation("分页查询物业工作站")
    public ResultDTO<Page<PropertyWorkstationVO>> findPropertyWorkstationPage(HttpServletRequest request, @RequestBody PropertyWorkstationQuery query) {
        ResultDTO<Page<PropertyWorkstationVO>> result = new ResultDTO<>();
        Page<PropertyWorkstationVO> page = propertyWorkstationService.findPropertyWorkstationPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePropertyWorkstation")
    @ApiOperation("保存或更新物业工作站")
    @OpLog(title = "保存或更新物业工作站", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePropertyWorkstation(HttpServletRequest request, @RequestBody PropertyWorkstationDTO propertyWorkstationDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = propertyWorkstationService.saveOrUpdatePropertyWorkstation(propertyWorkstationDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPropertyWorkstationById")
    @ApiOperation("根据ID获取物业工作站")
    public ResultDTO<PropertyWorkstationVO> getPropertyWorkstationById(Long id) {
        ResultDTO<PropertyWorkstationVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PropertyWorkstationVO propertyWorkstationVO = propertyWorkstationService.getPropertyWorkstationById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", propertyWorkstationVO);
    }

    @PostMapping("batchRemovePropertyWorkstationByIds")
    @ApiOperation("根据ID批量删除物业工作站")
    @OpLog(title = "根据ID批量删除物业工作站", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePropertyWorkstationByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        propertyWorkstationService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
