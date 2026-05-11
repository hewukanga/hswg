package com.jiumai.base.sm.controller;

import com.jiumai.base.sm.dto.InterfaceConfTreeDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.dto.InterfaceConfDTO;
import com.jiumai.base.sm.query.InterfaceConfQuery;
import com.jiumai.base.sm.service.InterfaceConfService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 接口管理 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2023-08-21
 */
@RestController
@RequestMapping("/d-admin/interface-conf")
@Api(tags = {"接口管理管理"})
public class InterfaceConfController {

    @Resource
    private InterfaceConfService interfaceConfService;

    @PostMapping("findInterfaceConfPage")
    @ApiOperation("分页查询接口管理")
    public ResultDTO<Page<InterfaceConfDTO>> findInterfaceConfPage(HttpServletRequest request, @RequestBody InterfaceConfQuery query) {
        ResultDTO<Page<InterfaceConfDTO>> result = new ResultDTO<>();
        Page<InterfaceConfDTO> page = interfaceConfService.findInterfaceConfPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateInterfaceConf")
    @ApiOperation("保存或更新接口管理")
    @OpLog(title = "保存或更新接口管理", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateInterfaceConf(HttpServletRequest request, @RequestBody InterfaceConfDTO interfaceConfDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = interfaceConfService.saveOrUpdateInterfaceConf(interfaceConfDTO);
        if (id > 0) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getInterfaceConfById")
    @ApiOperation("根据ID获取接口管理")
    public ResultDTO<InterfaceConfDTO> getInterfaceConfById(Long id) {
        ResultDTO<InterfaceConfDTO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        InterfaceConfDTO interfaceConfDTO = interfaceConfService.getInterfaceConfById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", interfaceConfDTO);
    }

    @PostMapping("removeInterfaceConfById")
    @ApiOperation("根据ID删除接口管理")
    @OpLog(title = "根据ID删除接口管理", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> removeInterfaceConfById(Long id) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,id为空");
        }
        interfaceConfService.removeInterfaceConfById(id);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }

    @GetMapping(value = "findInterfaceConfTree")
    @ApiOperation(value = "获取接口配置树形结构")
    public ResultDTO<List<InterfaceConfTreeDTO>> findInterfaceConfTree(){
        List<InterfaceConfTreeDTO> interfaceConfTreeDTOS = interfaceConfService.findInterfaceConfTree();
        return new ResultDTO<List<InterfaceConfTreeDTO>>().set(ResultCodeEnum.SUCCESS, "查询成功", interfaceConfTreeDTOS);
    }

}
