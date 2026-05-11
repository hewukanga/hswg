package com.jiumai.base.common.interceptor;

import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.utils.PropertiesUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class Knife4jInterceptor implements HandlerInterceptor {

    SysLog log = SysLogFactory.getLogger(Knife4jInterceptor.class);
    String active = PropertiesUtils.getProperty("spring.profiles.active");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("prod".equals(active)) {
            String uri = request.getRequestURI();
            if (
                    uri.contains("api-doc")
                            || uri.contains("doc.")
                            || uri.contains("swagger")
                            || uri.contains("webjars")
            ) {
                response.setCharacterEncoding("UTF-8");
                response.setStatus(404);
                return false;
            }
        }
        return true;
    }
}