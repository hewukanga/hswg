package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PostAddressDTO;
import com.jiumai.base.biz.query.PostAddressQuery;
import com.jiumai.base.biz.service.PostAddressService;
import com.jiumai.base.biz.vo.PostAddressVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 岗位地址信息表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/post-address")
@Api(tags = {"岗位地址信息表管理"})
public class PostAddressController {

    @Resource
    private PostAddressService postAddressService;

    @PostMapping("findPostAddressPage")
    @ApiOperation("分页查询岗位地址信息表")
    public ResultDTO<Page<PostAddressVO>> findPostAddressPage(HttpServletRequest request, @RequestBody PostAddressQuery query) {
        ResultDTO<Page<PostAddressVO>> result = new ResultDTO<>();
        Page<PostAddressVO> page = postAddressService.findPostAddressPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePostAddress")
    @ApiOperation("保存或更新岗位地址信息表")
    @OpLog(title = "保存或更新岗位地址信息表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePostAddress(HttpServletRequest request, @RequestBody PostAddressDTO postAddressDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = postAddressService.saveOrUpdatePostAddress(postAddressDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPostAddressById")
    @ApiOperation("根据ID获取岗位地址信息表")
    public ResultDTO<PostAddressVO> getPostAddressById(Long id) {
        ResultDTO<PostAddressVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PostAddressVO postAddressVO = postAddressService.getPostAddressById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", postAddressVO);
    }

    @PostMapping("batchRemovePostAddressByIds")
    @ApiOperation("根据ID批量删除岗位地址信息表")
    @OpLog(title = "根据ID批量删除岗位地址信息表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePostAddressByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        postAddressService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
