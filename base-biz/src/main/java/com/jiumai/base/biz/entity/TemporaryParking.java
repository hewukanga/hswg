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
 * 临停车(数泊推送)
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_temporary_parking")
@ApiModel("临停车(数泊推送)")
public class TemporaryParking implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("车牌号")
    @TableField("license_plate_number")
    private String licensePlateNumber;

    @ApiModelProperty("进出场id")
    @TableField("parking_id")
    private String parkingId;

    @ApiModelProperty("停车支付id")
    @TableField("pay_parking_id")
    private String payParkingId;

    @ApiModelProperty("停车场code(数泊)")
    @TableField("parking_lot_code")
    private String parkingLotCode;

    @ApiModelProperty("停车场名称")
    @TableField("parking_lot_name")
    private String parkingLotName;

    @ApiModelProperty("驶入时间")
    @TableField("entry_time")
    private LocalDateTime entryTime;

    @ApiModelProperty("驶出时间")
    @TableField("depart_time")
    private LocalDateTime departTime;

    @ApiModelProperty("缴费金额")
    @TableField("payment_amount")
    private BigDecimal paymentAmount;

    @ApiModelProperty("缴费时间")
    @TableField("payment_time")
    private LocalDateTime paymentTime;

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

    @TableField("a")
    private String a;
}
