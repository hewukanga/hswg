package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PostInfoDTO;
import com.jiumai.base.biz.query.PostInfoQuery;
import com.jiumai.base.biz.service.PostInfoService;
import com.jiumai.base.biz.vo.PostInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 岗位信息表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/post-info")
@Api(tags = {"岗位信息表管理"})
public class PostInfoController {

    @Resource
    private PostInfoService postInfoService;

    @PostMapping("findPostInfoPage")
    @ApiOperation("分页查询岗位信息表")
    public ResultDTO<Page<PostInfoVO>> findPostInfoPage(HttpServletRequest request, @RequestBody PostInfoQuery query) {
        ResultDTO<Page<PostInfoVO>> result = new ResultDTO<>();
        Page<PostInfoVO> page = postInfoService.findPostInfoPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePostInfo")
    @ApiOperation("保存或更新岗位信息表")
    @OpLog(title = "保存或更新岗位信息表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePostInfo(HttpServletRequest request, @RequestBody PostInfoDTO postInfoDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = postInfoService.saveOrUpdatePostInfo(postInfoDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPostInfoById")
    @ApiOperation("根据ID获取岗位信息表")
    public ResultDTO<PostInfoVO> getPostInfoById(Long id) {
        ResultDTO<PostInfoVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PostInfoVO postInfoVO = postInfoService.getPostInfoById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", postInfoVO);
    }

    @PostMapping("batchRemovePostInfoByIds")
    @ApiOperation("根据ID批量删除岗位信息表")
    @OpLog(title = "根据ID批量删除岗位信息表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePostInfoByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        postInfoService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
