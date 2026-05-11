package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.UtilityBillDTO;
import com.jiumai.base.biz.query.UtilityBillQuery;
import com.jiumai.base.biz.service.UtilityBillService;
import com.jiumai.base.biz.vo.UtilityBillVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 物业账单 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/utility-bill")
@Api(tags = {"物业账单管理"})
public class UtilityBillController {

    @Resource
    private UtilityBillService utilityBillService;

    @PostMapping("findUtilityBillPage")
    @ApiOperation("分页查询物业账单")
    public ResultDTO<Page<UtilityBillVO>> findUtilityBillPage(HttpServletRequest request, @RequestBody UtilityBillQuery query) {
        ResultDTO<Page<UtilityBillVO>> result = new ResultDTO<>();
        Page<UtilityBillVO> page = utilityBillService.findUtilityBillPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateUtilityBill")
    @ApiOperation("保存或更新物业账单")
    @OpLog(title = "保存或更新物业账单", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateUtilityBill(HttpServletRequest request, @RequestBody UtilityBillDTO utilityBillDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = utilityBillService.saveOrUpdateUtilityBill(utilityBillDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getUtilityBillById")
    @ApiOperation("根据ID获取物业账单")
    public ResultDTO<UtilityBillVO> getUtilityBillById(Long id) {
        ResultDTO<UtilityBillVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        UtilityBillVO utilityBillVO = utilityBillService.getUtilityBillById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", utilityBillVO);
    }

    @PostMapping("batchRemoveUtilityBillByIds")
    @ApiOperation("根据ID批量删除物业账单")
    @OpLog(title = "根据ID批量删除物业账单", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveUtilityBillByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        utilityBillService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
