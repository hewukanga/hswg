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
 * 交易流水表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("交易流水表视图类")
public class GeneralTransactionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("业务类型1.装修报备 2.物业账单 3.停车包月 4.停车离场缴费")
    private Integer businessType;

    @ApiModelProperty("订单编号")
    private String orderNo;

    @ApiModelProperty("交易单编号")
    private String tradeNo;

    @ApiModelProperty("第三方交易单编号")
    private String thirdTradeNo;

    @ApiModelProperty("支付方式")
    private Integer paymentMethod;

    @ApiModelProperty("支付状态")
    private Integer paymentState;

    @ApiModelProperty("总价格")
    private BigDecimal totalAmount;

    @ApiModelProperty("优惠价格")
    private BigDecimal discountAmount;

    @ApiModelProperty("实际价格")
    private BigDecimal actualAmount;

    @ApiModelProperty("交易单标题")
    private String title;

    @ApiModelProperty("交易单描述")
    private String body;

    @ApiModelProperty("支付用户信息")
    private String payUserInfo;

    @ApiModelProperty("服务器端后台notify通知地址")
    private String notifyUrl;

    @ApiModelProperty("前端回调地址")
    private String returnUrl;

    @ApiModelProperty("创建人名称")
    private String createName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人名称")
    private String modifyName;

    @ApiModelProperty("修改时间")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
