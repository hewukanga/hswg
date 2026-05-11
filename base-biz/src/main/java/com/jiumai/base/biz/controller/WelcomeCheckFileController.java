package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.WelcomeCheckFileDTO;
import com.jiumai.base.biz.query.WelcomeCheckFileQuery;
import com.jiumai.base.biz.service.WelcomeCheckFileService;
import com.jiumai.base.biz.vo.WelcomeCheckFileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 迎检附件表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/welcome-check-file")
@Api(tags = {"迎检附件表管理"})
public class WelcomeCheckFileController {

    @Resource
    private WelcomeCheckFileService welcomeCheckFileService;

    @PostMapping("findWelcomeCheckFilePage")
    @ApiOperation("分页查询迎检附件表")
    public ResultDTO<Page<WelcomeCheckFileVO>> findWelcomeCheckFilePage(HttpServletRequest request, @RequestBody WelcomeCheckFileQuery query) {
        ResultDTO<Page<WelcomeCheckFileVO>> result = new ResultDTO<>();
        Page<WelcomeCheckFileVO> page = welcomeCheckFileService.findWelcomeCheckFilePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateWelcomeCheckFile")
    @ApiOperation("保存或更新迎检附件表")
    @OpLog(title = "保存或更新迎检附件表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateWelcomeCheckFile(HttpServletRequest request, @RequestBody WelcomeCheckFileDTO welcomeCheckFileDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = welcomeCheckFileService.saveOrUpdateWelcomeCheckFile(welcomeCheckFileDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getWelcomeCheckFileById")
    @ApiOperation("根据ID获取迎检附件表")
    public ResultDTO<WelcomeCheckFileVO> getWelcomeCheckFileById(Long id) {
        ResultDTO<WelcomeCheckFileVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        WelcomeCheckFileVO welcomeCheckFileVO = welcomeCheckFileService.getWelcomeCheckFileById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", welcomeCheckFileVO);
    }

    @PostMapping("batchRemoveWelcomeCheckFileByIds")
    @ApiOperation("根据ID批量删除迎检附件表")
    @OpLog(title = "根据ID批量删除迎检附件表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveWelcomeCheckFileByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        welcomeCheckFileService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
