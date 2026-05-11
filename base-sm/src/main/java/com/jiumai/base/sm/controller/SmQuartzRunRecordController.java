package com.jiumai.base.sm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.sm.entity.SmQuartzRunRecord;
import com.jiumai.base.sm.query.QuartzRecordQuery;
import com.jiumai.base.sm.service.SmQuartzRunRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 定时任务执行记录 前端控制器
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-03
 */
@RestController
@RequestMapping("/d-admin/smQuartzRunRecord")
@Api(tags = "定时任务执行记录")
public class SmQuartzRunRecordController {

    @Resource
    private SmQuartzRunRecordService smQuartzRunRecordService;

    @PostMapping("quartzRunRecordPaging")
    @ApiOperation(value = "定时任务执行记录分页查询")
    public ResultDTO<IPage<SmQuartzRunRecord>> quartzRunRecordPaging(QuartzRecordQuery quartzRecordQuery){
        IPage<SmQuartzRunRecord> page = smQuartzRunRecordService.quartzRunRecordPaging(quartzRecordQuery);
        return new ResultDTO<IPage<SmQuartzRunRecord>>().set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }
}
