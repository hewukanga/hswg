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
 * 积分配置表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_integral_config")
@ApiModel("积分配置表")
public class IntegralConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("类型1.消费获取积分 2.任务获取积分 3.积分抵扣")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("子类型1.总体规则 2.来源规则 3.日常任务 4.基础任务 5.抵扣规则")
    @TableField("sub_type")
    private Integer subType;

    @ApiModelProperty("事项类型1.物业缴费 2.停车缴费 3.每日签到 4.室内报修评价 5.公共报修评价 6.参与社区活动 7.参与志愿者活动 8.提出建议并被采纳  9.物业费抵扣")
    @TableField("item_type")
    private Integer itemType;

    @ApiModelProperty("计算类型 1.非比例 2.比例")
    @TableField("calculation")
    private Integer calculation;

    @ApiModelProperty("积分数")
    @TableField("integral_sum")
    private Integer integralSum;

    @ApiModelProperty("金额")
    @TableField("amount")
    private Integer amount;

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
