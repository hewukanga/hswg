package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ProceedingsDTO;
import com.jiumai.base.biz.query.ProceedingsQuery;
import com.jiumai.base.biz.service.ProceedingsService;
import com.jiumai.base.biz.vo.ProceedingsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 会议议事 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/proceedings")
@Api(tags = {"会议议事管理"})
public class ProceedingsController {

    @Resource
    private ProceedingsService proceedingsService;

    @PostMapping("findProceedingsPage")
    @ApiOperation("分页查询会议议事")
    public ResultDTO<Page<ProceedingsVO>> findProceedingsPage(HttpServletRequest request, @RequestBody ProceedingsQuery query) {
        ResultDTO<Page<ProceedingsVO>> result = new ResultDTO<>();
        Page<ProceedingsVO> page = proceedingsService.findProceedingsPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateProceedings")
    @ApiOperation("保存或更新会议议事")
    @OpLog(title = "保存或更新会议议事", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateProceedings(HttpServletRequest request, @RequestBody ProceedingsDTO proceedingsDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = proceedingsService.saveOrUpdateProceedings(proceedingsDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getProceedingsById")
    @ApiOperation("根据ID获取会议议事")
    public ResultDTO<ProceedingsVO> getProceedingsById(Long id) {
        ResultDTO<ProceedingsVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ProceedingsVO proceedingsVO = proceedingsService.getProceedingsById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", proceedingsVO);
    }

    @PostMapping("batchRemoveProceedingsByIds")
    @ApiOperation("根据ID批量删除会议议事")
    @OpLog(title = "根据ID批量删除会议议事", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveProceedingsByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        proceedingsService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
