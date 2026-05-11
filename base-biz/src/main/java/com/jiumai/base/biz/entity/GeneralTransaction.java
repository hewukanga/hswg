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
 * 交易流水表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_general_transaction")
@ApiModel("交易流水表")
public class GeneralTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("业务类型1.装修报备 2.物业账单 3.停车包月 4.停车离场缴费")
    @TableField("business_type")
    private Integer businessType;

    @ApiModelProperty("订单编号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("交易单编号")
    @TableField("trade_no")
    private String tradeNo;

    @ApiModelProperty("第三方交易单编号")
    @TableField("third_trade_no")
    private String thirdTradeNo;

    @ApiModelProperty("支付方式")
    @TableField("payment_method")
    private Integer paymentMethod;

    @ApiModelProperty("支付状态")
    @TableField("payment_state")
    private Integer paymentState;

    @ApiModelProperty("总价格")
    @TableField("total_amount")
    private BigDecimal totalAmount;

    @ApiModelProperty("优惠价格")
    @TableField("discount_amount")
    private BigDecimal discountAmount;

    @ApiModelProperty("实际价格")
    @TableField("actual_amount")
    private BigDecimal actualAmount;

    @ApiModelProperty("交易单标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("交易单描述")
    @TableField("body")
    private String body;

    @ApiModelProperty("支付用户信息")
    @TableField("pay_user_info")
    private String payUserInfo;

    @ApiModelProperty("服务器端后台notify通知地址")
    @TableField("notify_url")
    private String notifyUrl;

    @ApiModelProperty("前端回调地址")
    @TableField("return_url")
    private String returnUrl;

    @ApiModelProperty("创建人ID")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人名称")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("修改人ID")
    @TableField(value = "modify_id", fill = FieldFill.INSERT_UPDATE)
    private Long modifyId;

    @ApiModelProperty("修改人名称")
    @TableField(value = "modify_name", fill = FieldFill.INSERT_UPDATE)
    private String modifyName;

    @ApiModelProperty("修改时间")
    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty("通用状态")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
