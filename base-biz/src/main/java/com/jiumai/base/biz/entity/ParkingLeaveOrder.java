package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 停车离场订单表(数泊)
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_parking_leave_order")
@ApiModel("停车离场订单表(数泊)")
public class ParkingLeaveOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("平台交易单号")
    @TableField("trade_no")
    private String tradeNo;

    @ApiModelProperty("进出场 ID")
    @TableField("parking_id")
    private String parkingId;

    @ApiModelProperty("场库编号")
    @TableField("project_no")
    private String projectNo;

    @ApiModelProperty("场库名称")
    @TableField("project_name")
    private String projectName;

    @ApiModelProperty("缴费订单号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("车主姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("停车时长(**小时**分钟)")
    @TableField("parking_duration")
    private String parkingDuration;

    @ApiModelProperty("车牌号")
    @TableField("license_plate_number")
    private String licensePlateNumber;

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
}
