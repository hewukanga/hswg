package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ShopDTO;
import com.jiumai.base.biz.query.ShopQuery;
import com.jiumai.base.biz.service.ShopService;
import com.jiumai.base.biz.vo.ShopVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 店铺信息表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/shop")
@Api(tags = {"店铺信息表管理"})
public class ShopController {

    @Resource
    private ShopService shopService;

    @PostMapping("findShopPage")
    @ApiOperation("分页查询店铺信息表")
    public ResultDTO<Page<ShopVO>> findShopPage(HttpServletRequest request, @RequestBody ShopQuery query) {
        ResultDTO<Page<ShopVO>> result = new ResultDTO<>();
        Page<ShopVO> page = shopService.findShopPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateShop")
    @ApiOperation("保存或更新店铺信息表")
    @OpLog(title = "保存或更新店铺信息表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateShop(HttpServletRequest request, @RequestBody ShopDTO shopDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = shopService.saveOrUpdateShop(shopDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getShopById")
    @ApiOperation("根据ID获取店铺信息表")
    public ResultDTO<ShopVO> getShopById(Long id) {
        ResultDTO<ShopVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ShopVO shopVO = shopService.getShopById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", shopVO);
    }

    @PostMapping("batchRemoveShopByIds")
    @ApiOperation("根据ID批量删除店铺信息表")
    @OpLog(title = "根据ID批量删除店铺信息表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveShopByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        shopService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
