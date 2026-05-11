package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.RectificationItemDTO;
import com.jiumai.base.biz.query.RectificationItemQuery;
import com.jiumai.base.biz.service.RectificationItemService;
import com.jiumai.base.biz.vo.RectificationItemVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 整改项 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/rectification-item")
@Api(tags = {"整改项管理"})
public class RectificationItemController {

    @Resource
    private RectificationItemService rectificationItemService;

    @PostMapping("findRectificationItemPage")
    @ApiOperation("分页查询整改项")
    public ResultDTO<Page<RectificationItemVO>> findRectificationItemPage(HttpServletRequest request, @RequestBody RectificationItemQuery query) {
        ResultDTO<Page<RectificationItemVO>> result = new ResultDTO<>();
        Page<RectificationItemVO> page = rectificationItemService.findRectificationItemPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateRectificationItem")
    @ApiOperation("保存或更新整改项")
    @OpLog(title = "保存或更新整改项", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateRectificationItem(HttpServletRequest request, @RequestBody RectificationItemDTO rectificationItemDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = rectificationItemService.saveOrUpdateRectificationItem(rectificationItemDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getRectificationItemById")
    @ApiOperation("根据ID获取整改项")
    public ResultDTO<RectificationItemVO> getRectificationItemById(Long id) {
        ResultDTO<RectificationItemVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        RectificationItemVO rectificationItemVO = rectificationItemService.getRectificationItemById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", rectificationItemVO);
    }

    @PostMapping("batchRemoveRectificationItemByIds")
    @ApiOperation("根据ID批量删除整改项")
    @OpLog(title = "根据ID批量删除整改项", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveRectificationItemByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        rectificationItemService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
