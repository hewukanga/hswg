package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ActivityDTO;
import com.jiumai.base.biz.query.ActivityQuery;
import com.jiumai.base.biz.service.ActivityService;
import com.jiumai.base.biz.vo.ActivityVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * activity 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/activity")
@Api(tags = {"activity管理"})
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @PostMapping("findActivityPage")
    @ApiOperation("分页查询activity")
    public ResultDTO<Page<ActivityVO>> findActivityPage(HttpServletRequest request, @RequestBody ActivityQuery query) {
        ResultDTO<Page<ActivityVO>> result = new ResultDTO<>();
        Page<ActivityVO> page = activityService.findActivityPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateActivity")
    @ApiOperation("保存或更新activity")
    @OpLog(title = "保存或更新activity", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateActivity(HttpServletRequest request, @RequestBody ActivityDTO activityDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = activityService.saveOrUpdateActivity(activityDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getActivityById")
    @ApiOperation("根据ID获取activity")
    public ResultDTO<ActivityVO> getActivityById(Long id) {
        ResultDTO<ActivityVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ActivityVO activityVO = activityService.getActivityById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", activityVO);
    }

    @PostMapping("batchRemoveActivityByIds")
    @ApiOperation("根据ID批量删除activity")
    @OpLog(title = "根据ID批量删除activity", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveActivityByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        activityService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }

    @PostMapping("offlineActivityByIds")
    @ApiOperation("根据ID批量下架activity")
    @OpLog(title = "根据ID批量下架activity", businessType = BusinessTypeEnum.UPDATE, isSaveRequestData = true)
    public ResultDTO<String> offlineActivityByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "下架失败,ids为空");
        }
        Boolean success = activityService.offlineActivityByIds(ids);
        if (success) {
            return result.set(ResultCodeEnum.SUCCESS, "下架成功");
        }
        return result.set(ResultCodeEnum.FAIL, "下架失败");
    }
}
