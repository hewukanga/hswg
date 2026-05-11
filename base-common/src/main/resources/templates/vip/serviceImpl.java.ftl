package ${package.ServiceImpl};

<#if entity?starts_with('Rel')>
<#else>
import ${BasePackagePath}.common.core.exception.BizException;
import ${BasePackagePath}.common.core.utils.CommonFuntions;
import ${DtoPath}.${entity}DTO;
import ${VoPath}.${entity}VO;
import ${QueryPath}.${entity}Query;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
</#if>
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    <#if entity?starts_with('Rel')>
    <#else>
    @Resource
    private ${table.mapperName} ${table.mapperName?uncap_first};

    @Override
    public Page<${entity}VO> find${entity}Page(${entity}Query query) {
        Page<${entity}VO> page = new Page<>(query.getPage(),query.getSize());
        List<${entity}VO> list = ${table.mapperName?uncap_first}.find${entity}Page(page, query);
        page.setRecords(list);

        return page;
    }

    @Override
    public Long saveOrUpdate${entity}(${entity}DTO ${entity?uncap_first}DTO){
        ${entity} ${entity?uncap_first} = new ${entity}();

        BeanUtils.copyProperties(${entity?uncap_first}DTO, ${entity?uncap_first});

        this.saveOrUpdate(${entity?uncap_first});

        return ${entity?uncap_first}.get<#list table.fields as field><#if field.keyFlag>${field.propertyName?cap_first}</#if></#list>();
    }

        <#list table.fields as field>
            <#if field.keyFlag>
    @Override
    public ${entity}VO get${entity}By${field.propertyName?cap_first}(${field.propertyType} ${field.propertyName?uncap_first}){
        ${entity} ${entity?uncap_first} = this.getById(${field.propertyName?uncap_first});
        if (CommonFuntions.isEmptyObject(${entity?uncap_first})){
            throw new BizException("查询失败，${table.comment!}不存在");
        }

        ${entity}VO ${entity?uncap_first}VO = new ${entity}VO();
        BeanUtils.copyProperties(${entity?uncap_first}, ${entity?uncap_first}VO);
        return ${entity?uncap_first}VO;
    }
            </#if>
        </#list>
    </#if>
}
</#if>
