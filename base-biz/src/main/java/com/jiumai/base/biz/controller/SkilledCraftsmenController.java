package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.SkilledCraftsmenDTO;
import com.jiumai.base.biz.query.SkilledCraftsmenQuery;
import com.jiumai.base.biz.service.SkilledCraftsmenService;
import com.jiumai.base.biz.vo.SkilledCraftsmenVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 能工巧匠信息 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/skilled-craftsmen")
@Api(tags = {"能工巧匠信息管理"})
public class SkilledCraftsmenController {

    @Resource
    private SkilledCraftsmenService skilledCraftsmenService;

    @PostMapping("findSkilledCraftsmenPage")
    @ApiOperation("分页查询能工巧匠信息")
    public ResultDTO<Page<SkilledCraftsmenVO>> findSkilledCraftsmenPage(HttpServletRequest request, @RequestBody SkilledCraftsmenQuery query) {
        ResultDTO<Page<SkilledCraftsmenVO>> result = new ResultDTO<>();
        Page<SkilledCraftsmenVO> page = skilledCraftsmenService.findSkilledCraftsmenPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateSkilledCraftsmen")
    @ApiOperation("保存或更新能工巧匠信息")
    @OpLog(title = "保存或更新能工巧匠信息", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateSkilledCraftsmen(HttpServletRequest request, @RequestBody SkilledCraftsmenDTO skilledCraftsmenDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = skilledCraftsmenService.saveOrUpdateSkilledCraftsmen(skilledCraftsmenDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getSkilledCraftsmenById")
    @ApiOperation("根据ID获取能工巧匠信息")
    public ResultDTO<SkilledCraftsmenVO> getSkilledCraftsmenById(Long id) {
        ResultDTO<SkilledCraftsmenVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        SkilledCraftsmenVO skilledCraftsmenVO = skilledCraftsmenService.getSkilledCraftsmenById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", skilledCraftsmenVO);
    }

    @PostMapping("batchRemoveSkilledCraftsmenByIds")
    @ApiOperation("根据ID批量删除能工巧匠信息")
    @OpLog(title = "根据ID批量删除能工巧匠信息", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveSkilledCraftsmenByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        skilledCraftsmenService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
