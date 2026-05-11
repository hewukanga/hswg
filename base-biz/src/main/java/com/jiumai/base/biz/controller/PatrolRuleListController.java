package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolRuleListDTO;
import com.jiumai.base.biz.query.PatrolRuleListQuery;
import com.jiumai.base.biz.service.PatrolRuleListService;
import com.jiumai.base.biz.vo.PatrolRuleListVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 巡查单规则表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/patrol-rule-list")
@Api(tags = {"巡查单规则表管理"})
public class PatrolRuleListController {

    @Resource
    private PatrolRuleListService patrolRuleListService;

    @PostMapping("findPatrolRuleListPage")
    @ApiOperation("分页查询巡查单规则表")
    public ResultDTO<Page<PatrolRuleListVO>> findPatrolRuleListPage(HttpServletRequest request, @RequestBody PatrolRuleListQuery query) {
        ResultDTO<Page<PatrolRuleListVO>> result = new ResultDTO<>();
        Page<PatrolRuleListVO> page = patrolRuleListService.findPatrolRuleListPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePatrolRuleList")
    @ApiOperation("保存或更新巡查单规则表")
    @OpLog(title = "保存或更新巡查单规则表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePatrolRuleList(HttpServletRequest request, @RequestBody PatrolRuleListDTO patrolRuleListDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = patrolRuleListService.saveOrUpdatePatrolRuleList(patrolRuleListDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPatrolRuleListById")
    @ApiOperation("根据ID获取巡查单规则表")
    public ResultDTO<PatrolRuleListVO> getPatrolRuleListById(Long id) {
        ResultDTO<PatrolRuleListVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PatrolRuleListVO patrolRuleListVO = patrolRuleListService.getPatrolRuleListById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", patrolRuleListVO);
    }

    @PostMapping("batchRemovePatrolRuleListByIds")
    @ApiOperation("根据ID批量删除巡查单规则表")
    @OpLog(title = "根据ID批量删除巡查单规则表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePatrolRuleListByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        patrolRuleListService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
