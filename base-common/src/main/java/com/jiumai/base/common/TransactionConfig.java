package com.jiumai.base.common;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class TransactionConfig {

	@Bean("txSource")
    public TransactionAttributeSource transactionAttributeSource(){
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
         /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        //RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        //requiredTx.setRollbackRules(
        //    Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        //requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED,
            Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        // TODO 放到配置文件
        requiredTx.setTimeout(30);
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("create*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("execute*", requiredTx);
        txMap.put("batch*", requiredTx);
        txMap.put("template*", requiredTx);
        txMap.put("set*", requiredTx);
        txMap.put("init*", requiredTx);
        txMap.put("submit*", requiredTx);
        txMap.put("remove*", requiredTx);
        txMap.put("edit*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("mass*", requiredTx);
        txMap.put("handle*", requiredTx);
        txMap.put("exec*", requiredTx);
        txMap.put("import*", requiredTx);
        txMap.put("gen*", requiredTx);
        txMap.put("cancel*", requiredTx);
        txMap.put("refund*", requiredTx);
        txMap.put("wxPay*", requiredTx);
        txMap.put("test*", requiredTx);
        
        source.setNameMap( txMap );
 
        return source;
    }
 
    /**切面拦截规则 参数会自动从容器中注入*/
    @Bean
    public AspectJExpressionPointcutAdvisor pointcutAdvisor(TransactionInterceptor txInterceptor){
        AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        pointcutAdvisor.setAdvice(txInterceptor);
        pointcutAdvisor.setExpression("execution (* com.jiumai.base.*.service.*.*(..))");
        return pointcutAdvisor;
    }
 
    /*事务拦截器*/
    @Bean("txInterceptor")
    TransactionInterceptor getTransactionInterceptor(PlatformTransactionManager tx){
        return new TransactionInterceptor(tx , transactionAttributeSource()) ;
    }
    
    @Bean(name = "dataSource")
	public DataSource datasource(Environment env) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		ds.setIdleTimeout(env.getProperty("spring.datasource.hikari.idle-timeout",int.class));
		ds.setMaxLifetime(env.getProperty("spring.datasource.hikari.max-lifetime",int.class));
		ds.setConnectionTimeout(env.getProperty("spring.datasource.hikari.connection-timeout",int.class));
		ds.setMaximumPoolSize(env.getProperty("spring.datasource.hikari.maximum-pool-size",int.class));
		Properties p =new Properties();
		p.setProperty("remarks", "true");
		ds.setDataSourceProperties(p);
		return ds;
	}

    
}
