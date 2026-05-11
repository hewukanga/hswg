//package com.jiumai.base.common.interceptor;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import com.jiumai.base.common.core.dto.InterfaceResourceBO;
//import com.jiumai.base.common.core.dto.InterfaceRoleBO;
//import com.jiumai.base.common.core.dto.LoginOperator;
//import com.jiumai.base.common.core.dto.ResultDTO;
//import com.jiumai.base.common.core.enums.ResultCodeEnum;
//import com.jiumai.base.common.core.service.CurrentOperatorService;
//import com.jiumai.base.common.core.session.OperatorUtil;
//import com.jiumai.base.common.core.utils.CommonConstant;
//import com.jiumai.base.common.core.utils.CommonFuntions;
//import com.jiumai.base.common.core.utils.SpringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * 接口鉴权拦截器
// */
//@Slf4j
//@Configuration
//public class InterfaceAuthInterceptor implements HandlerInterceptor {
//
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        String uri = request.getRequestURI();
//
//        if (uri.indexOf("auth") > 0
//                || uri.indexOf("login") > 0
//                || uri.indexOf("autoLogin") > 0
//                || uri.indexOf("public") > 0
//                || uri.indexOf("refresh") > 0
//                || uri.indexOf("getSysInfo") > 0
//        ) {
//            return true;
//        }
//
//        // 等到请求头信息authorization信息
//        final String token = request.getHeader("token") == null ? request.getParameter("token") : request.getHeader("token");
//
//        CurrentOperatorService service = SpringUtils.getCurrentOperatorService();
//
//        //取出token对应的角色id
//        List<Object> roleIdInterfaceValues = service.getRoleIdsByToken(token);
//        if (CommonFuntions.isNotEmptyObject(roleIdInterfaceValues)) {
//            List<String> uris = roleIdInterfaceValues
//                    .stream()
//                    .map(r -> (List<InterfaceRoleBO>) r)
//                    .flatMap(List::stream)
//                    .map(i -> i.getInterfaceUri())
//                    .collect(Collectors.toList());
//            if (uris.contains(uri)) {
//                return true;
//            }
//        }
//
//        //取出token对应的资源id
//        List<Object> resourceIdInterfaceValues = service.getResourceIdsByToken(token);
//        if (CommonFuntions.isNotEmptyObject(resourceIdInterfaceValues)) {
//            List<String> uris = resourceIdInterfaceValues.stream().map(r -> (InterfaceResourceBO) r).map(i -> i.getInterfaceUri()).collect(Collectors.toList());
//            if (uris.contains(uri)) {
//                return true;
//            }
//        }
//
//        //校验不通过
//        ResultDTO<String> result = new ResultDTO<>();
//        result.set(ResultCodeEnum.ERROR_AUTH_TIME_OUT, "无权访问！！！");
//        String errJson = JSON.toJSONString(result);
//        response.getOutputStream().write(errJson.getBytes(StandardCharsets.UTF_8));
//        return false;
//    }
//}