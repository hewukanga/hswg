package com.jiumai.base.common.interceptor;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.dto.InterfaceResourceBO;
import com.jiumai.base.common.core.dto.InterfaceRoleBO;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.service.CurrentOperatorService;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class TokenInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		response.setCharacterEncoding("UTF-8");
		// 等到请求头信息authorization信息
		final String token = request.getHeader("token") ==null ? request.getParameter("token"): request.getHeader("token");
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return true;
		}
		StringBuffer a = request.getRequestURL();
		// TODO 放到配置文件
		if (request.getRequestURI().indexOf("auth") > 0
				||request.getRequestURI().indexOf("login") > 0
				||request.getRequestURI().indexOf("autoLogin") > 0
				||request.getRequestURI().indexOf("public") > 0
				||request.getRequestURI().indexOf("refresh") > 0
				||request.getRequestURI().indexOf("getSysInfo") > 0
			) {
			return true;
		}
		ResultDTO<String> result = new ResultDTO<>();
		result.set(ResultCodeEnum.ERROR_AUTH_TIME_OUT, "Token invalid!");
		String errJson = JSON.toJSONString(result);
		if (token == null) {
			try {
				response.getOutputStream().write(errJson.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return false;
		}
		
		try {
			CurrentOperatorService service = SpringUtils.getCurrentOperatorService();
			if (CommonFuntions.isEmptyObject(service)) {
				response.getOutputStream().write(errJson.getBytes(StandardCharsets.UTF_8));
				return false;
			}
			LoginOperator operator = service.getCurentOperatorByToken(token);
			if (CommonFuntions.isNotEmptyObject(operator)) {
				request.setAttribute(OperatorUtil.REQUEST_OPID, operator.getOpId());
				request.setAttribute(OperatorUtil.REQUEST_OP_NAME, operator.getOpName());
				// 减少token刷新次数 TODO？
//				service.refToken(token);
			}else {
				response.getOutputStream().write(errJson.getBytes(StandardCharsets.UTF_8));
				return false;
			}
			//超级管理员 不鉴权
			if(operator.getOpId() == 1){
				return true;
			}else {
				return checkInterfaceAuth(response, request.getRequestURI(), token, service);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			try {
				response.getOutputStream().write(errJson.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * 接口鉴权
	 * @param response
	 * @param uri
	 * @param token
	 * @param service
	 * @return
	 * @throws IOException
	 */
	private boolean checkInterfaceAuth(HttpServletResponse response, String uri, String token, CurrentOperatorService service) throws IOException {
		//接口管理不鉴权
		if (uri.indexOf("interface-conf") > 0){
			return true;
		}
		//取出token对应的角色id
		List<Object> roleIdInterfaceValues = service.getRoleIdsByToken(token);
		if (CommonFuntions.isNotEmptyObject(roleIdInterfaceValues)) {
			List<String> uris = roleIdInterfaceValues
					.stream()
					.map(r -> JSON.parseArray(r.toString(), InterfaceRoleBO.class))
					.flatMap(List::stream)
					.map(i -> i.getInterfaceUri())
					.collect(Collectors.toList());
			if (uris.contains(uri)) {
				return true;
			}
		}

		//取出token对应的资源id
		List<Object> resourceIdInterfaceValues = service.getResourceIdsByToken(token);
		if (CommonFuntions.isNotEmptyObject(resourceIdInterfaceValues)) {
			List<String> uris = resourceIdInterfaceValues.stream().map(r -> JSON.parseObject(r.toString(), InterfaceResourceBO.class)).map(i -> i.getInterfacePath()).collect(Collectors.toList());
			if (uris.contains(uri)) {
				return true;
			}
		}

		//校验不通过
		ResultDTO<String> result = new ResultDTO<>();
		result.set(ResultCodeEnum.ERROR_AUTH_TIME_OUT, "无权访问！！！");
		String errJson = JSON.toJSONString(result);
		response.getOutputStream().write(errJson.getBytes(StandardCharsets.UTF_8));
		return false;
	}
}