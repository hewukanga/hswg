package com.jiumai.base.common.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;

@Configuration
@ConfigurationProperties(prefix = "spring.cross")
public class CrossInterceptor implements HandlerInterceptor {
    private static List<String> allowHosts=new ArrayList<String>();
    
    SysLog log=SysLogFactory.getLogger(CrossInterceptor.class);
    //配置允许跨域的域名
    public void setAllow(String allow){
        if(allow != null){
            allowHosts = Arrays.asList(allow.split(","));
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String origin  = request.getHeader(HttpHeaders.ORIGIN);
        if (origin != null) {
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Origin", origin);
        		//跨域 Header
                response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,X-URL-PATH,Content-Type,token,X-Access-token,changeOrigin,XFILENAME,XFILECATEGORY,XFILESIZE");
        } else {
//             log.warn("跨域失败：" + origin);
        }
        return true;
    }
}