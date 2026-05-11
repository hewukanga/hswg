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
 * 新积分记录表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_new_integral_record")
@ApiModel("新积分记录表")
public class NewIntegralRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("记录类型(1.居民 2.商户 3.拱墅市政)")
    @TableField("integral_record_type")
    private Integer integralRecordType;

    @ApiModelProperty("积分类型(1.消耗 2.获取)")
    @TableField("integral_type")
    private Integer integralType;

    @ApiModelProperty("细分类型(岗位获取、绑定户号获取、物业缴费获取、停车缴费获取、户内转增、物业缴费抵扣、停车缴费抵扣、二维码交易、验证码交易、现金兑换、物业缴费兑换、停车缴费兑换、岗位奖励发放、停车缴费发放、物业缴费发放、绑定户号发放、后台积分抵扣、后台积分兑换、积分退回)")
    @TableField("subdivision_type")
    private Integer subdivisionType;

    @ApiModelProperty("积分来源")
    @TableField("integral_source")
    private String integralSource;

    @ApiModelProperty("消耗对象")
    @TableField("consumption_object")
    private String consumptionObject;

    @ApiModelProperty("积分数量")
    @TableField("integral_sum")
    private Integer integralSum;

    @ApiModelProperty("积分余额")
    @TableField("integral_balance")
    private Integer integralBalance;

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

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;

    @ApiModelProperty("商户id")
    @TableField("operator_id")
    private Long operatorId;

    @ApiModelProperty("来源id")
    @TableField("source_id")
    private Long sourceId;
}
