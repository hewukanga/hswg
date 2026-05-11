package com.jiumai.base.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 停车离场订单表(数泊)视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("停车离场订单表(数泊)视图类")
public class ParkingLeaveOrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("平台交易单号")
    private String tradeNo;

    @ApiModelProperty("进出场 ID")
    private String parkingId;

    @ApiModelProperty("场库编号")
    private String projectNo;

    @ApiModelProperty("场库名称")
    private String projectName;

    @ApiModelProperty("缴费订单号")
    private String orderNo;

    @ApiModelProperty("车主姓名")
    private String userName;

    @ApiModelProperty("停车时长(**小时**分钟)")
    private String parkingDuration;

    @ApiModelProperty("车牌号")
    private String licensePlateNumber;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;
}
