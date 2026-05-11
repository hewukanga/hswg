package com.jiumai.base.common.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成工具（新版）
 */
public class MysqlGenerator {
    // 项目包
    private static final String parent = "com.jiumai.base";
    // 作者
    private static final String author = "mysqlGen";
    // 模板路径
    private static final String templatePath = "/templates/vip/";
    private static final String sqlConnectionURL = "jdbc:mysql://119.3.15.112:3666/base?relaxAutoCommit=true&zeroDateTimeBehavior=convertToNull&characterEncoding=utf-8&useSSL=false";
    private static final String sqlUsername = "base";
    private static final String sqlPassword = "base123";

    public static void main(String[] args) {
        // 表名：逗号隔开
        String tableName = "pm_work_result";
        // 工程名称，maven工程的文件夹名称
        String mavenPackageName = "base-biz";
        // 工程内部的业务包名，如：com.jiumai.base.sm 其中的sm为业务包名
        String moduleName = "biz";
        // 需要忽略的表前缀
        String tablePreFix = "pm";

        genCode(tableName, tablePreFix, mavenPackageName, moduleName);

    }


    /**
     * 代码生成
     *
     * @param tableName
     */
    private static void genCode(String tableName, String tablePreFix, String mavenPackageName, String moduleName) {

        String projectPath = System.getProperty("user.dir") + "/" + mavenPackageName + "/"; //获取项目路径
        String filePath = projectPath + "/src/main/java";  //java下的文件路径

        // 策略配置开始
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .addInclude(tableName)
                .addTablePrefix(tablePreFix)//设置表前缀过滤
                .entityBuilder()
                .enableLombok()
                .enableChainModel()
                .naming(NamingStrategy.underline_to_camel)//数据表映射实体命名策略：默认下划线转驼峰underline_to_camel
                .columnNaming(NamingStrategy.underline_to_camel)//表字段映射实体属性命名规则：默认null，不指定按照naming执行
                .idType(IdType.AUTO)//添加全局主键类型
                .addTableFills(new Column("create_id", FieldFill.INSERT))
                .addTableFills(new Column("create_name", FieldFill.INSERT))
                .addTableFills(new Column("create_date", FieldFill.INSERT))
                .addTableFills(new Column("modify_id", FieldFill.INSERT_UPDATE))
                .addTableFills(new Column("modify_name", FieldFill.INSERT_UPDATE))
                .addTableFills(new Column("modify_date", FieldFill.INSERT_UPDATE))
                .addTableFills(new Column("del_flag", FieldFill.INSERT))
                .addTableFills(new Column("version", FieldFill.INSERT))
                .logicDeleteColumnName("del_flag")
                .versionColumnName("version")
                .formatFileName("%s")//格式化实体名称，%s取消首字母I,
                .mapperBuilder()
//                .enableMapperAnnotation()//开启mapper注解
                .enableBaseResultMap()//启用xml文件中的BaseResultMap 生成
                .enableBaseColumnList()//启用xml文件中的BaseColumnList
                .formatMapperFileName("%sMapper")//格式化Dao类名称
                .formatXmlFileName("%sMapper")//格式化xml文件名称
                .serviceBuilder()
                .formatServiceFileName("%sService")//格式化 service 接口文件名称
                .formatServiceImplFileName("%sServiceImpl")//格式化 service 接口文件名称
                .controllerBuilder()
                .enableRestStyle()
                .build();

        // 自定义文件生成
        Map<String, Object> customMap = new HashMap<>();
        customMap.put("BasePackagePath", parent);
        customMap.put("DtoPath", parent + "." + moduleName + ".dto");
        customMap.put("VoPath", parent + "." + moduleName + ".vo");
        customMap.put("QueryPath", parent + "." + moduleName + ".query");
        InjectionConfig injectionConfig = new InjectionConfig.Builder()
                .customMap(customMap)
                .customFile(new CustomFile.Builder().filePath(filePath).fileName("DTO.java").templatePath(templatePath + "dto.java.ftl").packageName("dto").build())
                .customFile(new CustomFile.Builder().filePath(filePath).fileName("VO.java").templatePath(templatePath + "vo.java.ftl").packageName("vo").build())
                .customFile(new CustomFile.Builder().filePath(filePath).fileName("Query.java").templatePath(templatePath + "query.java.ftl").packageName("query").build())
                .build();

        // 包配置
        PackageConfig packages = new PackageConfig.Builder()
                .parent(parent)//父包名。
                .moduleName(moduleName)
//                .other("output")//输出自定义文件时的包名
                .build();

        //  模板位置
        TemplateConfig template = new TemplateConfig.Builder()
                .entity(templatePath + "entity.java")//entity模板路径
                .service(templatePath + "service.java")//service模板路径
                .serviceImpl(templatePath + "serviceImpl.java")//实现类模板路径
                .mapper(templatePath + "mapper.java")//mapper模板路径
                .xml(templatePath + "mapper.xml")//xml文件模板路路径
                .controller(templatePath + "controller.java")//controller层模板路径
                .build();

        // 全局配置
        GlobalConfig global = new GlobalConfig.Builder()
                .outputDir(filePath)//生成的输出路径
                .author(author)//生成的作者名字
                .enableSwagger() // 开启swagger，需要添加swagger依赖并配置
                .dateType(DateType.TIME_PACK)//时间策略
                .commentDate("yyyy-MM-dd")//格式化时间格式
                .disableOpenDir()// 禁止打开输出目录，默认false
                .build();

        // 先配置数据源
        MySqlQuery mySqlQuery = new MySqlQuery() {
            @Override
            public String[] fieldCustom() {
                return new String[]{"Default"};
            }
        };

        DataSourceConfig dsc = new DataSourceConfig.Builder(sqlConnectionURL, sqlUsername, sqlPassword)
                .dbQuery(mySqlQuery)
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();

        new AutoGenerator(dsc)
                .global(global)
                .template(template)
                .injection(injectionConfig)
                .packageInfo(packages)
                .strategy(strategyConfig)
                .execute(new FreemarkerTemplateEngine());
    }
}