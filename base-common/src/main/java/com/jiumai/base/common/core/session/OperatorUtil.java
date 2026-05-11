/*
 * @(#)UserUtil.java 2016年9月7日上午10:21:21
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.common.core.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.service.CurrentOperatorService;
import com.jiumai.base.common.core.utils.HttpUtils;
import com.jiumai.base.common.core.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 系统管理员操作工具类
 *
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */

public class OperatorUtil {

	private static SysLog loger = SysLogFactory.getLogger(OperatorUtil.class);

	public static final String REQUEST_OPID = "REQUEST_OPID";

	public static final String REQUEST_OP_NAME = "REQUEST_OP_NAME";

	/**
	 * 获取登陆管理员信息
	 *
	 * @param request
	 * @return
	 */
	public static LoginOperator getOperator(HttpServletRequest request) {
		// Long opId = getOperatorId(request);
		CurrentOperatorService opService = SpringUtils.getCurrentOperatorService();
		String token = HttpUtils.getToken(request);
		if(StringUtils.isBlank(token)){
			return null;
		}
		try {
			LoginOperator opdto = opService.getCurentOperatorByToken(token);
			return opdto;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取登陆管理员信息
	 *
	 * @param request
	 * @return
	 */
	public static Long getOperatorId(HttpServletRequest request) {
		Long opId = (Long) request.getAttribute(REQUEST_OPID);
		if (opId == null || opId.longValue() <= 0) {
            final String token = HttpUtils.getToken(request);
            try {
			opId = SpringUtils.getCurrentOperatorService().getOpIdFromToken(token);
            }catch(Exception ex){
            	loger.error("获取当前操作员ID失败：", ex);
            	return null;
            }
		}
		return opId;

	}

	/**
	 * 删除登陆管理员信息
	 *
	 * @param request
	 * @return sessionId
	 */
	public static void deleteOperator(HttpServletRequest request) {

		String token = HttpUtils.getToken(request);

		CurrentOperatorService currentOperatorService = SpringUtils.getCurrentOperatorService();

		currentOperatorService.destroyToken(token);

		HttpSession session = request.getSession();
		session.invalidate();
	}

}
