package ${package.Service};

<#if entity?starts_with('Rel')>
<#else>
import ${DtoPath}.${entity}DTO;
import ${VoPath}.${entity}VO;
import ${QueryPath}.${entity}Query;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
</#if>
import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    <#if entity?starts_with('Rel')>
    <#else>

    /**
     * 分页查询${table.comment!}
     * @param query
     * @return
     */
    Page<${entity}VO> find${entity}Page(${entity}Query query);

    /**
     * 添加或更新${table.comment!}
     * @param ${entity?uncap_first}DTO
     * @return
     */
    Long saveOrUpdate${entity}(${entity}DTO ${entity?uncap_first}DTO);

        <#list table.fields as field>
            <#if field.keyFlag>
    /**
     * 通过${field.propertyName?uncap_first}查询${table.comment!}详情
     * @param ${field.propertyName?uncap_first}
     * @return
     */
    ${entity}VO get${entity}By${field.propertyName?cap_first}(${field.propertyType} ${field.propertyName?uncap_first});
            </#if>
        </#list>
    </#if>

}
</#if>
