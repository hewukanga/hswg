package com.jiumai.base;

import com.jiumai.base.common.core.utils.SpringUtils;
import com.jiumai.base.listener.EnumListener;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;

/**
 * jar包的启动入口
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
@EnableScheduling
@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class},scanBasePackages = "com.jiumai.base")
@MapperScan({"com.jiumai.base.*.mapper","com.jiumai.base.*.*.mapper"})
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setWebApplicationType(WebApplicationType.SERVLET);
		// .setWebEnvironment(true);
		// 初始化IOC容器
		ApplicationContext ctx = app.run(args);
		SpringUtils.setCtx(ctx);
		// 初始化基础参数
		initBaseParam();
		log.info("===============================启动成功！========================================");

	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation("");
		return factory.createMultipartConfig();
	}

	/**
	 * 初始化基本参数
	 */
	public static void initBaseParam() {
		EnumListener bean = SpringUtils.getBean(EnumListener.class);
		bean.init();
	}

}
