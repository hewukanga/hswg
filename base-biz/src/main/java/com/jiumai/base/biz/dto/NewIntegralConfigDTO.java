package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 新积分配置表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("新积分配置表拓展类")
public class NewIntegralConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("积分配置类型(1.积分比例 2.积分消耗限制 3.物业缴费赠送积分规则 4.停车缴费赠送积分规则 5.首次绑定户号获取积分规则)")
    private Integer integralConfigType;

    @ApiModelProperty("积分       /type=2 一次性交易积分")
    private Double integralValue;

    @ApiModelProperty("金额      /type=2 一次性交易积分二次确认")
    private Double amountValue;

    @ApiModelProperty("每日获取次数上限")
    private Integer obtainLimit;

    @ApiModelProperty("开启状态")
    private Boolean enableStatus;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
