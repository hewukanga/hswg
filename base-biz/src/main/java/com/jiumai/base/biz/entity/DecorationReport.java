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
 * 装修报备表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_decoration_report")
@ApiModel("装修报备表")
public class DecorationReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("施工单位")
    @TableField("construction_unit")
    private String constructionUnit;

    @ApiModelProperty("施工负责人")
    @TableField("construction_principal")
    private String constructionPrincipal;

    @ApiModelProperty("施工负责人联系方式")
    @TableField("construction_principal_phone")
    private String constructionPrincipalPhone;

    @ApiModelProperty("房屋地址(小区+幢+单元+户号)")
    @TableField("house_address")
    private String houseAddress;

    @ApiModelProperty("房屋详细地址(幢+单元+户号)")
    @TableField("house_details_address")
    private String houseDetailsAddress;

    @ApiModelProperty("小区path(用于筛选数据)")
    @TableField("area_path")
    private String areaPath;

    @ApiModelProperty("装修开始时间")
    @TableField("renovation_start_date")
    private LocalDate renovationStartDate;

    @ApiModelProperty("装修结束时间")
    @TableField("renovation_end_date")
    private LocalDate renovationEndDate;

    @ApiModelProperty("装修押金")
    @TableField("renovation_deposit")
    private BigDecimal renovationDeposit;

    @ApiModelProperty("状态1.待缴纳押金 2.已缴纳押金 3,退押金审核中 4.押金退还中 5.已完成")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("付款凭证号平台交易单号")
    @TableField("trade_no")
    private String tradeNo;

    @ApiModelProperty("付款时间")
    @TableField("pay_date")
    private LocalDateTime payDate;

    @ApiModelProperty("收据编号")
    @TableField("receipt_no")
    private String receiptNo;

    @ApiModelProperty("收据附件")
    @TableField("receipt_files")
    private String receiptFiles;

    @ApiModelProperty("业主签名图片")
    @TableField("sign_image")
    private String signImage;

    @ApiModelProperty("同意押金退还时间")
    @TableField("agree_refund_date")
    private LocalDateTime agreeRefundDate;

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

    @ApiModelProperty("创建人地址")
    @TableField("create_address")
    private String createAddress;

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

    @ApiModelProperty("承诺书附件地址")
    @TableField("promise_files")
    private String promiseFiles;

    @ApiModelProperty("承诺书签名图片")
    @TableField("promise_image")
    private String promiseImage;
}
