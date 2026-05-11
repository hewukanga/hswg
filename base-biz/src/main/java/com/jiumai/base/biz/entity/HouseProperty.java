package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * 房产管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_house_property")
@ApiModel("房产管理")
public class HouseProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("街道名称")
    @TableField("street_name")
    private String streetName;

    @ApiModelProperty("街道id")
    @TableField("street_id")
    private Long streetId;

    @ApiModelProperty("社区名称")
    @TableField("community_name")
    private String communityName;

    @ApiModelProperty("社区id")
    @TableField("community_id")
    private Long communityId;

    @ApiModelProperty("网格名称")
    @TableField("grid_name")
    private String gridName;

    @ApiModelProperty("网格id")
    @TableField("grid_id")
    private Long gridId;

    @ApiModelProperty("小区名称")
    @TableField("residential_quarters_name")
    private String residentialQuartersName;

    @ApiModelProperty("小区id")
    @TableField("residential_quarters_id")
    private Long residentialQuartersId;

    @ApiModelProperty("单元")
    @TableField("unit")
    private String unit;

    @ApiModelProperty("幢")
    @TableField("building")
    private String building;

    @ApiModelProperty("门牌号")
    @TableField("house_number")
    private String houseNumber;

    @ApiModelProperty("完整地址")
    @TableField("complete_address")
    private String completeAddress;

    @ApiModelProperty("产权面积")
    @TableField("property_area")
    private BigDecimal propertyArea;

    @ApiModelProperty("单价")
    @TableField("unit_price")
    private BigDecimal unitPrice;

    @ApiModelProperty("装修押金")
    @TableField("decoration_deposit")
    private BigDecimal decorationDeposit;

    @ApiModelProperty("房产所属小区path")
    @TableField("area_path")
    private String areaPath;

    @ApiModelProperty("房产所属社区path")
    @TableField("community_path")
    private String communityPath;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("存在未缴费物业单")
    @TableField("have_unpaid_bill")
    private Integer haveUnpaidBill;

    @ApiModelProperty("户主姓名")
    @TableField("householder_name")
    private String householderName;

    @ApiModelProperty("户主手机号")
    @TableField("householder_phone")
    private String householderPhone;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("修改人id")
    @TableField(value = "modify_id", fill = FieldFill.INSERT_UPDATE)
    private Long modifyId;

    @ApiModelProperty("修改人姓名")
    @TableField(value = "modify_name", fill = FieldFill.INSERT_UPDATE)
    private String modifyName;

    @ApiModelProperty("修改日期")
    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
