package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * <p>
 * 物业区域表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_property_area")
@ApiModel("物业区域表")
public class PropertyArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父级id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("区域名称")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty("片区区域名称")
    @TableField("zone_area_name")
    private String zoneAreaName;

    @ApiModelProperty("区域code（街道）")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty("区域路径")
    @TableField("area_path")
    private String areaPath;

    @ApiModelProperty("区域级别")
    @TableField("area_level")
    private Integer areaLevel;

    @ApiModelProperty("单价")
    @TableField("unit_price")
    private BigDecimal unitPrice;

    @ApiModelProperty("装修押金")
    @TableField("decoration_deposit")
    private BigDecimal decorationDeposit;

    @ApiModelProperty("服务开始时间")
    @TableField("service_start_time")
    private LocalTime serviceStartTime;

    @ApiModelProperty("服务结束时间")
    @TableField("service_end_time")
    private LocalTime serviceEndTime;

    @ApiModelProperty("服务热线")
    @TableField("service_hotline")
    private String serviceHotline;

    @ApiModelProperty("建立日期")
    @TableField("build_date")
    private LocalDate buildDate;

    @ApiModelProperty("管辖面积")
    @TableField("jurisdictional_area")
    private Double jurisdictionalArea;

    @ApiModelProperty("保洁面积")
    @TableField("cleaning_area")
    private Double cleaningArea;

    @ApiModelProperty("保绿面积")
    @TableField("green_keeping_area")
    private Double greenKeepingArea;

    @ApiModelProperty("区域图片")
    @TableField("area_images")
    private String areaImages;

    @ApiModelProperty("大屏标识")
    @TableField("screen_flag")
    private Boolean screenFlag;
}
