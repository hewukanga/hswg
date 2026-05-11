package com.jiumai.base.biz.vo;

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
 * 订单表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("订单表视图类")
public class OrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("订单标题")
    private String orderTitle;

    @ApiModelProperty("平台交易单号")
    private String tradeNo;

    @ApiModelProperty("订单类型1.装修报备 2.物业账单 3.停车包月 4.停车离场缴费 5.工单缴费")
    private Integer orderType;

    @ApiModelProperty("订单状态1.待支付 2.已支付 3.已超时 4.已取消")
    private Integer orderState;

    @ApiModelProperty("订单总金额")
    private BigDecimal orderTotalAmount;

    @ApiModelProperty("订单优惠金额")
    private BigDecimal orderDiscountAmount;

    @ApiModelProperty("订单实际金额")
    private BigDecimal orderActualAmount;

    @ApiModelProperty("订单扣除积分")
    private Integer orderDeductionIntegral;

    @ApiModelProperty("订单扣除积分记录id")
    private Long deductionIntegralRecordId;

    @ApiModelProperty("订单下单时间")
    private LocalDateTime orderDateTime;

    @ApiModelProperty("订单支付时间")
    private LocalDateTime orderPayDate;

    @ApiModelProperty("订单取消原因")
    private String orderCancelReason;

    @ApiModelProperty("订单取消时间")
    private LocalDateTime orderCancelDate;

    @ApiModelProperty("查看状态(默认false)")
    private Boolean viewStatus;

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
