package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PayMonthlyUserInfoDTO;
import com.jiumai.base.biz.query.PayMonthlyUserInfoQuery;
import com.jiumai.base.biz.service.PayMonthlyUserInfoService;
import com.jiumai.base.biz.vo.PayMonthlyUserInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 包月用户信息(数泊推送) 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/pay-monthly-user-info")
@Api(tags = {"包月用户信息(数泊推送)管理"})
public class PayMonthlyUserInfoController {

    @Resource
    private PayMonthlyUserInfoService payMonthlyUserInfoService;

    @PostMapping("findPayMonthlyUserInfoPage")
    @ApiOperation("分页查询包月用户信息(数泊推送)")
    public ResultDTO<Page<PayMonthlyUserInfoVO>> findPayMonthlyUserInfoPage(HttpServletRequest request, @RequestBody PayMonthlyUserInfoQuery query) {
        ResultDTO<Page<PayMonthlyUserInfoVO>> result = new ResultDTO<>();
        Page<PayMonthlyUserInfoVO> page = payMonthlyUserInfoService.findPayMonthlyUserInfoPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePayMonthlyUserInfo")
    @ApiOperation("保存或更新包月用户信息(数泊推送)")
    @OpLog(title = "保存或更新包月用户信息(数泊推送)", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePayMonthlyUserInfo(HttpServletRequest request, @RequestBody PayMonthlyUserInfoDTO payMonthlyUserInfoDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = payMonthlyUserInfoService.saveOrUpdatePayMonthlyUserInfo(payMonthlyUserInfoDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPayMonthlyUserInfoById")
    @ApiOperation("根据ID获取包月用户信息(数泊推送)")
    public ResultDTO<PayMonthlyUserInfoVO> getPayMonthlyUserInfoById(Long id) {
        ResultDTO<PayMonthlyUserInfoVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PayMonthlyUserInfoVO payMonthlyUserInfoVO = payMonthlyUserInfoService.getPayMonthlyUserInfoById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", payMonthlyUserInfoVO);
    }

    @PostMapping("batchRemovePayMonthlyUserInfoByIds")
    @ApiOperation("根据ID批量删除包月用户信息(数泊推送)")
    @OpLog(title = "根据ID批量删除包月用户信息(数泊推送)", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePayMonthlyUserInfoByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        payMonthlyUserInfoService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
