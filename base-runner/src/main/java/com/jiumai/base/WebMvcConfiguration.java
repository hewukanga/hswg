package com.jiumai.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jiumai.base.common.JsonDateSerializer;
import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.utils.FileUtils;
import com.jiumai.base.common.interceptor.CrossInterceptor;
import com.jiumai.base.common.interceptor.Knife4jInterceptor;
import com.jiumai.base.common.interceptor.OptionsInterceptor;
import com.jiumai.base.common.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;
import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	SysLog log = SysLogFactory.getLogger(WebMvcConfiguration.class);

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new CrossInterceptor()).addPathPatterns("/**");
		log.info("跨域拦截器注册成功！");

		registry.addInterceptor(new OptionsInterceptor()).addPathPatterns("/**");
		log.info("Options请求拦截器注册成功！");

		registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/d-admin/**");
		log.info("Token请求拦截器注册成功！");

		registry.addInterceptor(new Knife4jInterceptor()).excludePathPatterns("/d-admin/**");
		log.info("Knife4j拦截器注册成功！");

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**").addResourceLocations("file:"+FileUtils.getFilePath());
	}


	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// converters.clear();
		for(HttpMessageConverter c : converters) {
			if(c instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jm = (MappingJackson2HttpMessageConverter)c;
				ObjectMapper obMapper =jm.getObjectMapper();
				SimpleModule module = new SimpleModule();
				module.addSerializer(Date.class, new JsonDateSerializer.Serializer());
				module.addDeserializer(Date.class, new JsonDateSerializer.Deserializer());
				obMapper.registerModule(module);
			}
		}
	}


}
