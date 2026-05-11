package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.NeighborhoodGoodsRecordDTO;
import com.jiumai.base.biz.query.NeighborhoodGoodsRecordQuery;
import com.jiumai.base.biz.service.NeighborhoodGoodsRecordService;
import com.jiumai.base.biz.vo.NeighborhoodGoodsRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 邻里商品操作记录表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/neighborhood-goods-record")
@Api(tags = {"邻里商品操作记录表管理"})
public class NeighborhoodGoodsRecordController {

    @Resource
    private NeighborhoodGoodsRecordService neighborhoodGoodsRecordService;

    @PostMapping("findNeighborhoodGoodsRecordPage")
    @ApiOperation("分页查询邻里商品操作记录表")
    public ResultDTO<Page<NeighborhoodGoodsRecordVO>> findNeighborhoodGoodsRecordPage(HttpServletRequest request, @RequestBody NeighborhoodGoodsRecordQuery query) {
        ResultDTO<Page<NeighborhoodGoodsRecordVO>> result = new ResultDTO<>();
        Page<NeighborhoodGoodsRecordVO> page = neighborhoodGoodsRecordService.findNeighborhoodGoodsRecordPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateNeighborhoodGoodsRecord")
    @ApiOperation("保存或更新邻里商品操作记录表")
    @OpLog(title = "保存或更新邻里商品操作记录表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateNeighborhoodGoodsRecord(HttpServletRequest request, @RequestBody NeighborhoodGoodsRecordDTO neighborhoodGoodsRecordDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = neighborhoodGoodsRecordService.saveOrUpdateNeighborhoodGoodsRecord(neighborhoodGoodsRecordDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getNeighborhoodGoodsRecordById")
    @ApiOperation("根据ID获取邻里商品操作记录表")
    public ResultDTO<NeighborhoodGoodsRecordVO> getNeighborhoodGoodsRecordById(Long id) {
        ResultDTO<NeighborhoodGoodsRecordVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        NeighborhoodGoodsRecordVO neighborhoodGoodsRecordVO = neighborhoodGoodsRecordService.getNeighborhoodGoodsRecordById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", neighborhoodGoodsRecordVO);
    }

    @PostMapping("batchRemoveNeighborhoodGoodsRecordByIds")
    @ApiOperation("根据ID批量删除邻里商品操作记录表")
    @OpLog(title = "根据ID批量删除邻里商品操作记录表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveNeighborhoodGoodsRecordByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        neighborhoodGoodsRecordService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
