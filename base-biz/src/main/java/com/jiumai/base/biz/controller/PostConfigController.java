package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PostConfigDTO;
import com.jiumai.base.biz.query.PostConfigQuery;
import com.jiumai.base.biz.service.PostConfigService;
import com.jiumai.base.biz.vo.PostConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 岗位配置信息表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/post-config")
@Api(tags = {"岗位配置信息表管理"})
public class PostConfigController {

    @Resource
    private PostConfigService postConfigService;

    @PostMapping("findPostConfigPage")
    @ApiOperation("分页查询岗位配置信息表")
    public ResultDTO<Page<PostConfigVO>> findPostConfigPage(HttpServletRequest request, @RequestBody PostConfigQuery query) {
        ResultDTO<Page<PostConfigVO>> result = new ResultDTO<>();
        Page<PostConfigVO> page = postConfigService.findPostConfigPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePostConfig")
    @ApiOperation("保存或更新岗位配置信息表")
    @OpLog(title = "保存或更新岗位配置信息表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePostConfig(HttpServletRequest request, @RequestBody PostConfigDTO postConfigDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = postConfigService.saveOrUpdatePostConfig(postConfigDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPostConfigById")
    @ApiOperation("根据ID获取岗位配置信息表")
    public ResultDTO<PostConfigVO> getPostConfigById(Long id) {
        ResultDTO<PostConfigVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PostConfigVO postConfigVO = postConfigService.getPostConfigById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", postConfigVO);
    }

    @PostMapping("batchRemovePostConfigByIds")
    @ApiOperation("根据ID批量删除岗位配置信息表")
    @OpLog(title = "根据ID批量删除岗位配置信息表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePostConfigByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        postConfigService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
