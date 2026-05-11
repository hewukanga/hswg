package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CamerasLocationDTO;
import com.jiumai.base.biz.query.CamerasLocationQuery;
import com.jiumai.base.biz.service.CamerasLocationService;
import com.jiumai.base.biz.vo.CamerasLocationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 执法仪经纬度记录表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/cameras-location")
@Api(tags = {"执法仪经纬度记录表管理"})
public class CamerasLocationController {

    @Resource
    private CamerasLocationService camerasLocationService;

    @PostMapping("findCamerasLocationPage")
    @ApiOperation("分页查询执法仪经纬度记录表")
    public ResultDTO<Page<CamerasLocationVO>> findCamerasLocationPage(HttpServletRequest request, @RequestBody CamerasLocationQuery query) {
        ResultDTO<Page<CamerasLocationVO>> result = new ResultDTO<>();
        Page<CamerasLocationVO> page = camerasLocationService.findCamerasLocationPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateCamerasLocation")
    @ApiOperation("保存或更新执法仪经纬度记录表")
    @OpLog(title = "保存或更新执法仪经纬度记录表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<String> saveOrUpdateCamerasLocation(HttpServletRequest request, @RequestBody CamerasLocationDTO camerasLocationDTO) {
        ResultDTO<String> result = new ResultDTO<>();
        String id = camerasLocationService.saveOrUpdateCamerasLocation(camerasLocationDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getCamerasLocationById")
    @ApiOperation("根据ID获取执法仪经纬度记录表")
    public ResultDTO<CamerasLocationVO> getCamerasLocationById(String id) {
        ResultDTO<CamerasLocationVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        CamerasLocationVO camerasLocationVO = camerasLocationService.getCamerasLocationById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", camerasLocationVO);
    }

    @PostMapping("batchRemoveCamerasLocationByIds")
    @ApiOperation("根据ID批量删除执法仪经纬度记录表")
    @OpLog(title = "根据ID批量删除执法仪经纬度记录表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveCamerasLocationByIds(@RequestBody List<String> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        camerasLocationService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
