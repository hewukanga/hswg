package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OneRepairDTO;
import com.jiumai.base.biz.query.OneRepairQuery;
import com.jiumai.base.biz.service.OneRepairService;
import com.jiumai.base.biz.vo.OneRepairVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 一键报修表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/one-repair")
@Api(tags = {"一键报修表管理"})
public class OneRepairController {

    @Resource
    private OneRepairService oneRepairService;

    @PostMapping("findOneRepairPage")
    @ApiOperation("分页查询一键报修表")
    public ResultDTO<Page<OneRepairVO>> findOneRepairPage(HttpServletRequest request, @RequestBody OneRepairQuery query) {
        ResultDTO<Page<OneRepairVO>> result = new ResultDTO<>();
        Page<OneRepairVO> page = oneRepairService.findOneRepairPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateOneRepair")
    @ApiOperation("保存或更新一键报修表")
    @OpLog(title = "保存或更新一键报修表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateOneRepair(HttpServletRequest request, @RequestBody OneRepairDTO oneRepairDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = oneRepairService.saveOrUpdateOneRepair(oneRepairDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getOneRepairById")
    @ApiOperation("根据ID获取一键报修表")
    public ResultDTO<OneRepairVO> getOneRepairById(Long id) {
        ResultDTO<OneRepairVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        OneRepairVO oneRepairVO = oneRepairService.getOneRepairById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", oneRepairVO);
    }

    @PostMapping("batchRemoveOneRepairByIds")
    @ApiOperation("根据ID批量删除一键报修表")
    @OpLog(title = "根据ID批量删除一键报修表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveOneRepairByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        oneRepairService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
