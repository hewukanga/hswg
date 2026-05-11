package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.OilCardRecordDTO;
import com.jiumai.base.biz.query.OilCardRecordQuery;
import com.jiumai.base.biz.service.OilCardRecordService;
import com.jiumai.base.biz.vo.OilCardRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 油卡上报记录表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/oil-card-record")
@Api(tags = {"油卡上报记录表管理"})
public class OilCardRecordController {

    @Resource
    private OilCardRecordService oilCardRecordService;

    @PostMapping("findOilCardRecordPage")
    @ApiOperation("分页查询油卡上报记录表")
    public ResultDTO<Page<OilCardRecordVO>> findOilCardRecordPage(HttpServletRequest request, @RequestBody OilCardRecordQuery query) {
        ResultDTO<Page<OilCardRecordVO>> result = new ResultDTO<>();
        Page<OilCardRecordVO> page = oilCardRecordService.findOilCardRecordPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateOilCardRecord")
    @ApiOperation("保存或更新油卡上报记录表")
    @OpLog(title = "保存或更新油卡上报记录表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateOilCardRecord(HttpServletRequest request, @RequestBody OilCardRecordDTO oilCardRecordDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = oilCardRecordService.saveOrUpdateOilCardRecord(oilCardRecordDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getOilCardRecordById")
    @ApiOperation("根据ID获取油卡上报记录表")
    public ResultDTO<OilCardRecordVO> getOilCardRecordById(Long id) {
        ResultDTO<OilCardRecordVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        OilCardRecordVO oilCardRecordVO = oilCardRecordService.getOilCardRecordById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", oilCardRecordVO);
    }

    @PostMapping("batchRemoveOilCardRecordByIds")
    @ApiOperation("根据ID批量删除油卡上报记录表")
    @OpLog(title = "根据ID批量删除油卡上报记录表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveOilCardRecordByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        oilCardRecordService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
