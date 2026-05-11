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
 * 亲情卡数据
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_family_card_info")
@ApiModel("亲情卡数据")
public class FamilyCardInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("车牌号")
    @TableField("license_plate_number")
    private String licensePlateNumber;

    @ApiModelProperty("账户code")
    @TableField("account_code")
    private String accountCode;

    @ApiModelProperty("账户类型 1.钱包账户 2.时长账户")
    @TableField("account_type")
    private Integer accountType;

    @ApiModelProperty("停车场code(数泊)")
    @TableField("parking_lot_code")
    private String parkingLotCode;

    @ApiModelProperty("停车场名称")
    @TableField("parking_lot_name")
    private String parkingLotName;

    @ApiModelProperty("余额")
    @TableField("card_balance")
    private BigDecimal cardBalance;

    @ApiModelProperty("充值时间")
    @TableField("recharge_time")
    private LocalDateTime rechargeTime;

    @ApiModelProperty("充值金额")
    @TableField("recharge_amount")
    private BigDecimal rechargeAmount;

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
