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
 * 保洁情况
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_cleaning_situation")
@ApiModel("保洁情况")
public class CleaningSituation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("保洁区域id")
    @TableField("cleaning_area_id")
    private Long cleaningAreaId;

    @ApiModelProperty("保洁区域名称")
    @TableField("cleaning_area_name")
    private String cleaningAreaName;

    @ApiModelProperty("保洁类型 1.街巷保洁 2.楼栋保洁(字典CLEANING_TYPE)")
    @TableField("cleaning_type")
    private Integer cleaningType;

    @ApiModelProperty("所属片区名称")
    @TableField("street_name")
    private String streetName;

    @ApiModelProperty("所属片区path")
    @TableField("street_path")
    private String streetPath;

    @ApiModelProperty("所属社区名称")
    @TableField("community_name")
    private String communityName;

    @ApiModelProperty("所属社区path")
    @TableField("community_path")
    private String communityPath;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;
}
