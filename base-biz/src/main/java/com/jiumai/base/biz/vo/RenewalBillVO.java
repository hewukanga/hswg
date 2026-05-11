package com.jiumai.base.biz.vo;

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
 * 续费账单视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("续费账单视图类")
public class RenewalBillVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("平台交易单号")
    private String tradeNo;

    @ApiModelProperty("车牌号")
    private String licensePlateNumber;

    @ApiModelProperty("停车场code(数泊)")
    private String parkingLotCode;

    @ApiModelProperty("包月规则code")
    private String payMonthlyRuleCode;

    @ApiModelProperty("包月规则名称")
    private String payMonthlyRuleName;

    @ApiModelProperty("包月规则收费金额")
    private BigDecimal payMonthlyRuleAmount;

    @ApiModelProperty("包月时长（月）")
    private Integer payMonthlyDuration;

    @ApiModelProperty("月卡有效期开始时间")
    private LocalDate monthlyCardValidityStartDate;

    @ApiModelProperty("月卡有效期结束时间")
    private LocalDate monthlyCardValidityEndDate;

    @ApiModelProperty("实际金额")
    private BigDecimal actualAmount;

    @ApiModelProperty("开票状态")
    private Boolean invoicingStatus;

    @ApiModelProperty("收据单号")
    private String receiptNo;

    @ApiModelProperty("收据图片")
    private String receiptImage;

    @ApiModelProperty("缴费状态 1.待缴费 2.已缴费")
    private Integer renewalBillStatus;

    @ApiModelProperty("数泊续费单号")
    private String thirdRenewalBillOrderNo;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    private String createPhone;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
