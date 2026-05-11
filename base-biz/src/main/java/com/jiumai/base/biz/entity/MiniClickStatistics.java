package com.jiumai.base.biz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 小程序点击统计表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_mini_click_statistics")
@ApiModel("小程序点击统计表")
public class MiniClickStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("片区id")
    @TableField("street_id")
    private Long streetId;

    @ApiModelProperty("片区名称")
    @TableField("street_name")
    private String streetName;

    @ApiModelProperty("统计日期")
    @TableField("statistics_date")
    private LocalDate statisticsDate;

    @ApiModelProperty("今日访问量")
    @TableField("today_visits")
    private Integer todayVisits;

    @ApiModelProperty("今日点击量")
    @TableField("today_hits")
    private Integer todayHits;

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
