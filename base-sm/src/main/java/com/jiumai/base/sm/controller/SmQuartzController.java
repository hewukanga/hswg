package com.jiumai.base.sm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.sm.dto.SmQuartzDTO;
import com.jiumai.base.sm.query.QuartzQuery;
import com.jiumai.base.sm.service.SmQuartzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-02
 */
@RestController
@RequestMapping("/d-admin/quartz")
@Api(tags = "定时任务控制器")
public class SmQuartzController {

    @Resource
    private SmQuartzService smQuartzService;

    @PostMapping("quartzPaging")
    @ApiOperation(value = "定时任务分页列表")
    public ResultDTO<IPage<SmQuartzDTO>> quartzPaging(@RequestBody QuartzQuery quartzQuery) {
        IPage<SmQuartzDTO> iPage = smQuartzService.quartzPaging(quartzQuery);
        return new ResultDTO<IPage<SmQuartzDTO>>().set(ResultCodeEnum.SUCCESS, "查询成功", iPage);
    }

    @PostMapping("saveQuartz")
    @ApiOperation(value = "保存定时任务")
    public ResultDTO saveQuartz(@RequestBody SmQuartzDTO smQuartzDTO) throws ClassNotFoundException, SchedulerException {
        smQuartzService.saveQuartz(smQuartzDTO);
        return new ResultDTO<>().set(ResultCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "判断定时任务是否存在", notes = "false不存在 true存在")
    @GetMapping("checkTriggerExist")
    public ResultDTO<Boolean> checkTriggerExist(@RequestParam("triggerName") String triggerName, @RequestParam("triggerGroup") String triggerGroup) throws SchedulerException {
        return new ResultDTO<Boolean>().set(ResultCodeEnum.SUCCESS, "判断成功", smQuartzService.checkTriggerExist(triggerName, triggerGroup));
    }

    @PostMapping("/pauseQuartz")
    @ApiOperation(value = "暂停定时任务")
    public ResultDTO pauseQuartz(@RequestBody SmQuartzDTO smQuartzDTO) throws SchedulerException {
        smQuartzService.pauseQuartz(smQuartzDTO);
        return new ResultDTO().set(ResultCodeEnum.SUCCESS, "暂停定时任务成功");
    }

    @PostMapping("/resumeQuartz")
    @ApiOperation(value = "恢复定时任务")
    public ResultDTO resumeQuartz(@RequestBody SmQuartzDTO smQuartzDTO) throws SchedulerException {
        smQuartzService.resumeQuartz(smQuartzDTO);
        return new ResultDTO().set(ResultCodeEnum.SUCCESS, "恢复任务成功");
    }

    @PostMapping("/updateQuartz")
    @ApiOperation(value = "修改定时任务")
    public ResultDTO updateQuartz(@RequestBody SmQuartzDTO smQuartzDTO) throws SchedulerException, ClassNotFoundException {
        smQuartzService.updateQuartz(smQuartzDTO);
        return new ResultDTO().set(ResultCodeEnum.SUCCESS, "修改任务成功");
    }

    @PostMapping("/deleteQuartz")
    @ApiOperation(value = "删除任务")
    public ResultDTO deleteQuartz(@RequestBody SmQuartzDTO smQuartzDTO) throws SchedulerException {
        smQuartzService.deleteQuartz(smQuartzDTO);
        return new ResultDTO().set(ResultCodeEnum.SUCCESS, "删除任务成功");
    }

}
