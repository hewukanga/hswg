package com.jiumai.base.sm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.CoreConstant;
import com.jiumai.base.sm.dto.EnumConfDTO;
import com.jiumai.base.sm.entity.SmEnumConf;
import com.jiumai.base.sm.query.EnumQuery;
import com.jiumai.base.sm.service.EnumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"枚举类"})
@RestController
@RequestMapping("d-admin/enum")
public class EnumController {

    private static SysLog log = SysLogFactory.getLogger(EnumController.class);

    @Autowired
    private Environment env;

    @Autowired
    private EnumService enumService;

    @GetMapping("findEnumValue")
    @ApiOperation(value = "获取枚举", notes = "枚举类名以逗号隔开，返回map，key为枚举类名;</br>"
            + "人员状态：CodeStatusEnum；证件类型：IdentityTypeEnum；组织类型：OrgTypeEnum；"
            + "功能类型：ResourceTypeEnum；角色类型：RoleTypeEnum；是否：YesOrNoEnum；有效状态：IsValidEnum；")
    @ApiImplicitParam(paramType = "query", dataType = "String", name = "enumName", value = "枚举类字符串", example = "CodeStatusEnum,OrgTypeEnum", required = true, dataTypeClass = String.class)
    public ResultDTO<Map<String, Object>> findEnumValue(String enumName) throws Exception {
        env.getClass();
        ResultDTO<Map<String, Object>> resultDTO = new ResultDTO<>();
        Map<String, Object> map = new HashMap<>();
        if (CommonFuntions.isEmptyObject(enumName)) {
            return resultDTO.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "枚举名不能为空");
        }
        String[] split = enumName.split(",");
        for (String string : split) {
            Object[] objects = null;
            try {
                objects = this.getObjecEnum(string);
            } catch (Exception e) {
                log.error(e);
                return resultDTO.set(ResultCodeEnum.ERROR_UNKNOW, e.getMessage());
            }
            if (objects == null || objects.length == 0) {
                // 从数据库查询
                objects = this.getFromDB(string);
            }
            map.put(string, objects);
        }

        return resultDTO.set(ResultCodeEnum.SUCCESS, "获取成功", map);

    }

    @GetMapping("checkEnumCodeIsUniquen")
    @ApiOperation(value = "验证枚举编码是否存在", notes = "验证枚举编码是否存在")
    public ResultDTO<Void> checkEnumCodeIsUniquen(String enumCode) throws Exception {

        ResultDTO<Void> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(enumCode)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "枚举编码不能为空!");
        }
        if (this.enumService.checkEnumCodeIsUniquen(enumCode)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "枚举编码已存在!");
        }
        return result.set(ResultCodeEnum.SUCCESS, "");
    }


    @PostMapping("addEnums")
    @ApiOperation(value = "添加或者编辑Enum", notes = "添加或者编辑Enum")
    @OpLog(title = "添加或者编辑Enum", businessType = BusinessTypeEnum.INSERT_OR_UPDATE)
    public ResultDTO<Void> addEnums(@RequestBody List<SmEnumConf> enums) throws Exception {

        ResultDTO<Void> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(enums)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "枚举列表不能为空");
        }
        if (CommonFuntions.isEmptyObject(enums.get(0).getEnumCode())) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "枚举编码不能为空");
        }
//		 if (this.enumService.checkEnumCodeIsUniquen(enums.get(0).getEnumCode())) {
//		 return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "枚举编码已存在");
//		 }
        try {
            this.enumService.saveOrUpdateUserEnums(enums);
        } catch (Exception e) {
            log.error(e);
            result.set(ResultCodeEnum.ERROR_HANDLE, e.getMessage());
        }
        return result.set(ResultCodeEnum.SUCCESS, "保存成功");
    }


    @PostMapping("findEnumPaging")
    @ApiOperation(value = "查询Enum分页列表")
    public ResultDTO<IPage<EnumConfDTO>> findEnumPaging(@RequestBody EnumQuery enumQuery, HttpServletRequest request) {
        IPage<EnumConfDTO> enumPaging = this.enumService.findEnumPaging(enumQuery);
        return new ResultDTO<IPage<EnumConfDTO>>().set(ResultCodeEnum.SUCCESS, null, enumPaging);
    }


    @GetMapping("syncSysEnums")
    @ApiOperation(value = "同步系统Enum")
    @OpLog(title = "同步系统Enum", businessType = BusinessTypeEnum.UPDATE)
    public ResultDTO<String> syncSysEnums(HttpServletRequest request) throws Exception {
        List<SmEnumConf> list = new ArrayList<SmEnumConf>();
        ResultDTO<String> re = new ResultDTO<String>();
        try {
            // 插入新的枚举值
            for (List<SmEnumConf> item : CoreConstant.enumMaps.values()) {
                // sqlManager.insertBatch(SmEnumConf.class, list);
                list.addAll(item);
            }
            this.enumService.batchUpdateSysEnums(list);
            re.set(ResultCodeEnum.SUCCESS, "同步Enum成功", "");
        } catch (Exception ex) {
            log.error(ex);
            re.set(ResultCodeEnum.ERROR_UNKNOW, "同步Enum失败", "");
        }
        return re;
    }


    @PostMapping("deleteBatchEnums")
    @ApiOperation(value = "删除Enum")
    @OpLog(title = "删除Enum", businessType = BusinessTypeEnum.DELETE)
    public ResultDTO<Void> deleteBatchEnums(@RequestBody List<Long> enumIds) throws Exception {

        ResultDTO<Void> result = new ResultDTO<>();
        try {
            this.enumService.deleteBatchEnums(enumIds);
        } catch (Exception e) {
            log.error(e);
            result.set(ResultCodeEnum.ERROR_HANDLE, "删除失败");
        }

        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }


    private Object[] getFromDB(String enumName) throws Exception {
        List<SmEnumConf> enums = this.enumService.findEnumConfByEnumCode(enumName);
        return this.mkResult(enums);
    }

    private Object[] mkResult(List<SmEnumConf> enums) {
        Object[] result = new Object[enums.size()];
        int i = 0;
        for (SmEnumConf conf : enums) {
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("value", conf.getEntityValue());
            temp.put("name", conf.getEntityName());
            if (conf.getRefEntityValue() != null) {
                temp.put("parentValue", conf.getRefEntityValue());
            }
            result[i] = temp;
            i++;
        }
        return result;
    }

    private Object[] getObjecEnum(String enumName) throws Exception {
        List<SmEnumConf> enums = CoreConstant.enumMaps.get(enumName);
        if (enums == null) {
            // throw new Exception(enumName + "类不存在");
            return null;
        }
        return mkResult(enums);
    }

}
