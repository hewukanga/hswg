package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.WelcomeCheckOpDTO;
import com.jiumai.base.biz.query.WelcomeCheckOpQuery;
import com.jiumai.base.biz.service.WelcomeCheckOpService;
import com.jiumai.base.biz.vo.WelcomeCheckOpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 迎检通知人员表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/welcome-check-op")
@Api(tags = {"迎检通知人员表管理"})
public class WelcomeCheckOpController {

    @Resource
    private WelcomeCheckOpService welcomeCheckOpService;

    @PostMapping("findWelcomeCheckOpPage")
    @ApiOperation("分页查询迎检通知人员表")
    public ResultDTO<Page<WelcomeCheckOpVO>> findWelcomeCheckOpPage(HttpServletRequest request, @RequestBody WelcomeCheckOpQuery query) {
        ResultDTO<Page<WelcomeCheckOpVO>> result = new ResultDTO<>();
        Page<WelcomeCheckOpVO> page = welcomeCheckOpService.findWelcomeCheckOpPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateWelcomeCheckOp")
    @ApiOperation("保存或更新迎检通知人员表")
    @OpLog(title = "保存或更新迎检通知人员表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateWelcomeCheckOp(HttpServletRequest request, @RequestBody WelcomeCheckOpDTO welcomeCheckOpDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = welcomeCheckOpService.saveOrUpdateWelcomeCheckOp(welcomeCheckOpDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getWelcomeCheckOpById")
    @ApiOperation("根据ID获取迎检通知人员表")
    public ResultDTO<WelcomeCheckOpVO> getWelcomeCheckOpById(Long id) {
        ResultDTO<WelcomeCheckOpVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        WelcomeCheckOpVO welcomeCheckOpVO = welcomeCheckOpService.getWelcomeCheckOpById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", welcomeCheckOpVO);
    }

    @PostMapping("batchRemoveWelcomeCheckOpByIds")
    @ApiOperation("根据ID批量删除迎检通知人员表")
    @OpLog(title = "根据ID批量删除迎检通知人员表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveWelcomeCheckOpByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        welcomeCheckOpService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
