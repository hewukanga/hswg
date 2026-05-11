package com.jiumai.base.common.core.utils.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 邮件发送配置信息
 * @author CZ
 *
 */
@ConfigurationProperties(prefix = "mail")
public class MailConfig {
	
    private  String host; //邮件服务器地址
    private  String port;//邮件服务器端口
    private  String auth;//邮件是否权限验证
    private  String fromAcc;//邮件发送者邮箱地址
    private  String fromPwd;//邮件发送者邮箱密码
    private  String fromName;//邮件发送者名称
    private  String ccAccs;//邮件抄送账户
    private  Map<String, Object> emailMap = null;//发送邮件
    
    public  Map<String, Object> getEmailMap() {
    	emailMap = new HashMap<>();
    	emailMap.put(SendMailUtils.MAIL_SMTP_HOST, this.host);
		emailMap.put(SendMailUtils.MAIL_SMTP_PORT, this.port);
		emailMap.put(SendMailUtils.MAIL_SMTP_AUTH, this.auth);
		emailMap.put(SendMailUtils.MAIL_FROM_ACC, this.fromAcc);
		emailMap.put(SendMailUtils.MAIL_FROM_PWD,  this.fromPwd);
		emailMap.put(SendMailUtils.MAIL_FROM_NAME, this.fromName);
		emailMap.put(SendMailUtils.MAIL_CC_ACCS,   this.ccAccs);
		return emailMap;
	}


	/**
	 * 邮件服务器地址
	 */
	public  String getHost() {
		return host;
	}
	
	public  void setHost(String host) {
		this.host = host;
	}
	
	/**
     * 邮件服务器端口
     */
	public  String getPort() {
		return port;
	}
	
	public  void setPort(String port) {
		this.port = port;
	}
	
	/**
	 * 邮件是否权限验证
	 */
	public  String getAuth() {
		return auth;
	}
	
	public  void setAuth(String auth) {
		this.auth = auth;
	}
	
	/**
	 * 邮件发送者邮箱地址
	 */
	public  String getFromAcc() {
		return fromAcc;
	}
	
	public  void setFromAcc(String fromAcc) {
		this.fromAcc = fromAcc;
	}
	
	/**
	 * 邮件发送者邮箱密码
	 */
	public  String getFromPwd() {
		return fromPwd;
	}
	
	public  void setFromPwd(String fromPwd) {
		this.fromPwd = fromPwd;
	}
	
	/**
	 * 邮件发送者名称
	 */
	public  String getFromName() {
		return fromName;
	}
	
	public  void setFromName(String fromName) {
		this.fromName = fromName;
	}
	
	/**
	 * 邮件抄送账户
	 */
	public  String getCcAccs() {
		return ccAccs;
	}
	
	public  void setCcAccs(String ccAccs) {
		this.ccAccs = ccAccs;
	}
    
    
   
}
