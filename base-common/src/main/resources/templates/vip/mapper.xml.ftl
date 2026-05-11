<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>
</#if>

<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
<#list table.commonFields as field>
        ${field.name},
</#list>
        ${table.fieldNames}
    </sql>
</#if>

<#if entity?starts_with('Rel')>
<#else>
    <select id="find${entity}Page" resultType="${VoPath}.${entity}VO">
        SELECT <include refid="Base_Column_List"></include>
        FROM ${table.name}
        WHERE ${logicDeleteFieldName} = 1
    </select>
</#if>
</mapper>
