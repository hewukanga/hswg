package com.jiumai.base.biz.dto;

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
 * 装修报备表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("装修报备表拓展类")
public class DecorationReportDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("施工单位")
    private String constructionUnit;

    @ApiModelProperty("施工负责人")
    private String constructionPrincipal;

    @ApiModelProperty("施工负责人联系方式")
    private String constructionPrincipalPhone;

    @ApiModelProperty("房屋地址(小区+幢+单元+户号)")
    private String houseAddress;

    @ApiModelProperty("房屋详细地址(幢+单元+户号)")
    private String houseDetailsAddress;

    @ApiModelProperty("小区path(用于筛选数据)")
    private String areaPath;

    @ApiModelProperty("装修开始时间")
    private LocalDate renovationStartDate;

    @ApiModelProperty("装修结束时间")
    private LocalDate renovationEndDate;

    @ApiModelProperty("装修押金")
    private BigDecimal renovationDeposit;

    @ApiModelProperty("状态1.待缴纳押金 2.已缴纳押金 3,退押金审核中 4.押金退还中 5.已完成")
    private Integer status;

    @ApiModelProperty("付款凭证号平台交易单号")
    private String tradeNo;

    @ApiModelProperty("付款时间")
    private LocalDateTime payDate;

    @ApiModelProperty("收据编号")
    private String receiptNo;

    @ApiModelProperty("收据附件")
    private String receiptFiles;

    @ApiModelProperty("业主签名图片")
    private String signImage;

    @ApiModelProperty("同意押金退还时间")
    private LocalDateTime agreeRefundDate;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    private String createPhone;

    @ApiModelProperty("创建人地址")
    private String createAddress;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;

    @ApiModelProperty("承诺书附件地址")
    private String promiseFiles;

    @ApiModelProperty("承诺书签名图片")
    private String promiseImage;
}
