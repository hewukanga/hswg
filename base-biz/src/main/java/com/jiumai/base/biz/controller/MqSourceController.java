package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MqSourceDTO;
import com.jiumai.base.biz.query.MqSourceQuery;
import com.jiumai.base.biz.service.MqSourceService;
import com.jiumai.base.biz.vo.MqSourceVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 消息队列消息原文 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/mq-source")
@Api(tags = {"消息队列消息原文管理"})
public class MqSourceController {

    @Resource
    private MqSourceService mqSourceService;

    @PostMapping("findMqSourcePage")
    @ApiOperation("分页查询消息队列消息原文")
    public ResultDTO<Page<MqSourceVO>> findMqSourcePage(HttpServletRequest request, @RequestBody MqSourceQuery query) {
        ResultDTO<Page<MqSourceVO>> result = new ResultDTO<>();
        Page<MqSourceVO> page = mqSourceService.findMqSourcePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateMqSource")
    @ApiOperation("保存或更新消息队列消息原文")
    @OpLog(title = "保存或更新消息队列消息原文", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<String> saveOrUpdateMqSource(HttpServletRequest request, @RequestBody MqSourceDTO mqSourceDTO) {
        ResultDTO<String> result = new ResultDTO<>();
        String id = mqSourceService.saveOrUpdateMqSource(mqSourceDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getMqSourceById")
    @ApiOperation("根据ID获取消息队列消息原文")
    public ResultDTO<MqSourceVO> getMqSourceById(String id) {
        ResultDTO<MqSourceVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        MqSourceVO mqSourceVO = mqSourceService.getMqSourceById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", mqSourceVO);
    }

    @PostMapping("batchRemoveMqSourceByIds")
    @ApiOperation("根据ID批量删除消息队列消息原文")
    @OpLog(title = "根据ID批量删除消息队列消息原文", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveMqSourceByIds(@RequestBody List<String> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        mqSourceService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
