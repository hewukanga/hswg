package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.RenewalBillDTO;
import com.jiumai.base.biz.query.RenewalBillQuery;
import com.jiumai.base.biz.service.RenewalBillService;
import com.jiumai.base.biz.vo.RenewalBillVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 续费账单 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/renewal-bill")
@Api(tags = {"续费账单管理"})
public class RenewalBillController {

    @Resource
    private RenewalBillService renewalBillService;

    @PostMapping("findRenewalBillPage")
    @ApiOperation("分页查询续费账单")
    public ResultDTO<Page<RenewalBillVO>> findRenewalBillPage(HttpServletRequest request, @RequestBody RenewalBillQuery query) {
        ResultDTO<Page<RenewalBillVO>> result = new ResultDTO<>();
        Page<RenewalBillVO> page = renewalBillService.findRenewalBillPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateRenewalBill")
    @ApiOperation("保存或更新续费账单")
    @OpLog(title = "保存或更新续费账单", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateRenewalBill(HttpServletRequest request, @RequestBody RenewalBillDTO renewalBillDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = renewalBillService.saveOrUpdateRenewalBill(renewalBillDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getRenewalBillById")
    @ApiOperation("根据ID获取续费账单")
    public ResultDTO<RenewalBillVO> getRenewalBillById(Long id) {
        ResultDTO<RenewalBillVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        RenewalBillVO renewalBillVO = renewalBillService.getRenewalBillById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", renewalBillVO);
    }

    @PostMapping("batchRemoveRenewalBillByIds")
    @ApiOperation("根据ID批量删除续费账单")
    @OpLog(title = "根据ID批量删除续费账单", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveRenewalBillByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        renewalBillService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
