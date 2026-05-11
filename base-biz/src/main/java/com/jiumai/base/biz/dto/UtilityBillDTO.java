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
 * 物业账单拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("物业账单拓展类")
public class UtilityBillDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("房产id")
    private Long housePropertyId;

    @ApiModelProperty("缴费年度")
    private Integer paymentYear;

    @ApiModelProperty("社区id")
    private Long communityId;

    @ApiModelProperty("小区id")
    private Long residentialQuartersId;

    @ApiModelProperty("小区名称")
    private String residentialQuartersName;

    @ApiModelProperty("小区path")
    private String residentialQuartersPath;

    @ApiModelProperty("单元")
    private String unit;

    @ApiModelProperty("幢")
    private String building;

    @ApiModelProperty("门牌号")
    private String houseNumber;

    @ApiModelProperty("单价")
    private BigDecimal unitPrice;

    @ApiModelProperty("产权面积")
    private BigDecimal propertyArea;

    @ApiModelProperty("应缴费价格")
    private BigDecimal payablePrice;

    @ApiModelProperty("实缴费价格")
    private BigDecimal actualPaymentPrice;

    @ApiModelProperty("缴费状态1.待缴费 2.缴费中 3.已缴费")
    private Integer paymentStatus;

    @ApiModelProperty("缴费来源1.线上 2.线下")
    private Integer paymentSource;

    @ApiModelProperty("缴费时间")
    private LocalDateTime paymentDate;

    @ApiModelProperty("缴费人")
    private String paymentUser;

    @ApiModelProperty("缴费人id")
    private Long paymentUserId;

    @ApiModelProperty("缴费人手机号")
    private String paymentUserPhone;

    @ApiModelProperty("收款方式1.公司账户 2.支付宝3.微信4.现金")
    private Integer paymentMethod;

    @ApiModelProperty("开票状态1.未开票2.已开票3.申请中")
    private Integer invoiceStatus;

    @ApiModelProperty("开票单据号")
    private String invoiceNo;

    @ApiModelProperty("开票附件")
    private String invoiceFile;

    @ApiModelProperty("平台交易单号")
    private String tradeNo;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
