package com.jiumai.base.common.core.service;

import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.dto.TokenDTO;

import java.util.List;

public interface CurrentOperatorService {

	/**
	 * 刷新token有效期
	 * @param token
	 */
	@Deprecated
	void refToken(String token);
	
	/**
	 * 获取当前操作员
	 * @param opId
	 * @return
	 * @throws Exception
	 */
	LoginOperator getCurentOperatorByToken(String token) throws Exception;
	
	/**
	 * 登录时生产Token
	 * @param op
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	String genToken(LoginOperator op) throws Exception;

	/**
	 * 登录时生产Token(新的方式)
	 */
	TokenDTO genTokenByNewWay(LoginOperator op);
	/**
	 * 修改用户信息时需要修改缓存
	 */
	void updateRedisToken(String token,LoginOperator op);
	
	/**
	 * 根据toke获取操作员ID
	 * @param token
	 * @return
	 * @throws Exception
	 */
	Long getOpIdFromToken(String token) throws Exception;
	
	/**
	 * 根据操作员ID获取token
	 * @param opId
	 * @return
	 */
	public String getTokenFromOpId(Long opId);
	
	/**
	 * 注销token
	 * @param token
	 * @throws Exception
	 */
	void destroyToken(String token) ;

	/**
	 * 刷新token(新的方式)
	 */
	TokenDTO refreshTokenByNewWay(String refreshToken);

	/**
	 * 取出token对应的角色id
	 * @param token
	 * @return
	 */
	List<Object> getRoleIdsByToken(String token);

	/**
	 * 取出token对应的资源id
	 * @param token
	 * @return
	 */
	List<Object> getResourceIdsByToken(String token);
}
