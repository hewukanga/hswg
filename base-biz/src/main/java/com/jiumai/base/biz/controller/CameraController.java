package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.CameraDTO;
import com.jiumai.base.biz.query.CameraQuery;
import com.jiumai.base.biz.service.CameraService;
import com.jiumai.base.biz.vo.CameraVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * pm_camera 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/camera")
@Api(tags = {"pm_camera管理"})
public class CameraController {

    @Resource
    private CameraService cameraService;

    @PostMapping("findCameraPage")
    @ApiOperation("分页查询pm_camera")
    public ResultDTO<Page<CameraVO>> findCameraPage(HttpServletRequest request, @RequestBody CameraQuery query) {
        ResultDTO<Page<CameraVO>> result = new ResultDTO<>();
        Page<CameraVO> page = cameraService.findCameraPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateCamera")
    @ApiOperation("保存或更新pm_camera")
    @OpLog(title = "保存或更新pm_camera", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateCamera(HttpServletRequest request, @RequestBody CameraDTO cameraDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = cameraService.saveOrUpdateCamera(cameraDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getCameraById")
    @ApiOperation("根据ID获取pm_camera")
    public ResultDTO<CameraVO> getCameraById(Long id) {
        ResultDTO<CameraVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        CameraVO cameraVO = cameraService.getCameraById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", cameraVO);
    }

    @PostMapping("batchRemoveCameraByIds")
    @ApiOperation("根据ID批量删除pm_camera")
    @OpLog(title = "根据ID批量删除pm_camera", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveCameraByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        cameraService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
