package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.ReplacementRecordDTO;
import com.jiumai.base.biz.query.ReplacementRecordQuery;
import com.jiumai.base.biz.service.ReplacementRecordService;
import com.jiumai.base.biz.vo.ReplacementRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * pm_replacement_record 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/replacement-record")
@Api(tags = {"pm_replacement_record管理"})
public class ReplacementRecordController {

    @Resource
    private ReplacementRecordService replacementRecordService;

    @PostMapping("findReplacementRecordPage")
    @ApiOperation("分页查询pm_replacement_record")
    public ResultDTO<Page<ReplacementRecordVO>> findReplacementRecordPage(HttpServletRequest request, @RequestBody ReplacementRecordQuery query) {
        ResultDTO<Page<ReplacementRecordVO>> result = new ResultDTO<>();
        Page<ReplacementRecordVO> page = replacementRecordService.findReplacementRecordPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateReplacementRecord")
    @ApiOperation("保存或更新pm_replacement_record")
    @OpLog(title = "保存或更新pm_replacement_record", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateReplacementRecord(HttpServletRequest request, @RequestBody ReplacementRecordDTO replacementRecordDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = replacementRecordService.saveOrUpdateReplacementRecord(replacementRecordDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getReplacementRecordById")
    @ApiOperation("根据ID获取pm_replacement_record")
    public ResultDTO<ReplacementRecordVO> getReplacementRecordById(Long id) {
        ResultDTO<ReplacementRecordVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        ReplacementRecordVO replacementRecordVO = replacementRecordService.getReplacementRecordById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", replacementRecordVO);
    }

    @PostMapping("batchRemoveReplacementRecordByIds")
    @ApiOperation("根据ID批量删除pm_replacement_record")
    @OpLog(title = "根据ID批量删除pm_replacement_record", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveReplacementRecordByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        replacementRecordService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
