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
 * 包月用户信息(数泊推送)拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("包月用户信息(数泊推送)拓展类")
public class PayMonthlyUserInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("合约id")
    private String contractId;

    @ApiModelProperty("车牌号")
    private String licensePlateNumber;

    @ApiModelProperty("停车场code(数泊)")
    private String parkingLotCode;

    @ApiModelProperty("停车场名称")
    private String parkingLotName;

    @ApiModelProperty("停车场地址")
    private String parkingLotAddress;

    @ApiModelProperty("服务电话")
    private String serviceTelephone;

    @ApiModelProperty("包月规则code")
    private String payMonthlyRuleCode;

    @ApiModelProperty("包月规则收费金额")
    private BigDecimal chargeAmount;

    @ApiModelProperty("月卡有效期开始时间")
    private LocalDate monthlyCardValidityStartDate;

    @ApiModelProperty("月卡有效期结束时间")
    private LocalDate monthlyCardValidityEndDate;

    @ApiModelProperty("月卡付款时间")
    private LocalDateTime monthlyPayDate;

    @ApiModelProperty("月卡支付金额")
    private BigDecimal monthlyPayAmount;

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
}
