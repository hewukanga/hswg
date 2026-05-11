package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.UserOpinionDTO;
import com.jiumai.base.biz.query.UserOpinionQuery;
import com.jiumai.base.biz.service.UserOpinionService;
import com.jiumai.base.biz.vo.UserOpinionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 居民意见表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/user-opinion")
@Api(tags = {"居民意见表管理"})
public class UserOpinionController {

    @Resource
    private UserOpinionService userOpinionService;

    @PostMapping("findUserOpinionPage")
    @ApiOperation("分页查询居民意见表")
    public ResultDTO<Page<UserOpinionVO>> findUserOpinionPage(HttpServletRequest request, @RequestBody UserOpinionQuery query) {
        ResultDTO<Page<UserOpinionVO>> result = new ResultDTO<>();
        Page<UserOpinionVO> page = userOpinionService.findUserOpinionPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateUserOpinion")
    @ApiOperation("保存或更新居民意见表")
    @OpLog(title = "保存或更新居民意见表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateUserOpinion(HttpServletRequest request, @RequestBody UserOpinionDTO userOpinionDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = userOpinionService.saveOrUpdateUserOpinion(userOpinionDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getUserOpinionById")
    @ApiOperation("根据ID获取居民意见表")
    public ResultDTO<UserOpinionVO> getUserOpinionById(Long id) {
        ResultDTO<UserOpinionVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        UserOpinionVO userOpinionVO = userOpinionService.getUserOpinionById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", userOpinionVO);
    }

    @PostMapping("batchRemoveUserOpinionByIds")
    @ApiOperation("根据ID批量删除居民意见表")
    @OpLog(title = "根据ID批量删除居民意见表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveUserOpinionByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        userOpinionService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
