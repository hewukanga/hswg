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
 * 新积分配置表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_new_integral_config")
@ApiModel("新积分配置表")
public class NewIntegralConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("积分配置类型(1.积分比例 2.积分消耗限制 3.物业缴费赠送积分规则 4.停车缴费赠送积分规则 5.首次绑定户号获取积分规则)")
    @TableField("integral_config_type")
    private Integer integralConfigType;

    @ApiModelProperty("积分       /type=2 一次性交易积分")
    @TableField("integral_value")
    private Double integralValue;

    @ApiModelProperty("金额      /type=2 一次性交易积分二次确认")
    @TableField("amount_value")
    private Double amountValue;

    @ApiModelProperty("每日获取次数上限")
    @TableField("obtain_limit")
    private Integer obtainLimit;

    @ApiModelProperty("开启状态")
    @TableField("enable_status")
    private Boolean enableStatus;

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
