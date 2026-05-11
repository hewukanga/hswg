/*
 * @(#)FileInfo.java 2015-5-30下午3:11:30
 * Copyright (c)  2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.common.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件信息
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
@Data
public class FileInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName; // 文件名

    private String path; // 物理路径

    private String url; // 文件下载URL

    private long size; // 文件大小


}
