package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PayMonthlyRuleDTO;
import com.jiumai.base.biz.query.PayMonthlyRuleQuery;
import com.jiumai.base.biz.service.PayMonthlyRuleService;
import com.jiumai.base.biz.vo.PayMonthlyRuleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 包月规则(数泊推送) 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/pay-monthly-rule")
@Api(tags = {"包月规则(数泊推送)管理"})
public class PayMonthlyRuleController {

    @Resource
    private PayMonthlyRuleService payMonthlyRuleService;

    @PostMapping("findPayMonthlyRulePage")
    @ApiOperation("分页查询包月规则(数泊推送)")
    public ResultDTO<Page<PayMonthlyRuleVO>> findPayMonthlyRulePage(HttpServletRequest request, @RequestBody PayMonthlyRuleQuery query) {
        ResultDTO<Page<PayMonthlyRuleVO>> result = new ResultDTO<>();
        Page<PayMonthlyRuleVO> page = payMonthlyRuleService.findPayMonthlyRulePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePayMonthlyRule")
    @ApiOperation("保存或更新包月规则(数泊推送)")
    @OpLog(title = "保存或更新包月规则(数泊推送)", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePayMonthlyRule(HttpServletRequest request, @RequestBody PayMonthlyRuleDTO payMonthlyRuleDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = payMonthlyRuleService.saveOrUpdatePayMonthlyRule(payMonthlyRuleDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPayMonthlyRuleById")
    @ApiOperation("根据ID获取包月规则(数泊推送)")
    public ResultDTO<PayMonthlyRuleVO> getPayMonthlyRuleById(Long id) {
        ResultDTO<PayMonthlyRuleVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PayMonthlyRuleVO payMonthlyRuleVO = payMonthlyRuleService.getPayMonthlyRuleById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", payMonthlyRuleVO);
    }

    @PostMapping("batchRemovePayMonthlyRuleByIds")
    @ApiOperation("根据ID批量删除包月规则(数泊推送)")
    @OpLog(title = "根据ID批量删除包月规则(数泊推送)", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePayMonthlyRuleByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        payMonthlyRuleService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
