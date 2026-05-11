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
 * 积分记录
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_integral_record")
@ApiModel("积分记录")
public class IntegralRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("1.物业缴费 2.停车缴费 3.每日签到 4.室内报修评价 5.公共报修评价 6.参与社区活动 7.参与志愿者活动 8.提出建议并被采纳  9.物业费抵扣 10.商品兑换")
    @TableField("integral_source")
    private Integer integralSource;

    @ApiModelProperty("类型 1.发放 2.扣除")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("房屋信息")
    @TableField("house_address")
    private String houseAddress;

    @ApiModelProperty("积分数")
    @TableField("integral_sum")
    private Integer integralSum;

    @ApiModelProperty("积分余额")
    @TableField("integral_balance")
    private Integer integralBalance;

    @ApiModelProperty("状态 true:成功 false:失败")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
