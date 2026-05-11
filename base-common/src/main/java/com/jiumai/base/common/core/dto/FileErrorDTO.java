/*
 * @(#)FileErrorMsg.java 2015-6-18下午2:07:25
 * Copyright (c)  2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.common.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 导入错误信息返回实体
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
@Data
public class FileErrorDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int dataNum; // 错误行数
	private String errorType; // 错误类型
	private String errorMsg; // 错误信息
	private String errorData = "无"; //错误数据


}
