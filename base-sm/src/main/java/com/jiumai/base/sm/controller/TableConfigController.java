package com.jiumai.base.sm.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.sm.dto.TableConfigDTO;
import com.jiumai.base.sm.entity.TableConfig;
import com.jiumai.base.sm.service.TableConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mysql gen
 * @since 2023-02-23
 */
@Api(tags = "表格配置接口")
@RestController
@RequestMapping("/d-admin/tableConfig")
public class TableConfigController {

    @Autowired
    private TableConfigService tableConfigService;

    @PostMapping("saveTableConfig")
    @ApiOperation("保存表格配置")
    public ResultDTO<Void> saveTableConfig(HttpServletRequest request, @RequestBody TableConfigDTO tableConfigDTO) {
        ResultDTO<Void> result = new ResultDTO<>();

        Long operatorId = OperatorUtil.getOperatorId(request);

        tableConfigService.saveTableConfig(tableConfigDTO, operatorId);

        return result.set(ResultCodeEnum.SUCCESS);
    }


    @PostMapping("getTableConfig")
    @ApiOperation("获取表格配置")
    public ResultDTO<TableConfigDTO> getTableConfig(HttpServletRequest request, String tableCode) {
        ResultDTO<TableConfigDTO> result = new ResultDTO<>();

        if(StringUtils.isBlank(tableCode)){
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS,"表格编码必填");
        }
        Long operatorId = OperatorUtil.getOperatorId(request);

        LambdaQueryWrapper<TableConfig> wrapper = Wrappers.lambdaQuery(TableConfig.class)
                .eq(TableConfig::getOpId, operatorId)
                .eq(TableConfig::getTableCode, tableCode);

        TableConfig dbTableConfig = tableConfigService.getOne(wrapper, false);

        TableConfigDTO tableConfigDTO = BeanUtil.copyProperties(dbTableConfig, TableConfigDTO.class);

        return result.set(ResultCodeEnum.SUCCESS, null, tableConfigDTO);
    }

}
