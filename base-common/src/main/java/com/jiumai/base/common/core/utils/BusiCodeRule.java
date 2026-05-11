package com.jiumai.base.common.core.utils;

import java.util.HashMap;
import java.util.Map;

public class BusiCodeRule implements java.io.Serializable {

	
	public static Map<String,BusiCodeRule> codes=new HashMap<String,BusiCodeRule>();
	static {
		codes.put("SUB_CODE",new BusiCodeRule("用户编号","SUB_CODE","SUB_${YYYYMMDD}_${SEQ}"));
		codes.put("ORD_CODE",new BusiCodeRule("订购编号","ORD_CODE","ORD_${YYYYMMDD}_${SEQ}"));
		codes.put("ACC_CODE",new BusiCodeRule("账户编号","ACC_CODE","ORD_${YYYYMMDD}_${SEQ}"));
	}
	
	public BusiCodeRule() {
		
	}
	
	public BusiCodeRule(String ruleName,String ruleCode,String ruleRegx) {
		this.ruleName=ruleName;
		this.ruleCode=ruleCode;
		this.ruleRegx=ruleRegx;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ruleName; //规则名称
	private String ruleCode; //规则编码，rediskey 全英文字符
	private String ruleRegx; //规则表达式如：${dept}_${yyyymm}_${seq} 其中seq自动生成
	private int minValue=1; //顺序号最少值
	private int maxValue; //顺序号最大数值
	private boolean needWad=true; //顺序号不足是否填充,位数取最大值的位数
	private char wadChar='0'; //填充字符 默认为0
	private boolean cycDay=true; //顺序号是否按天循环 默认为true
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public String getRuleRegx() {
		return ruleRegx;
	}
	public void setRuleRegx(String ruleRegx) {
		this.ruleRegx = ruleRegx;
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public boolean isNeedWad() {
		return needWad;
	}
	public void setNeedWad(boolean needWad) {
		this.needWad = needWad;
	}
	public char getWadChar() {
		return wadChar;
	}
	public void setWadChar(char wadChar) {
		this.wadChar = wadChar;
	}
	public boolean isCycDay() {
		return cycDay;
	}
	public void setCycDay(boolean cycDay) {
		this.cycDay = cycDay;
	}
	
	
	
}
