package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ActivitySignUpDTO;
import com.jiumai.base.biz.query.ActivitySignUpQuery;
import com.jiumai.base.biz.service.ActivitySignUpService;
import com.jiumai.base.biz.vo.ActivitySignUpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 活动报名表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/activity-sign-up")
@Api(tags = {"活动报名表管理"})
public class ActivitySignUpController {

    @Resource
    private ActivitySignUpService activitySignUpService;

    @PostMapping("findActivitySignUpPage")
    @ApiOperation("分页查询活动报名表")
    public ResultDTO<Page<ActivitySignUpVO>> findActivitySignUpPage(HttpServletRequest request, @RequestBody ActivitySignUpQuery query) {
        ResultDTO<Page<ActivitySignUpVO>> result = new ResultDTO<>();
        Page<ActivitySignUpVO> page = activitySignUpService.findActivitySignUpPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateActivitySignUp")
    @ApiOperation("保存或更新活动报名表")
    @OpLog(title = "保存或更新活动报名表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateActivitySignUp(HttpServletRequest request, @RequestBody ActivitySignUpDTO activitySignUpDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = activitySignUpService.saveOrUpdateActivitySignUp(activitySignUpDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @PostMapping("signUpActivity")
    @ApiOperation("居民端活动报名")
    public ResultDTO<Long> signUpActivity(HttpServletRequest request, @RequestBody ActivitySignUpDTO activitySignUpDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = activitySignUpService.signUpActivity(activitySignUpDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "报名成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "报名失败");
    }

    @GetMapping("getActivitySignUpById")
    @ApiOperation("根据ID获取活动报名表")
    public ResultDTO<ActivitySignUpVO> getActivitySignUpById(Long id) {
        ResultDTO<ActivitySignUpVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ActivitySignUpVO activitySignUpVO = activitySignUpService.getActivitySignUpById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", activitySignUpVO);
    }

    @PostMapping("batchRemoveActivitySignUpByIds")
    @ApiOperation("根据ID批量删除活动报名表")
    @OpLog(title = "根据ID批量删除活动报名表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveActivitySignUpByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        activitySignUpService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
