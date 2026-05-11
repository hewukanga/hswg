package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.HelpDTO;
import com.jiumai.base.biz.query.HelpQuery;
import com.jiumai.base.biz.service.HelpService;
import com.jiumai.base.biz.vo.HelpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 帮扶 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/help")
@Api(tags = {"帮扶管理"})
public class HelpController {

    @Resource
    private HelpService helpService;

    @PostMapping("findHelpPage")
    @ApiOperation("分页查询帮扶")
    public ResultDTO<Page<HelpVO>> findHelpPage(HttpServletRequest request, @RequestBody HelpQuery query) {
        ResultDTO<Page<HelpVO>> result = new ResultDTO<>();
        Page<HelpVO> page = helpService.findHelpPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateHelp")
    @ApiOperation("保存或更新帮扶")
    @OpLog(title = "保存或更新帮扶", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateHelp(HttpServletRequest request, @RequestBody HelpDTO helpDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = helpService.saveOrUpdateHelp(helpDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getHelpById")
    @ApiOperation("根据ID获取帮扶")
    public ResultDTO<HelpVO> getHelpById(Long id) {
        ResultDTO<HelpVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        HelpVO helpVO = helpService.getHelpById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", helpVO);
    }

    @PostMapping("batchRemoveHelpByIds")
    @ApiOperation("根据ID批量删除帮扶")
    @OpLog(title = "根据ID批量删除帮扶", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveHelpByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        helpService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
