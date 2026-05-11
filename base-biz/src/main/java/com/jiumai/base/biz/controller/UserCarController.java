package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.UserCarDTO;
import com.jiumai.base.biz.query.UserCarQuery;
import com.jiumai.base.biz.service.UserCarService;
import com.jiumai.base.biz.vo.UserCarVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 用户车辆管理 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/user-car")
@Api(tags = {"用户车辆管理管理"})
public class UserCarController {

    @Resource
    private UserCarService userCarService;

    @PostMapping("findUserCarPage")
    @ApiOperation("分页查询用户车辆管理")
    public ResultDTO<Page<UserCarVO>> findUserCarPage(HttpServletRequest request, @RequestBody UserCarQuery query) {
        ResultDTO<Page<UserCarVO>> result = new ResultDTO<>();
        Page<UserCarVO> page = userCarService.findUserCarPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateUserCar")
    @ApiOperation("保存或更新用户车辆管理")
    @OpLog(title = "保存或更新用户车辆管理", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateUserCar(HttpServletRequest request, @RequestBody UserCarDTO userCarDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = userCarService.saveOrUpdateUserCar(userCarDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getUserCarById")
    @ApiOperation("根据ID获取用户车辆管理")
    public ResultDTO<UserCarVO> getUserCarById(Long id) {
        ResultDTO<UserCarVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        UserCarVO userCarVO = userCarService.getUserCarById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", userCarVO);
    }

    @PostMapping("batchRemoveUserCarByIds")
    @ApiOperation("根据ID批量删除用户车辆管理")
    @OpLog(title = "根据ID批量删除用户车辆管理", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveUserCarByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        userCarService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
