package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 房产管理拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("房产管理拓展类")
public class HousePropertyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("街道名称")
    private String streetName;

    @ApiModelProperty("街道id")
    private Long streetId;

    @ApiModelProperty("社区名称")
    private String communityName;

    @ApiModelProperty("社区id")
    private Long communityId;

    @ApiModelProperty("网格名称")
    private String gridName;

    @ApiModelProperty("网格id")
    private Long gridId;

    @ApiModelProperty("小区名称")
    private String residentialQuartersName;

    @ApiModelProperty("小区id")
    private Long residentialQuartersId;

    @ApiModelProperty("单元")
    private String unit;

    @ApiModelProperty("幢")
    private String building;

    @ApiModelProperty("门牌号")
    private String houseNumber;

    @ApiModelProperty("完整地址")
    private String completeAddress;

    @ApiModelProperty("产权面积")
    private BigDecimal propertyArea;

    @ApiModelProperty("单价")
    private BigDecimal unitPrice;

    @ApiModelProperty("装修押金")
    private BigDecimal decorationDeposit;

    @ApiModelProperty("房产所属小区path")
    private String areaPath;

    @ApiModelProperty("房产所属社区path")
    private String communityPath;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("存在未缴费物业单")
    private Integer haveUnpaidBill;

    @ApiModelProperty("户主姓名")
    private String householderName;

    @ApiModelProperty("户主手机号")
    private String householderPhone;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
