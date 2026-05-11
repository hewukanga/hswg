package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MqttConfigDTO;
import com.jiumai.base.biz.query.MqttConfigQuery;
import com.jiumai.base.biz.service.MqttConfigService;
import com.jiumai.base.biz.vo.MqttConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * mqtt参数表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/mqtt-config")
@Api(tags = {"mqtt参数表管理"})
public class MqttConfigController {

    @Resource
    private MqttConfigService mqttConfigService;

    @PostMapping("findMqttConfigPage")
    @ApiOperation("分页查询mqtt参数表")
    public ResultDTO<Page<MqttConfigVO>> findMqttConfigPage(HttpServletRequest request, @RequestBody MqttConfigQuery query) {
        ResultDTO<Page<MqttConfigVO>> result = new ResultDTO<>();
        Page<MqttConfigVO> page = mqttConfigService.findMqttConfigPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateMqttConfig")
    @ApiOperation("保存或更新mqtt参数表")
    @OpLog(title = "保存或更新mqtt参数表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Integer> saveOrUpdateMqttConfig(HttpServletRequest request, @RequestBody MqttConfigDTO mqttConfigDTO) {
        ResultDTO<Integer> result = new ResultDTO<>();
        Integer id = mqttConfigService.saveOrUpdateMqttConfig(mqttConfigDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getMqttConfigById")
    @ApiOperation("根据ID获取mqtt参数表")
    public ResultDTO<MqttConfigVO> getMqttConfigById(Integer id) {
        ResultDTO<MqttConfigVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        MqttConfigVO mqttConfigVO = mqttConfigService.getMqttConfigById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", mqttConfigVO);
    }

    @PostMapping("batchRemoveMqttConfigByIds")
    @ApiOperation("根据ID批量删除mqtt参数表")
    @OpLog(title = "根据ID批量删除mqtt参数表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveMqttConfigByIds(@RequestBody List<Integer> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        mqttConfigService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
