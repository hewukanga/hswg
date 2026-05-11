package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.GridPersonnelDTO;
import com.jiumai.base.biz.query.GridPersonnelQuery;
import com.jiumai.base.biz.service.GridPersonnelService;
import com.jiumai.base.biz.vo.GridPersonnelVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 网格人员信息 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/grid-personnel")
@Api(tags = {"网格人员信息管理"})
public class GridPersonnelController {

    @Resource
    private GridPersonnelService gridPersonnelService;

    @PostMapping("findGridPersonnelPage")
    @ApiOperation("分页查询网格人员信息")
    public ResultDTO<Page<GridPersonnelVO>> findGridPersonnelPage(HttpServletRequest request, @RequestBody GridPersonnelQuery query) {
        ResultDTO<Page<GridPersonnelVO>> result = new ResultDTO<>();
        Page<GridPersonnelVO> page = gridPersonnelService.findGridPersonnelPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateGridPersonnel")
    @ApiOperation("保存或更新网格人员信息")
    @OpLog(title = "保存或更新网格人员信息", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateGridPersonnel(HttpServletRequest request, @RequestBody GridPersonnelDTO gridPersonnelDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = gridPersonnelService.saveOrUpdateGridPersonnel(gridPersonnelDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getGridPersonnelById")
    @ApiOperation("根据ID获取网格人员信息")
    public ResultDTO<GridPersonnelVO> getGridPersonnelById(Long id) {
        ResultDTO<GridPersonnelVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        GridPersonnelVO gridPersonnelVO = gridPersonnelService.getGridPersonnelById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", gridPersonnelVO);
    }

    @PostMapping("batchRemoveGridPersonnelByIds")
    @ApiOperation("根据ID批量删除网格人员信息")
    @OpLog(title = "根据ID批量删除网格人员信息", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveGridPersonnelByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        gridPersonnelService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
