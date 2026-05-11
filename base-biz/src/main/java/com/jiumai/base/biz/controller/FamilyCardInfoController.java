package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.FamilyCardInfoDTO;
import com.jiumai.base.biz.query.FamilyCardInfoQuery;
import com.jiumai.base.biz.service.FamilyCardInfoService;
import com.jiumai.base.biz.vo.FamilyCardInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 亲情卡数据 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/family-card-info")
@Api(tags = {"亲情卡数据管理"})
public class FamilyCardInfoController {

    @Resource
    private FamilyCardInfoService familyCardInfoService;

    @PostMapping("findFamilyCardInfoPage")
    @ApiOperation("分页查询亲情卡数据")
    public ResultDTO<Page<FamilyCardInfoVO>> findFamilyCardInfoPage(HttpServletRequest request, @RequestBody FamilyCardInfoQuery query) {
        ResultDTO<Page<FamilyCardInfoVO>> result = new ResultDTO<>();
        Page<FamilyCardInfoVO> page = familyCardInfoService.findFamilyCardInfoPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateFamilyCardInfo")
    @ApiOperation("保存或更新亲情卡数据")
    @OpLog(title = "保存或更新亲情卡数据", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateFamilyCardInfo(HttpServletRequest request, @RequestBody FamilyCardInfoDTO familyCardInfoDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = familyCardInfoService.saveOrUpdateFamilyCardInfo(familyCardInfoDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getFamilyCardInfoById")
    @ApiOperation("根据ID获取亲情卡数据")
    public ResultDTO<FamilyCardInfoVO> getFamilyCardInfoById(Long id) {
        ResultDTO<FamilyCardInfoVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        FamilyCardInfoVO familyCardInfoVO = familyCardInfoService.getFamilyCardInfoById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", familyCardInfoVO);
    }

    @PostMapping("batchRemoveFamilyCardInfoByIds")
    @ApiOperation("根据ID批量删除亲情卡数据")
    @OpLog(title = "根据ID批量删除亲情卡数据", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveFamilyCardInfoByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        familyCardInfoService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
