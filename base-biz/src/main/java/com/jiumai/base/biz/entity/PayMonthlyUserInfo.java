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
 * 包月用户信息(数泊推送)
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_pay_monthly_user_info")
@ApiModel("包月用户信息(数泊推送)")
public class PayMonthlyUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("合约id")
    @TableField("contract_id")
    private String contractId;

    @ApiModelProperty("车牌号")
    @TableField("license_plate_number")
    private String licensePlateNumber;

    @ApiModelProperty("停车场code(数泊)")
    @TableField("parking_lot_code")
    private String parkingLotCode;

    @ApiModelProperty("停车场名称")
    @TableField("parking_lot_name")
    private String parkingLotName;

    @ApiModelProperty("停车场地址")
    @TableField("parking_lot_address")
    private String parkingLotAddress;

    @ApiModelProperty("服务电话")
    @TableField("service_telephone")
    private String serviceTelephone;

    @ApiModelProperty("包月规则code")
    @TableField("pay_monthly_rule_code")
    private String payMonthlyRuleCode;

    @ApiModelProperty("包月规则收费金额")
    @TableField("charge_amount")
    private BigDecimal chargeAmount;

    @ApiModelProperty("月卡有效期开始时间")
    @TableField("monthly_card_validity_start_date")
    private LocalDate monthlyCardValidityStartDate;

    @ApiModelProperty("月卡有效期结束时间")
    @TableField("monthly_card_validity_end_date")
    private LocalDate monthlyCardValidityEndDate;

    @ApiModelProperty("月卡付款时间")
    @TableField("monthly_pay_date")
    private LocalDateTime monthlyPayDate;

    @ApiModelProperty("月卡支付金额")
    @TableField("monthly_pay_amount")
    private BigDecimal monthlyPayAmount;

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
