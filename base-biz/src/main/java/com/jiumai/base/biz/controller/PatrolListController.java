package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PatrolListDTO;
import com.jiumai.base.biz.query.PatrolListQuery;
import com.jiumai.base.biz.service.PatrolListService;
import com.jiumai.base.biz.vo.PatrolListVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 巡查单表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/patrol-list")
@Api(tags = {"巡查单表管理"})
public class PatrolListController {

    @Resource
    private PatrolListService patrolListService;

    @PostMapping("findPatrolListPage")
    @ApiOperation("分页查询巡查单表")
    public ResultDTO<Page<PatrolListVO>> findPatrolListPage(HttpServletRequest request, @RequestBody PatrolListQuery query) {
        ResultDTO<Page<PatrolListVO>> result = new ResultDTO<>();
        Page<PatrolListVO> page = patrolListService.findPatrolListPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePatrolList")
    @ApiOperation("保存或更新巡查单表")
    @OpLog(title = "保存或更新巡查单表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePatrolList(HttpServletRequest request, @RequestBody PatrolListDTO patrolListDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = patrolListService.saveOrUpdatePatrolList(patrolListDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPatrolListById")
    @ApiOperation("根据ID获取巡查单表")
    public ResultDTO<PatrolListVO> getPatrolListById(Long id) {
        ResultDTO<PatrolListVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PatrolListVO patrolListVO = patrolListService.getPatrolListById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", patrolListVO);
    }

    @PostMapping("batchRemovePatrolListByIds")
    @ApiOperation("根据ID批量删除巡查单表")
    @OpLog(title = "根据ID批量删除巡查单表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePatrolListByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        patrolListService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
