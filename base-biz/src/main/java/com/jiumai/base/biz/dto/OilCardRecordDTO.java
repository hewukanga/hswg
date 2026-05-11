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
 * 油卡上报记录表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("油卡上报记录表拓展类")
public class OilCardRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("油卡id")
    private Long cardId;

    @ApiModelProperty("油卡号码")
    private String cardNo;

    @ApiModelProperty("车辆id")
    private Long carId;

    @ApiModelProperty("车牌号")
    private String carNumber;

    @ApiModelProperty("记录对应金额")
    private BigDecimal amount;

    @ApiModelProperty("记录类型（1：充值，2：加油，3：后台修改）")
    private Integer recordType;

    @ApiModelProperty("当前余额")
    private BigDecimal balance;

    @ApiModelProperty("记录对应时间")
    private LocalDateTime operateDate;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("照片")
    private String images;

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
