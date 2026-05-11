package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PostPersonInfoDTO;
import com.jiumai.base.biz.query.PostPersonInfoQuery;
import com.jiumai.base.biz.service.PostPersonInfoService;
import com.jiumai.base.biz.vo.PostPersonInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 岗位人员信息表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/post-person-info")
@Api(tags = {"岗位人员信息表管理"})
public class PostPersonInfoController {

    @Resource
    private PostPersonInfoService postPersonInfoService;

    @PostMapping("findPostPersonInfoPage")
    @ApiOperation("分页查询岗位人员信息表")
    public ResultDTO<Page<PostPersonInfoVO>> findPostPersonInfoPage(HttpServletRequest request, @RequestBody PostPersonInfoQuery query) {
        ResultDTO<Page<PostPersonInfoVO>> result = new ResultDTO<>();
        Page<PostPersonInfoVO> page = postPersonInfoService.findPostPersonInfoPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePostPersonInfo")
    @ApiOperation("保存或更新岗位人员信息表")
    @OpLog(title = "保存或更新岗位人员信息表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePostPersonInfo(HttpServletRequest request, @RequestBody PostPersonInfoDTO postPersonInfoDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = postPersonInfoService.saveOrUpdatePostPersonInfo(postPersonInfoDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPostPersonInfoById")
    @ApiOperation("根据ID获取岗位人员信息表")
    public ResultDTO<PostPersonInfoVO> getPostPersonInfoById(Long id) {
        ResultDTO<PostPersonInfoVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PostPersonInfoVO postPersonInfoVO = postPersonInfoService.getPostPersonInfoById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", postPersonInfoVO);
    }

    @PostMapping("batchRemovePostPersonInfoByIds")
    @ApiOperation("根据ID批量删除岗位人员信息表")
    @OpLog(title = "根据ID批量删除岗位人员信息表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePostPersonInfoByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        postPersonInfoService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
