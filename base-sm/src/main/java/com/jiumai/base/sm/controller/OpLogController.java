package com.jiumai.base.sm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.sm.entity.SmOpLog;
import com.jiumai.base.sm.query.OpLogQuery;
import com.jiumai.base.sm.service.OpLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = {"操作日志查询类"})
@Slf4j
@RestController
@RequestMapping("d-admin/opLog")
public class OpLogController {

    @Resource
    private OpLogService opLogService;

    @PostMapping("findOpLogPaging")
    @ApiOperation(value = "分页查询操作日志")
    public ResultDTO<IPage<SmOpLog>> findOpLogPaging(HttpServletRequest request, @RequestBody OpLogQuery query) {
        ResultDTO<IPage<SmOpLog>> resultDTO = new ResultDTO<>();
        IPage<SmOpLog> iPage = null;
        LoginOperator operator = OperatorUtil.getOperator(request);
        try {
            iPage = opLogService.findPaging(query, operator);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return resultDTO.set(ResultCodeEnum.FAIL);
        }
        return resultDTO.set(ResultCodeEnum.SUCCESS, "查询成功", iPage);
    }

    @GetMapping("getOpLogById")
    @ApiOperation("查询操作日志详情")
    public ResultDTO<SmOpLog> getOpLogById(Long opLogId) {
        ResultDTO<SmOpLog> resultDTO = new ResultDTO<>();
        SmOpLog smOpLog;
        try {
            smOpLog = opLogService.getOpLogById(opLogId);
        } catch (Exception e) {
            log.error("查询操作日志详情", e);
            return resultDTO.set(ResultCodeEnum.FAIL);
        }
        return resultDTO.set(ResultCodeEnum.SUCCESS, null, smOpLog);
    }

}
