package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.NeighborhoodGoodsDTO;
import com.jiumai.base.biz.query.NeighborhoodGoodsQuery;
import com.jiumai.base.biz.service.NeighborhoodGoodsService;
import com.jiumai.base.biz.vo.NeighborhoodGoodsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 邻里商品表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/neighborhood-goods")
@Api(tags = {"邻里商品表管理"})
public class NeighborhoodGoodsController {

    @Resource
    private NeighborhoodGoodsService neighborhoodGoodsService;

    @PostMapping("findNeighborhoodGoodsPage")
    @ApiOperation("分页查询邻里商品表")
    public ResultDTO<Page<NeighborhoodGoodsVO>> findNeighborhoodGoodsPage(HttpServletRequest request, @RequestBody NeighborhoodGoodsQuery query) {
        ResultDTO<Page<NeighborhoodGoodsVO>> result = new ResultDTO<>();
        Page<NeighborhoodGoodsVO> page = neighborhoodGoodsService.findNeighborhoodGoodsPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateNeighborhoodGoods")
    @ApiOperation("保存或更新邻里商品表")
    @OpLog(title = "保存或更新邻里商品表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateNeighborhoodGoods(HttpServletRequest request, @RequestBody NeighborhoodGoodsDTO neighborhoodGoodsDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = neighborhoodGoodsService.saveOrUpdateNeighborhoodGoods(neighborhoodGoodsDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getNeighborhoodGoodsById")
    @ApiOperation("根据ID获取邻里商品表")
    public ResultDTO<NeighborhoodGoodsVO> getNeighborhoodGoodsById(Long id) {
        ResultDTO<NeighborhoodGoodsVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        NeighborhoodGoodsVO neighborhoodGoodsVO = neighborhoodGoodsService.getNeighborhoodGoodsById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", neighborhoodGoodsVO);
    }

    @PostMapping("batchRemoveNeighborhoodGoodsByIds")
    @ApiOperation("根据ID批量删除邻里商品表")
    @OpLog(title = "根据ID批量删除邻里商品表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveNeighborhoodGoodsByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        neighborhoodGoodsService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
