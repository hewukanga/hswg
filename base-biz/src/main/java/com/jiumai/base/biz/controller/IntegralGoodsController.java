package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.IntegralGoodsDTO;
import com.jiumai.base.biz.query.IntegralGoodsQuery;
import com.jiumai.base.biz.service.IntegralGoodsService;
import com.jiumai.base.biz.vo.IntegralGoodsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 积分商品 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/integral-goods")
@Api(tags = {"积分商品管理"})
public class IntegralGoodsController {

    @Resource
    private IntegralGoodsService integralGoodsService;

    @PostMapping("findIntegralGoodsPage")
    @ApiOperation("分页查询积分商品")
    public ResultDTO<Page<IntegralGoodsVO>> findIntegralGoodsPage(HttpServletRequest request, @RequestBody IntegralGoodsQuery query) {
        ResultDTO<Page<IntegralGoodsVO>> result = new ResultDTO<>();
        Page<IntegralGoodsVO> page = integralGoodsService.findIntegralGoodsPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateIntegralGoods")
    @ApiOperation("保存或更新积分商品")
    @OpLog(title = "保存或更新积分商品", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateIntegralGoods(HttpServletRequest request, @RequestBody IntegralGoodsDTO integralGoodsDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = integralGoodsService.saveOrUpdateIntegralGoods(integralGoodsDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getIntegralGoodsById")
    @ApiOperation("根据ID获取积分商品")
    public ResultDTO<IntegralGoodsVO> getIntegralGoodsById(Long id) {
        ResultDTO<IntegralGoodsVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        IntegralGoodsVO integralGoodsVO = integralGoodsService.getIntegralGoodsById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", integralGoodsVO);
    }

    @PostMapping("batchRemoveIntegralGoodsByIds")
    @ApiOperation("根据ID批量删除积分商品")
    @OpLog(title = "根据ID批量删除积分商品", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveIntegralGoodsByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        integralGoodsService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
