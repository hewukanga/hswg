package com.jiumai.base.biz.dto;

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
 * 物业区域表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("物业区域表拓展类")
public class PropertyAreaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("父级id")
    private Long parentId;

    @ApiModelProperty("区域名称")
    private String areaName;

    @ApiModelProperty("片区区域名称")
    private String zoneAreaName;

    @ApiModelProperty("区域code（街道）")
    private String areaCode;

    @ApiModelProperty("区域路径")
    private String areaPath;

    @ApiModelProperty("区域级别")
    private Integer areaLevel;

    @ApiModelProperty("单价")
    private BigDecimal unitPrice;

    @ApiModelProperty("装修押金")
    private BigDecimal decorationDeposit;

    @ApiModelProperty("服务开始时间")
    private LocalTime serviceStartTime;

    @ApiModelProperty("服务结束时间")
    private LocalTime serviceEndTime;

    @ApiModelProperty("服务热线")
    private String serviceHotline;

    @ApiModelProperty("建立日期")
    private LocalDate buildDate;

    @ApiModelProperty("管辖面积")
    private Double jurisdictionalArea;

    @ApiModelProperty("保洁面积")
    private Double cleaningArea;

    @ApiModelProperty("保绿面积")
    private Double greenKeepingArea;

    @ApiModelProperty("区域图片")
    private String areaImages;

    @ApiModelProperty("大屏标识")
    private Boolean screenFlag;
}
