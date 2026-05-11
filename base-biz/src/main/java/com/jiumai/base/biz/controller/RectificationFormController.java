package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.RectificationFormDTO;
import com.jiumai.base.biz.query.RectificationFormQuery;
import com.jiumai.base.biz.service.RectificationFormService;
import com.jiumai.base.biz.vo.RectificationFormVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 整改单 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/rectification-form")
@Api(tags = {"整改单管理"})
public class RectificationFormController {

    @Resource
    private RectificationFormService rectificationFormService;

    @PostMapping("findRectificationFormPage")
    @ApiOperation("分页查询整改单")
    public ResultDTO<Page<RectificationFormVO>> findRectificationFormPage(HttpServletRequest request, @RequestBody RectificationFormQuery query) {
        ResultDTO<Page<RectificationFormVO>> result = new ResultDTO<>();
        Page<RectificationFormVO> page = rectificationFormService.findRectificationFormPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateRectificationForm")
    @ApiOperation("保存或更新整改单")
    @OpLog(title = "保存或更新整改单", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateRectificationForm(HttpServletRequest request, @RequestBody RectificationFormDTO rectificationFormDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = rectificationFormService.saveOrUpdateRectificationForm(rectificationFormDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getRectificationFormById")
    @ApiOperation("根据ID获取整改单")
    public ResultDTO<RectificationFormVO> getRectificationFormById(Long id) {
        ResultDTO<RectificationFormVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        RectificationFormVO rectificationFormVO = rectificationFormService.getRectificationFormById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", rectificationFormVO);
    }

    @PostMapping("batchRemoveRectificationFormByIds")
    @ApiOperation("根据ID批量删除整改单")
    @OpLog(title = "根据ID批量删除整改单", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveRectificationFormByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        rectificationFormService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
