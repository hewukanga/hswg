# 研发注意

## 一、项目初始化

1. 修改包名、maven名称、项目名称

    > 把base相关的统一替换为业务名称，如'.base.' 替换为 '.xxx.'  'base-'  替换为 'xxx-'  '>base<' 替换为 '>xxx<'
    
2. 修改数据库、禁止使用base库

3. 业务相关代码写到新建的工程中，禁止写到xxx-sm中，如xxx-biz，xxx-app

## 二、项目打包
1. 项目引入瘦身打包，提升jar包上传速度（依赖外置）
    + 1.1 启动命令：java -jar -Dloader.path=./lib xxxx.jar 

    + 1.2 runner的pom文件：
    
        >   不 需要 lib 文件时：
        ``` maven-dependency-plugin 打开 <skip>true</skip> ```
    
        > 新增加模块时：
        ``` spring-boot-maven-plugin 中对应增加include选项 ```
