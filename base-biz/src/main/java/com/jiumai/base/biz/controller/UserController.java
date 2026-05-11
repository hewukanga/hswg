package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.UserDTO;
import com.jiumai.base.biz.query.UserQuery;
import com.jiumai.base.biz.service.UserService;
import com.jiumai.base.biz.vo.UserVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 业主表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/user")
@Api(tags = {"业主表管理"})
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("findUserPage")
    @ApiOperation("分页查询业主表")
    public ResultDTO<Page<UserVO>> findUserPage(HttpServletRequest request, @RequestBody UserQuery query) {
        ResultDTO<Page<UserVO>> result = new ResultDTO<>();
        Page<UserVO> page = userService.findUserPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateUser")
    @ApiOperation("保存或更新业主表")
    @OpLog(title = "保存或更新业主表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateUser(HttpServletRequest request, @RequestBody UserDTO userDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = userService.saveOrUpdateUser(userDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getUserById")
    @ApiOperation("根据ID获取业主表")
    public ResultDTO<UserVO> getUserById(Long id) {
        ResultDTO<UserVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        UserVO userVO = userService.getUserById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", userVO);
    }

    @PostMapping("batchRemoveUserByIds")
    @ApiOperation("根据ID批量删除业主表")
    @OpLog(title = "根据ID批量删除业主表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveUserByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        userService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
