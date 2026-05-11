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
 * 临停车(数泊推送)拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("临停车(数泊推送)拓展类")
public class TemporaryParkingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("车牌号")
    private String licensePlateNumber;

    @ApiModelProperty("进出场id")
    private String parkingId;

    @ApiModelProperty("停车支付id")
    private String payParkingId;

    @ApiModelProperty("停车场code(数泊)")
    private String parkingLotCode;

    @ApiModelProperty("停车场名称")
    private String parkingLotName;

    @ApiModelProperty("驶入时间")
    private LocalDateTime entryTime;

    @ApiModelProperty("驶出时间")
    private LocalDateTime departTime;

    @ApiModelProperty("缴费金额")
    private BigDecimal paymentAmount;

    @ApiModelProperty("缴费时间")
    private LocalDateTime paymentTime;

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

    private String a;
}
