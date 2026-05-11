package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 续费账单
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_renewal_bill")
@ApiModel("续费账单")
public class RenewalBill implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("平台交易单号")
    @TableField("trade_no")
    private String tradeNo;

    @ApiModelProperty("车牌号")
    @TableField("license_plate_number")
    private String licensePlateNumber;

    @ApiModelProperty("停车场code(数泊)")
    @TableField("parking_lot_code")
    private String parkingLotCode;

    @ApiModelProperty("包月规则code")
    @TableField("pay_monthly_rule_code")
    private String payMonthlyRuleCode;

    @ApiModelProperty("包月规则名称")
    @TableField("pay_monthly_rule_name")
    private String payMonthlyRuleName;

    @ApiModelProperty("包月规则收费金额")
    @TableField("pay_monthly_rule_amount")
    private BigDecimal payMonthlyRuleAmount;

    @ApiModelProperty("包月时长（月）")
    @TableField("pay_monthly_duration")
    private Integer payMonthlyDuration;

    @ApiModelProperty("月卡有效期开始时间")
    @TableField("monthly_card_validity_start_date")
    private LocalDate monthlyCardValidityStartDate;

    @ApiModelProperty("月卡有效期结束时间")
    @TableField("monthly_card_validity_end_date")
    private LocalDate monthlyCardValidityEndDate;

    @ApiModelProperty("实际金额")
    @TableField("actual_amount")
    private BigDecimal actualAmount;

    @ApiModelProperty("开票状态")
    @TableField("invoicing_status")
    private Boolean invoicingStatus;

    @ApiModelProperty("收据单号")
    @TableField("receipt_no")
    private String receiptNo;

    @ApiModelProperty("收据图片")
    @TableField("receipt_image")
    private String receiptImage;

    @ApiModelProperty("缴费状态 1.待缴费 2.已缴费")
    @TableField("renewal_bill_status")
    private Integer renewalBillStatus;

    @ApiModelProperty("数泊续费单号")
    @TableField("third_renewal_bill_order_no")
    private String thirdRenewalBillOrderNo;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    @TableField("create_phone")
    private String createPhone;

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
