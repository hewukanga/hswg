package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.KeepGreenDTO;
import com.jiumai.base.biz.query.KeepGreenQuery;
import com.jiumai.base.biz.service.KeepGreenService;
import com.jiumai.base.biz.vo.KeepGreenVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 保绿 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/keep-green")
@Api(tags = {"保绿管理"})
public class KeepGreenController {

    @Resource
    private KeepGreenService keepGreenService;

    @PostMapping("findKeepGreenPage")
    @ApiOperation("分页查询保绿")
    public ResultDTO<Page<KeepGreenVO>> findKeepGreenPage(HttpServletRequest request, @RequestBody KeepGreenQuery query) {
        ResultDTO<Page<KeepGreenVO>> result = new ResultDTO<>();
        Page<KeepGreenVO> page = keepGreenService.findKeepGreenPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateKeepGreen")
    @ApiOperation("保存或更新保绿")
    @OpLog(title = "保存或更新保绿", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateKeepGreen(HttpServletRequest request, @RequestBody KeepGreenDTO keepGreenDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = keepGreenService.saveOrUpdateKeepGreen(keepGreenDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getKeepGreenById")
    @ApiOperation("根据ID获取保绿")
    public ResultDTO<KeepGreenVO> getKeepGreenById(Long id) {
        ResultDTO<KeepGreenVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        KeepGreenVO keepGreenVO = keepGreenService.getKeepGreenById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", keepGreenVO);
    }

    @PostMapping("batchRemoveKeepGreenByIds")
    @ApiOperation("根据ID批量删除保绿")
    @OpLog(title = "根据ID批量删除保绿", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveKeepGreenByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        keepGreenService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
