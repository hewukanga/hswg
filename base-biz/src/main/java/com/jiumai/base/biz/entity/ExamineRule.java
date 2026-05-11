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
 * 考核规则
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_examine_rule")
@ApiModel("考核规则")
public class ExamineRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("计算周期 1.近一月 2.近一年 3.近一周")
    @TableField("calculation_period")
    private Integer calculationPeriod;

    @ApiModelProperty("考核类型 1.考勤完成率 2.巡查完成率 3.保洁完成率 4.服务工单完成率 5.有效投诉件数 6.物业缴费率 7.帮扶次数")
    @TableField("examine_type")
    private Integer examineType;

    @ApiModelProperty("考核第一名分数")
    @TableField("examine_one_score")
    private Integer examineOneScore;

    @ApiModelProperty("考核第二名分数")
    @TableField("examine_two_score")
    private Integer examineTwoScore;

    @ApiModelProperty("考核第三名分数")
    @TableField("examine_three_score")
    private Integer examineThreeScore;

    @ApiModelProperty("考核第四名分数")
    @TableField("examine_four_score")
    private Integer examineFourScore;

    @ApiModelProperty("考核第五名分数")
    @TableField("examine_five_score")
    private Integer examineFiveScore;

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
}
