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
 * 物业账单
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_utility_bill")
@ApiModel("物业账单")
public class UtilityBill implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("房产id")
    @TableField("house_property_id")
    private Long housePropertyId;

    @ApiModelProperty("缴费年度")
    @TableField("payment_year")
    private Integer paymentYear;

    @ApiModelProperty("社区id")
    @TableField("community_id")
    private Long communityId;

    @ApiModelProperty("小区id")
    @TableField("residential_quarters_id")
    private Long residentialQuartersId;

    @ApiModelProperty("小区名称")
    @TableField("residential_quarters_name")
    private String residentialQuartersName;

    @ApiModelProperty("小区path")
    @TableField("residential_quarters_path")
    private String residentialQuartersPath;

    @ApiModelProperty("单元")
    @TableField("unit")
    private String unit;

    @ApiModelProperty("幢")
    @TableField("building")
    private String building;

    @ApiModelProperty("门牌号")
    @TableField("house_number")
    private String houseNumber;

    @ApiModelProperty("单价")
    @TableField("unit_price")
    private BigDecimal unitPrice;

    @ApiModelProperty("产权面积")
    @TableField("property_area")
    private BigDecimal propertyArea;

    @ApiModelProperty("应缴费价格")
    @TableField("payable_price")
    private BigDecimal payablePrice;

    @ApiModelProperty("实缴费价格")
    @TableField("actual_payment_price")
    private BigDecimal actualPaymentPrice;

    @ApiModelProperty("缴费状态1.待缴费 2.缴费中 3.已缴费")
    @TableField("payment_status")
    private Integer paymentStatus;

    @ApiModelProperty("缴费来源1.线上 2.线下")
    @TableField("payment_source")
    private Integer paymentSource;

    @ApiModelProperty("缴费时间")
    @TableField("payment_date")
    private LocalDateTime paymentDate;

    @ApiModelProperty("缴费人")
    @TableField("payment_user")
    private String paymentUser;

    @ApiModelProperty("缴费人id")
    @TableField("payment_user_id")
    private Long paymentUserId;

    @ApiModelProperty("缴费人手机号")
    @TableField("payment_user_phone")
    private String paymentUserPhone;

    @ApiModelProperty("收款方式1.公司账户 2.支付宝3.微信4.现金")
    @TableField("payment_method")
    private Integer paymentMethod;

    @ApiModelProperty("开票状态1.未开票2.已开票3.申请中")
    @TableField("invoice_status")
    private Integer invoiceStatus;

    @ApiModelProperty("开票单据号")
    @TableField("invoice_no")
    private String invoiceNo;

    @ApiModelProperty("开票附件")
    @TableField("invoice_file")
    private String invoiceFile;

    @ApiModelProperty("平台交易单号")
    @TableField("trade_no")
    private String tradeNo;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
