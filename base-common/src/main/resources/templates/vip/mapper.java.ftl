package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if entity?starts_with('Rel')>
<#else>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import ${DtoPath}.${entity}DTO;
import ${VoPath}.${entity}VO;
import ${QueryPath}.${entity}Query;
</#if>
import java.util.List;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    <#if entity?starts_with('Rel')>
    <#else>
    /**
    * 分页查询${table.comment!}
    * @param page
    * @param query
    * @return
    */
    List<${entity}VO> find${entity}Page(Page<${entity}VO> page, @Param("query") ${entity}Query query);
    </#if>
}
</#if>
