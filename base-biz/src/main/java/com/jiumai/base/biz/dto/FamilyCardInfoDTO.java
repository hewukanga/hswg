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
 * 亲情卡数据拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("亲情卡数据拓展类")
public class FamilyCardInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("车牌号")
    private String licensePlateNumber;

    @ApiModelProperty("账户code")
    private String accountCode;

    @ApiModelProperty("账户类型 1.钱包账户 2.时长账户")
    private Integer accountType;

    @ApiModelProperty("停车场code(数泊)")
    private String parkingLotCode;

    @ApiModelProperty("停车场名称")
    private String parkingLotName;

    @ApiModelProperty("余额")
    private BigDecimal cardBalance;

    @ApiModelProperty("充值时间")
    private LocalDateTime rechargeTime;

    @ApiModelProperty("充值金额")
    private BigDecimal rechargeAmount;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
