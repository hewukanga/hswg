package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolListPointDTO;
import com.jiumai.base.biz.query.PatrolListPointQuery;
import com.jiumai.base.biz.service.PatrolListPointService;
import com.jiumai.base.biz.vo.PatrolListPointVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 巡查单点位表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/patrol-list-point")
@Api(tags = {"巡查单点位表管理"})
public class PatrolListPointController {

    @Resource
    private PatrolListPointService patrolListPointService;

    @PostMapping("findPatrolListPointPage")
    @ApiOperation("分页查询巡查单点位表")
    public ResultDTO<Page<PatrolListPointVO>> findPatrolListPointPage(HttpServletRequest request, @RequestBody PatrolListPointQuery query) {
        ResultDTO<Page<PatrolListPointVO>> result = new ResultDTO<>();
        Page<PatrolListPointVO> page = patrolListPointService.findPatrolListPointPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePatrolListPoint")
    @ApiOperation("保存或更新巡查单点位表")
    @OpLog(title = "保存或更新巡查单点位表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePatrolListPoint(HttpServletRequest request, @RequestBody PatrolListPointDTO patrolListPointDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = patrolListPointService.saveOrUpdatePatrolListPoint(patrolListPointDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPatrolListPointById")
    @ApiOperation("根据ID获取巡查单点位表")
    public ResultDTO<PatrolListPointVO> getPatrolListPointById(Long id) {
        ResultDTO<PatrolListPointVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PatrolListPointVO patrolListPointVO = patrolListPointService.getPatrolListPointById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", patrolListPointVO);
    }

    @PostMapping("batchRemovePatrolListPointByIds")
    @ApiOperation("根据ID批量删除巡查单点位表")
    @OpLog(title = "根据ID批量删除巡查单点位表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePatrolListPointByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        patrolListPointService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
