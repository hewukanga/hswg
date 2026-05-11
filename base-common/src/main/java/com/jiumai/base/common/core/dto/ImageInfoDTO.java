/*
 * @(#)ImageInfo.java 2015-3-17下午3:48:07
 * Copyright (c)  2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.common.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片信息
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
@Data
public class ImageInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String picName; // 图片原名字
    
    private String newName; // 新名字
    
    private String picPath; // 图片路径（物理路径）
    
    private String picUrl; // 图片URL（相对路径）
    
    private int height; // 图片高
    
    private int width; // 图片宽


}
