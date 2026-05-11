package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.WelcomeCheckDTO;
import com.jiumai.base.biz.query.WelcomeCheckQuery;
import com.jiumai.base.biz.service.WelcomeCheckService;
import com.jiumai.base.biz.vo.WelcomeCheckVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 迎检表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/welcome-check")
@Api(tags = {"迎检表管理"})
public class WelcomeCheckController {

    @Resource
    private WelcomeCheckService welcomeCheckService;

    @PostMapping("findWelcomeCheckPage")
    @ApiOperation("分页查询迎检表")
    public ResultDTO<Page<WelcomeCheckVO>> findWelcomeCheckPage(HttpServletRequest request, @RequestBody WelcomeCheckQuery query) {
        ResultDTO<Page<WelcomeCheckVO>> result = new ResultDTO<>();
        Page<WelcomeCheckVO> page = welcomeCheckService.findWelcomeCheckPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateWelcomeCheck")
    @ApiOperation("保存或更新迎检表")
    @OpLog(title = "保存或更新迎检表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateWelcomeCheck(HttpServletRequest request, @RequestBody WelcomeCheckDTO welcomeCheckDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = welcomeCheckService.saveOrUpdateWelcomeCheck(welcomeCheckDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getWelcomeCheckById")
    @ApiOperation("根据ID获取迎检表")
    public ResultDTO<WelcomeCheckVO> getWelcomeCheckById(Long id) {
        ResultDTO<WelcomeCheckVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        WelcomeCheckVO welcomeCheckVO = welcomeCheckService.getWelcomeCheckById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", welcomeCheckVO);
    }

    @PostMapping("batchRemoveWelcomeCheckByIds")
    @ApiOperation("根据ID批量删除迎检表")
    @OpLog(title = "根据ID批量删除迎检表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveWelcomeCheckByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        welcomeCheckService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
