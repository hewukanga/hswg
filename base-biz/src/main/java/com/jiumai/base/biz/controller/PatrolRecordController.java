package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolRecordDTO;
import com.jiumai.base.biz.query.PatrolRecordQuery;
import com.jiumai.base.biz.service.PatrolRecordService;
import com.jiumai.base.biz.vo.PatrolRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 巡查记录表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/patrol-record")
@Api(tags = {"巡查记录表管理"})
public class PatrolRecordController {

    @Resource
    private PatrolRecordService patrolRecordService;

    @PostMapping("findPatrolRecordPage")
    @ApiOperation("分页查询巡查记录表")
    public ResultDTO<Page<PatrolRecordVO>> findPatrolRecordPage(HttpServletRequest request, @RequestBody PatrolRecordQuery query) {
        ResultDTO<Page<PatrolRecordVO>> result = new ResultDTO<>();
        Page<PatrolRecordVO> page = patrolRecordService.findPatrolRecordPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePatrolRecord")
    @ApiOperation("保存或更新巡查记录表")
    @OpLog(title = "保存或更新巡查记录表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePatrolRecord(HttpServletRequest request, @RequestBody PatrolRecordDTO patrolRecordDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = patrolRecordService.saveOrUpdatePatrolRecord(patrolRecordDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPatrolRecordById")
    @ApiOperation("根据ID获取巡查记录表")
    public ResultDTO<PatrolRecordVO> getPatrolRecordById(Long id) {
        ResultDTO<PatrolRecordVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PatrolRecordVO patrolRecordVO = patrolRecordService.getPatrolRecordById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", patrolRecordVO);
    }

    @PostMapping("batchRemovePatrolRecordByIds")
    @ApiOperation("根据ID批量删除巡查记录表")
    @OpLog(title = "根据ID批量删除巡查记录表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePatrolRecordByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        patrolRecordService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
