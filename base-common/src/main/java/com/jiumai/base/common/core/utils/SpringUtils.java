package com.jiumai.base.common.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.service.CurrentOpConfig;
import com.jiumai.base.common.core.service.CurrentOperatorService;

/**
 * Spring IOC工具类
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public class SpringUtils {
	private static SysLog loger = SysLogFactory.getLogger(SpringUtils.class);

    private static ApplicationContext ctx;

    public static void setCtx(ApplicationContext ctx) {
        SpringUtils.ctx = ctx;
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        try {
            T t = ctx.getBean(name, requiredType);
            return t;
        } catch (BeansException ex) {
        	ex.printStackTrace();
            return null;
        }
    }

    public static Object getBean(String name) throws BeansException {
        return ctx.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return ctx.getBean(requiredType);
    }
    
    public static CurrentOperatorService getCurrentOperatorService() {
    	CurrentOpConfig config = ctx.getBean(CurrentOpConfig.class);
        String beanName = "DbCurrentOperator";
        if(config!=null) {
     	   beanName =  config.getCurrentOperatorServiceName();
        }else {
           loger.error("无法获取获取当前操作员的Service配置，请在application.propertis 中添加 spring.current.currentOperatorServiceName");
        }
        Object bean = SpringUtils.getBean(beanName);
        if(bean == null) {
           loger.error("无法获取获取当前操作员的Service服务类,对应的Bean名字为："+ config.getCurrentOperatorServiceName());
     	   return null;
        }
        CurrentOperatorService service = (CurrentOperatorService)bean;
        return service;
    }
}
