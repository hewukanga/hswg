package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.HousePropertyDTO;
import com.jiumai.base.biz.query.HousePropertyQuery;
import com.jiumai.base.biz.service.HousePropertyService;
import com.jiumai.base.biz.vo.HousePropertyVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 房产管理 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/house-property")
@Api(tags = {"房产管理管理"})
public class HousePropertyController {

    @Resource
    private HousePropertyService housePropertyService;

    @PostMapping("findHousePropertyPage")
    @ApiOperation("分页查询房产管理")
    public ResultDTO<Page<HousePropertyVO>> findHousePropertyPage(HttpServletRequest request, @RequestBody HousePropertyQuery query) {
        ResultDTO<Page<HousePropertyVO>> result = new ResultDTO<>();
        Page<HousePropertyVO> page = housePropertyService.findHousePropertyPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateHouseProperty")
    @ApiOperation("保存或更新房产管理")
    @OpLog(title = "保存或更新房产管理", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateHouseProperty(HttpServletRequest request, @RequestBody HousePropertyDTO housePropertyDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = housePropertyService.saveOrUpdateHouseProperty(housePropertyDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getHousePropertyById")
    @ApiOperation("根据ID获取房产管理")
    public ResultDTO<HousePropertyVO> getHousePropertyById(Long id) {
        ResultDTO<HousePropertyVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        HousePropertyVO housePropertyVO = housePropertyService.getHousePropertyById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", housePropertyVO);
    }

    @PostMapping("batchRemoveHousePropertyByIds")
    @ApiOperation("根据ID批量删除房产管理")
    @OpLog(title = "根据ID批量删除房产管理", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveHousePropertyByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        housePropertyService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
