package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.LawEnforcementInstrumentManageDTO;
import com.jiumai.base.biz.query.LawEnforcementInstrumentManageQuery;
import com.jiumai.base.biz.service.LawEnforcementInstrumentManageService;
import com.jiumai.base.biz.vo.LawEnforcementInstrumentManageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 执法仪管理 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/law-enforcement-instrument-manage")
@Api(tags = {"执法仪管理管理"})
public class LawEnforcementInstrumentManageController {

    @Resource
    private LawEnforcementInstrumentManageService lawEnforcementInstrumentManageService;

    @PostMapping("findLawEnforcementInstrumentManagePage")
    @ApiOperation("分页查询执法仪管理")
    public ResultDTO<Page<LawEnforcementInstrumentManageVO>> findLawEnforcementInstrumentManagePage(HttpServletRequest request, @RequestBody LawEnforcementInstrumentManageQuery query) {
        ResultDTO<Page<LawEnforcementInstrumentManageVO>> result = new ResultDTO<>();
        Page<LawEnforcementInstrumentManageVO> page = lawEnforcementInstrumentManageService.findLawEnforcementInstrumentManagePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateLawEnforcementInstrumentManage")
    @ApiOperation("保存或更新执法仪管理")
    @OpLog(title = "保存或更新执法仪管理", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateLawEnforcementInstrumentManage(HttpServletRequest request, @RequestBody LawEnforcementInstrumentManageDTO lawEnforcementInstrumentManageDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = lawEnforcementInstrumentManageService.saveOrUpdateLawEnforcementInstrumentManage(lawEnforcementInstrumentManageDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getLawEnforcementInstrumentManageById")
    @ApiOperation("根据ID获取执法仪管理")
    public ResultDTO<LawEnforcementInstrumentManageVO> getLawEnforcementInstrumentManageById(Long id) {
        ResultDTO<LawEnforcementInstrumentManageVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        LawEnforcementInstrumentManageVO lawEnforcementInstrumentManageVO = lawEnforcementInstrumentManageService.getLawEnforcementInstrumentManageById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", lawEnforcementInstrumentManageVO);
    }

    @PostMapping("batchRemoveLawEnforcementInstrumentManageByIds")
    @ApiOperation("根据ID批量删除执法仪管理")
    @OpLog(title = "根据ID批量删除执法仪管理", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveLawEnforcementInstrumentManageByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        lawEnforcementInstrumentManageService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
