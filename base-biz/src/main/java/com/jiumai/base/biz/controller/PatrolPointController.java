package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolPointDTO;
import com.jiumai.base.biz.query.PatrolPointQuery;
import com.jiumai.base.biz.service.PatrolPointService;
import com.jiumai.base.biz.vo.PatrolPointVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 巡查点位表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/patrol-point")
@Api(tags = {"巡查点位表管理"})
public class PatrolPointController {

    @Resource
    private PatrolPointService patrolPointService;

    @PostMapping("findPatrolPointPage")
    @ApiOperation("分页查询巡查点位表")
    public ResultDTO<Page<PatrolPointVO>> findPatrolPointPage(HttpServletRequest request, @RequestBody PatrolPointQuery query) {
        ResultDTO<Page<PatrolPointVO>> result = new ResultDTO<>();
        Page<PatrolPointVO> page = patrolPointService.findPatrolPointPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePatrolPoint")
    @ApiOperation("保存或更新巡查点位表")
    @OpLog(title = "保存或更新巡查点位表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePatrolPoint(HttpServletRequest request, @RequestBody PatrolPointDTO patrolPointDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = patrolPointService.saveOrUpdatePatrolPoint(patrolPointDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPatrolPointById")
    @ApiOperation("根据ID获取巡查点位表")
    public ResultDTO<PatrolPointVO> getPatrolPointById(Long id) {
        ResultDTO<PatrolPointVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PatrolPointVO patrolPointVO = patrolPointService.getPatrolPointById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", patrolPointVO);
    }

    @PostMapping("batchRemovePatrolPointByIds")
    @ApiOperation("根据ID批量删除巡查点位表")
    @OpLog(title = "根据ID批量删除巡查点位表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePatrolPointByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        patrolPointService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
