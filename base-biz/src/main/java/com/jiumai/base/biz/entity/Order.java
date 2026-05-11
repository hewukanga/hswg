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
 * 订单表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_order")
@ApiModel("订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单标题")
    @TableField("order_title")
    private String orderTitle;

    @ApiModelProperty("平台交易单号")
    @TableField("trade_no")
    private String tradeNo;

    @ApiModelProperty("订单类型1.装修报备 2.物业账单 3.停车包月 4.停车离场缴费 5.工单缴费")
    @TableField("order_type")
    private Integer orderType;

    @ApiModelProperty("订单状态1.待支付 2.已支付 3.已超时 4.已取消")
    @TableField("order_state")
    private Integer orderState;

    @ApiModelProperty("订单总金额")
    @TableField("order_total_amount")
    private BigDecimal orderTotalAmount;

    @ApiModelProperty("订单优惠金额")
    @TableField("order_discount_amount")
    private BigDecimal orderDiscountAmount;

    @ApiModelProperty("订单实际金额")
    @TableField("order_actual_amount")
    private BigDecimal orderActualAmount;

    @ApiModelProperty("订单扣除积分")
    @TableField("order_deduction_integral")
    private Integer orderDeductionIntegral;

    @ApiModelProperty("订单扣除积分记录id")
    @TableField("deduction_integral_record_id")
    private Long deductionIntegralRecordId;

    @ApiModelProperty("订单下单时间")
    @TableField("order_date_time")
    private LocalDateTime orderDateTime;

    @ApiModelProperty("订单支付时间")
    @TableField("order_pay_date")
    private LocalDateTime orderPayDate;

    @ApiModelProperty("订单取消原因")
    @TableField("order_cancel_reason")
    private String orderCancelReason;

    @ApiModelProperty("订单取消时间")
    @TableField("order_cancel_date")
    private LocalDateTime orderCancelDate;

    @ApiModelProperty("查看状态(默认false)")
    @TableField("view_status")
    private Boolean viewStatus;

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
