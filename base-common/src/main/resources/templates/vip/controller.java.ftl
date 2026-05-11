package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if entity?starts_with('Rel')>
<#else>
import ${BasePackagePath}.common.core.annotation.OpLog;
import ${BasePackagePath}.common.core.dto.ResultDTO;
import ${BasePackagePath}.common.core.enums.BusinessTypeEnum;
import ${BasePackagePath}.common.core.enums.ResultCodeEnum;
import ${BasePackagePath}.common.core.utils.CommonFuntions;
import ${DtoPath}.${entity}DTO;
import ${VoPath}.${entity}VO;
import ${QueryPath}.${entity}Query;
import ${package.Service}.${table.serviceName};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

</#if>
<#if swagger>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import java.util.List;

/**
 * <p>
 * ${table.comment!} 管理
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/d-admin/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if swagger>
@Api(tags = {"${table.comment!}管理"})
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    <#if entity?starts_with('Rel')>
    <#else>
    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @PostMapping("find${entity}Page")
        <#if swagger>
    @ApiOperation("分页查询${table.comment!}")
        </#if>
    public ResultDTO<Page<${entity}VO>> find${entity}Page(HttpServletRequest request, @RequestBody ${entity}Query query) {
        ResultDTO<Page<${entity}VO>> result = new ResultDTO<>();
        Page<${entity}VO> page = ${table.serviceName?uncap_first}.find${entity}Page(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdate${entity}")
        <#if swagger>
    @ApiOperation("保存或更新${table.comment!}")
        </#if>
    @OpLog(title = "保存或更新${table.comment!}", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdate${entity}(HttpServletRequest request, @RequestBody ${entity}DTO ${entity?uncap_first}DTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = ${table.serviceName?uncap_first}.saveOrUpdate${entity}(${entity?uncap_first}DTO);
        if (id > 0) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

        <#list table.fields as field>
            <#if field.keyFlag>
    @GetMapping("get${entity}By${field.propertyName?cap_first}")
        <#if swagger>
    @ApiOperation("根据ID获取${table.comment!}")
        </#if>
    public ResultDTO<${entity}VO> get${entity}By${field.propertyName?cap_first}(${field.propertyType} ${field.propertyName?uncap_first}) {
        ResultDTO<${entity}VO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(${field.propertyName?uncap_first})) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,${field.propertyName?uncap_first}为空");
        }
        ${entity}VO ${entity?uncap_first}VO = ${table.serviceName?uncap_first}.get${entity}By${field.propertyName?cap_first}(${field.propertyName?uncap_first});
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", ${entity?uncap_first}VO);
    }

    @PostMapping("batchRemove${entity}By${field.propertyName?cap_first}s")
        <#if swagger>
    @ApiOperation("根据ID批量删除${table.comment!}")
        </#if>
    @OpLog(title = "根据ID批量删除${table.comment!}", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemove${entity}By${field.propertyName?cap_first}s(List<${field.propertyType}> ${field.propertyName?uncap_first}s) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(${field.propertyName?uncap_first}s)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,${field.propertyName?uncap_first}s为空");
        }
        ${table.serviceName?uncap_first}.removeByIds(${field.propertyName?uncap_first}s);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
            </#if>
        </#list>
    </#if>
}
</#if>
