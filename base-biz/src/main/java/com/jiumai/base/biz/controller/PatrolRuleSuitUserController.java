package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolRuleSuitUserDTO;
import com.jiumai.base.biz.query.PatrolRuleSuitUserQuery;
import com.jiumai.base.biz.service.PatrolRuleSuitUserService;
import com.jiumai.base.biz.vo.PatrolRuleSuitUserVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 巡查单规则适用人员表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/patrol-rule-suit-user")
@Api(tags = {"巡查单规则适用人员表管理"})
public class PatrolRuleSuitUserController {

    @Resource
    private PatrolRuleSuitUserService patrolRuleSuitUserService;

    @PostMapping("findPatrolRuleSuitUserPage")
    @ApiOperation("分页查询巡查单规则适用人员表")
    public ResultDTO<Page<PatrolRuleSuitUserVO>> findPatrolRuleSuitUserPage(HttpServletRequest request, @RequestBody PatrolRuleSuitUserQuery query) {
        ResultDTO<Page<PatrolRuleSuitUserVO>> result = new ResultDTO<>();
        Page<PatrolRuleSuitUserVO> page = patrolRuleSuitUserService.findPatrolRuleSuitUserPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePatrolRuleSuitUser")
    @ApiOperation("保存或更新巡查单规则适用人员表")
    @OpLog(title = "保存或更新巡查单规则适用人员表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePatrolRuleSuitUser(HttpServletRequest request, @RequestBody PatrolRuleSuitUserDTO patrolRuleSuitUserDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = patrolRuleSuitUserService.saveOrUpdatePatrolRuleSuitUser(patrolRuleSuitUserDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPatrolRuleSuitUserById")
    @ApiOperation("根据ID获取巡查单规则适用人员表")
    public ResultDTO<PatrolRuleSuitUserVO> getPatrolRuleSuitUserById(Long id) {
        ResultDTO<PatrolRuleSuitUserVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PatrolRuleSuitUserVO patrolRuleSuitUserVO = patrolRuleSuitUserService.getPatrolRuleSuitUserById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", patrolRuleSuitUserVO);
    }

    @PostMapping("batchRemovePatrolRuleSuitUserByIds")
    @ApiOperation("根据ID批量删除巡查单规则适用人员表")
    @OpLog(title = "根据ID批量删除巡查单规则适用人员表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePatrolRuleSuitUserByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        patrolRuleSuitUserService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
