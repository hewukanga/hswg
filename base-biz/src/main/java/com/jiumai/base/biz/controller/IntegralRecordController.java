package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.IntegralRecordDTO;
import com.jiumai.base.biz.query.IntegralRecordQuery;
import com.jiumai.base.biz.service.IntegralRecordService;
import com.jiumai.base.biz.vo.IntegralRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 积分记录 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/integral-record")
@Api(tags = {"积分记录管理"})
public class IntegralRecordController {

    @Resource
    private IntegralRecordService integralRecordService;

    @PostMapping("findIntegralRecordPage")
    @ApiOperation("分页查询积分记录")
    public ResultDTO<Page<IntegralRecordVO>> findIntegralRecordPage(HttpServletRequest request, @RequestBody IntegralRecordQuery query) {
        ResultDTO<Page<IntegralRecordVO>> result = new ResultDTO<>();
        Page<IntegralRecordVO> page = integralRecordService.findIntegralRecordPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateIntegralRecord")
    @ApiOperation("保存或更新积分记录")
    @OpLog(title = "保存或更新积分记录", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateIntegralRecord(HttpServletRequest request, @RequestBody IntegralRecordDTO integralRecordDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = integralRecordService.saveOrUpdateIntegralRecord(integralRecordDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getIntegralRecordById")
    @ApiOperation("根据ID获取积分记录")
    public ResultDTO<IntegralRecordVO> getIntegralRecordById(Long id) {
        ResultDTO<IntegralRecordVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        IntegralRecordVO integralRecordVO = integralRecordService.getIntegralRecordById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", integralRecordVO);
    }

    @PostMapping("batchRemoveIntegralRecordByIds")
    @ApiOperation("根据ID批量删除积分记录")
    @OpLog(title = "根据ID批量删除积分记录", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveIntegralRecordByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        integralRecordService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
