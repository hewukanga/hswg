package com.jiumai.base.common.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

/**
 * @ Author     ：cz
 * @ Date       ：Created in 2022-03-1612:41
 * @ Description：
 * @ Modified By：
 */
@Slf4j
@Component("mybatisLogInterceptor")
//@Profile({"dev", "test"})
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
public class MybatisLogInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        Long costTime = end - start;
        PreparedStatement statement = (PreparedStatement) invocation.getArgs()[0];
        if (Proxy.isProxyClass(statement.getClass())) {
            InvocationHandler preparedStatementLogger = Proxy.getInvocationHandler(statement);
            if (preparedStatementLogger.getClass().getName().endsWith(".PreparedStatementLogger")) {
                Field field = preparedStatementLogger.getClass().getDeclaredField("statement");
                field.setAccessible(true);
                PreparedStatement clientPreparedStatement = null;
                PreparedStatement tempPreparedStatement = (PreparedStatement) field.get(preparedStatementLogger);

                if (tempPreparedStatement.getClass().getName().endsWith(".DruidPooledPreparedStatement")) {
                    field = tempPreparedStatement.getClass().getDeclaredField("stmt");
                    field.setAccessible(true);
                    tempPreparedStatement = (PreparedStatement) field.get(tempPreparedStatement);

                    if (tempPreparedStatement.getClass().getName().endsWith(".ClientPreparedStatement")) {
                        clientPreparedStatement = tempPreparedStatement;
                    } else if (tempPreparedStatement.getClass().getName().endsWith(".PreparedStatementProxyImpl")) {
                        field = tempPreparedStatement.getClass().getDeclaredField("statement");
                        field.setAccessible(true);
                        tempPreparedStatement = (PreparedStatement) field.get(tempPreparedStatement);
                        if (tempPreparedStatement.getClass().getName().endsWith(".ClientPreparedStatement")) {
                            clientPreparedStatement = tempPreparedStatement;
                        }
                    }
                } else {
                    clientPreparedStatement = tempPreparedStatement;
                }
                if (clientPreparedStatement != null) {
                    String sql = clientPreparedStatement.toString().replaceAll("[\\s\n ]+", " ")
                            .replaceFirst("HikariProxy(.*)ClientPreparedStatement:", "");

                    log.info("SQL：[{}]，time：[{}ms]", sql, costTime);

                }
            }
        }else{
            if(statement!= null){
                String sql = statement.toString().replaceAll("[\\s\n ]+", " ")
                        .replaceFirst("HikariProxy(.*)ClientPreparedStatement:", "");

                log.info("SQL：[{}]，time：[{}ms]", sql, costTime);
            }
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}