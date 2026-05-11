package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.IntegralGoodsExchangeDTO;
import com.jiumai.base.biz.query.IntegralGoodsExchangeQuery;
import com.jiumai.base.biz.service.IntegralGoodsExchangeService;
import com.jiumai.base.biz.vo.IntegralGoodsExchangeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 积分商品兑换记录 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/integral-goods-exchange")
@Api(tags = {"积分商品兑换记录管理"})
public class IntegralGoodsExchangeController {

    @Resource
    private IntegralGoodsExchangeService integralGoodsExchangeService;

    @PostMapping("findIntegralGoodsExchangePage")
    @ApiOperation("分页查询积分商品兑换记录")
    public ResultDTO<Page<IntegralGoodsExchangeVO>> findIntegralGoodsExchangePage(HttpServletRequest request, @RequestBody IntegralGoodsExchangeQuery query) {
        ResultDTO<Page<IntegralGoodsExchangeVO>> result = new ResultDTO<>();
        Page<IntegralGoodsExchangeVO> page = integralGoodsExchangeService.findIntegralGoodsExchangePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateIntegralGoodsExchange")
    @ApiOperation("保存或更新积分商品兑换记录")
    @OpLog(title = "保存或更新积分商品兑换记录", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateIntegralGoodsExchange(HttpServletRequest request, @RequestBody IntegralGoodsExchangeDTO integralGoodsExchangeDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = integralGoodsExchangeService.saveOrUpdateIntegralGoodsExchange(integralGoodsExchangeDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getIntegralGoodsExchangeById")
    @ApiOperation("根据ID获取积分商品兑换记录")
    public ResultDTO<IntegralGoodsExchangeVO> getIntegralGoodsExchangeById(Long id) {
        ResultDTO<IntegralGoodsExchangeVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        IntegralGoodsExchangeVO integralGoodsExchangeVO = integralGoodsExchangeService.getIntegralGoodsExchangeById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", integralGoodsExchangeVO);
    }

    @PostMapping("batchRemoveIntegralGoodsExchangeByIds")
    @ApiOperation("根据ID批量删除积分商品兑换记录")
    @OpLog(title = "根据ID批量删除积分商品兑换记录", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveIntegralGoodsExchangeByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        integralGoodsExchangeService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
