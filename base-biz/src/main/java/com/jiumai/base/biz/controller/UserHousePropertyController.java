package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.UserHousePropertyDTO;
import com.jiumai.base.biz.query.UserHousePropertyQuery;
import com.jiumai.base.biz.service.UserHousePropertyService;
import com.jiumai.base.biz.vo.UserHousePropertyVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 业主关联房产表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/user-house-property")
@Api(tags = {"业主关联房产表管理"})
public class UserHousePropertyController {

    @Resource
    private UserHousePropertyService userHousePropertyService;

    @PostMapping("findUserHousePropertyPage")
    @ApiOperation("分页查询业主关联房产表")
    public ResultDTO<Page<UserHousePropertyVO>> findUserHousePropertyPage(HttpServletRequest request, @RequestBody UserHousePropertyQuery query) {
        ResultDTO<Page<UserHousePropertyVO>> result = new ResultDTO<>();
        Page<UserHousePropertyVO> page = userHousePropertyService.findUserHousePropertyPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateUserHouseProperty")
    @ApiOperation("保存或更新业主关联房产表")
    @OpLog(title = "保存或更新业主关联房产表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateUserHouseProperty(HttpServletRequest request, @RequestBody UserHousePropertyDTO userHousePropertyDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = userHousePropertyService.saveOrUpdateUserHouseProperty(userHousePropertyDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getUserHousePropertyById")
    @ApiOperation("根据ID获取业主关联房产表")
    public ResultDTO<UserHousePropertyVO> getUserHousePropertyById(Long id) {
        ResultDTO<UserHousePropertyVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        UserHousePropertyVO userHousePropertyVO = userHousePropertyService.getUserHousePropertyById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", userHousePropertyVO);
    }

    @PostMapping("batchRemoveUserHousePropertyByIds")
    @ApiOperation("根据ID批量删除业主关联房产表")
    @OpLog(title = "根据ID批量删除业主关联房产表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveUserHousePropertyByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        userHousePropertyService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
