package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.RepairTypeDTO;
import com.jiumai.base.biz.query.RepairTypeQuery;
import com.jiumai.base.biz.service.RepairTypeService;
import com.jiumai.base.biz.vo.RepairTypeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 维修项目配置表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/repair-type")
@Api(tags = {"维修项目配置表管理"})
public class RepairTypeController {

    @Resource
    private RepairTypeService repairTypeService;

    @PostMapping("findRepairTypePage")
    @ApiOperation("分页查询维修项目配置表")
    public ResultDTO<Page<RepairTypeVO>> findRepairTypePage(HttpServletRequest request, @RequestBody RepairTypeQuery query) {
        ResultDTO<Page<RepairTypeVO>> result = new ResultDTO<>();
        Page<RepairTypeVO> page = repairTypeService.findRepairTypePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateRepairType")
    @ApiOperation("保存或更新维修项目配置表")
    @OpLog(title = "保存或更新维修项目配置表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateRepairType(HttpServletRequest request, @RequestBody RepairTypeDTO repairTypeDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = repairTypeService.saveOrUpdateRepairType(repairTypeDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getRepairTypeById")
    @ApiOperation("根据ID获取维修项目配置表")
    public ResultDTO<RepairTypeVO> getRepairTypeById(Long id) {
        ResultDTO<RepairTypeVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        RepairTypeVO repairTypeVO = repairTypeService.getRepairTypeById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", repairTypeVO);
    }

    @PostMapping("batchRemoveRepairTypeByIds")
    @ApiOperation("根据ID批量删除维修项目配置表")
    @OpLog(title = "根据ID批量删除维修项目配置表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveRepairTypeByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        repairTypeService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
