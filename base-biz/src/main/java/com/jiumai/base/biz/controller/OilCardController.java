package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OilCardDTO;
import com.jiumai.base.biz.query.OilCardQuery;
import com.jiumai.base.biz.service.OilCardService;
import com.jiumai.base.biz.vo.OilCardVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 油卡表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/oil-card")
@Api(tags = {"油卡表管理"})
public class OilCardController {

    @Resource
    private OilCardService oilCardService;

    @PostMapping("findOilCardPage")
    @ApiOperation("分页查询油卡表")
    public ResultDTO<Page<OilCardVO>> findOilCardPage(HttpServletRequest request, @RequestBody OilCardQuery query) {
        ResultDTO<Page<OilCardVO>> result = new ResultDTO<>();
        Page<OilCardVO> page = oilCardService.findOilCardPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateOilCard")
    @ApiOperation("保存或更新油卡表")
    @OpLog(title = "保存或更新油卡表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateOilCard(HttpServletRequest request, @RequestBody OilCardDTO oilCardDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = oilCardService.saveOrUpdateOilCard(oilCardDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getOilCardById")
    @ApiOperation("根据ID获取油卡表")
    public ResultDTO<OilCardVO> getOilCardById(Long id) {
        ResultDTO<OilCardVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        OilCardVO oilCardVO = oilCardService.getOilCardById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", oilCardVO);
    }

    @PostMapping("batchRemoveOilCardByIds")
    @ApiOperation("根据ID批量删除油卡表")
    @OpLog(title = "根据ID批量删除油卡表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveOilCardByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        oilCardService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
