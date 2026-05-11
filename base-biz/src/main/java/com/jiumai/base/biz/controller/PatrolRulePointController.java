package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolRulePointDTO;
import com.jiumai.base.biz.query.PatrolRulePointQuery;
import com.jiumai.base.biz.service.PatrolRulePointService;
import com.jiumai.base.biz.vo.PatrolRulePointVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 巡查单规则点位表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/patrol-rule-point")
@Api(tags = {"巡查单规则点位表管理"})
public class PatrolRulePointController {

    @Resource
    private PatrolRulePointService patrolRulePointService;

    @PostMapping("findPatrolRulePointPage")
    @ApiOperation("分页查询巡查单规则点位表")
    public ResultDTO<Page<PatrolRulePointVO>> findPatrolRulePointPage(HttpServletRequest request, @RequestBody PatrolRulePointQuery query) {
        ResultDTO<Page<PatrolRulePointVO>> result = new ResultDTO<>();
        Page<PatrolRulePointVO> page = patrolRulePointService.findPatrolRulePointPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePatrolRulePoint")
    @ApiOperation("保存或更新巡查单规则点位表")
    @OpLog(title = "保存或更新巡查单规则点位表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePatrolRulePoint(HttpServletRequest request, @RequestBody PatrolRulePointDTO patrolRulePointDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = patrolRulePointService.saveOrUpdatePatrolRulePoint(patrolRulePointDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPatrolRulePointById")
    @ApiOperation("根据ID获取巡查单规则点位表")
    public ResultDTO<PatrolRulePointVO> getPatrolRulePointById(Long id) {
        ResultDTO<PatrolRulePointVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PatrolRulePointVO patrolRulePointVO = patrolRulePointService.getPatrolRulePointById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", patrolRulePointVO);
    }

    @PostMapping("batchRemovePatrolRulePointByIds")
    @ApiOperation("根据ID批量删除巡查单规则点位表")
    @OpLog(title = "根据ID批量删除巡查单规则点位表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePatrolRulePointByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        patrolRulePointService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
