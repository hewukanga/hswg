package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.TemporaryParkingDTO;
import com.jiumai.base.biz.query.TemporaryParkingQuery;
import com.jiumai.base.biz.service.TemporaryParkingService;
import com.jiumai.base.biz.vo.TemporaryParkingVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 临停车(数泊推送) 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/temporary-parking")
@Api(tags = {"临停车(数泊推送)管理"})
public class TemporaryParkingController {

    @Resource
    private TemporaryParkingService temporaryParkingService;

    @PostMapping("findTemporaryParkingPage")
    @ApiOperation("分页查询临停车(数泊推送)")
    public ResultDTO<Page<TemporaryParkingVO>> findTemporaryParkingPage(HttpServletRequest request, @RequestBody TemporaryParkingQuery query) {
        ResultDTO<Page<TemporaryParkingVO>> result = new ResultDTO<>();
        Page<TemporaryParkingVO> page = temporaryParkingService.findTemporaryParkingPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateTemporaryParking")
    @ApiOperation("保存或更新临停车(数泊推送)")
    @OpLog(title = "保存或更新临停车(数泊推送)", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateTemporaryParking(HttpServletRequest request, @RequestBody TemporaryParkingDTO temporaryParkingDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = temporaryParkingService.saveOrUpdateTemporaryParking(temporaryParkingDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getTemporaryParkingById")
    @ApiOperation("根据ID获取临停车(数泊推送)")
    public ResultDTO<TemporaryParkingVO> getTemporaryParkingById(Long id) {
        ResultDTO<TemporaryParkingVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        TemporaryParkingVO temporaryParkingVO = temporaryParkingService.getTemporaryParkingById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", temporaryParkingVO);
    }

    @PostMapping("batchRemoveTemporaryParkingByIds")
    @ApiOperation("根据ID批量删除临停车(数泊推送)")
    @OpLog(title = "根据ID批量删除临停车(数泊推送)", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveTemporaryParkingByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        temporaryParkingService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
