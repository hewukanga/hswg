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
 * 保洁区域管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_cleaning_area")
@ApiModel("保洁区域管理")
public class CleaningArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("保洁区域名称")
    @TableField("cleaning_area_name")
    private String cleaningAreaName;

    @ApiModelProperty("点位描述")
    @TableField("point_description")
    private String pointDescription;

    @ApiModelProperty("保洁类型 1.街巷保洁 2.楼栋保洁(字典CLEANING_TYPE)")
    @TableField("cleaning_type")
    private Integer cleaningType;

    @ApiModelProperty("保洁区域点位")
    @TableField("cleaning_area_point")
    private String cleaningAreaPoint;

    @ApiModelProperty("保洁区域中心经度")
    @TableField("cleaning_center_lng")
    private Double cleaningCenterLng;

    @ApiModelProperty("保洁区域中心纬度")
    @TableField("cleaning_center_lat")
    private Double cleaningCenterLat;

    @ApiModelProperty("保洁区域中心名称")
    @TableField("cleaning_center_name")
    private String cleaningCenterName;

    @ApiModelProperty("保洁人员数量")
    @TableField("cleaning_person_size")
    private Integer cleaningPersonSize;

    @ApiModelProperty("所属片区id")
    @TableField("street_id")
    private Long streetId;

    @ApiModelProperty("所属片区名称")
    @TableField("street_name")
    private String streetName;

    @ApiModelProperty("所属片区path")
    @TableField("street_path")
    private String streetPath;

    @ApiModelProperty("所属社区id")
    @TableField("community_id")
    private Long communityId;

    @ApiModelProperty("所属社区名称")
    @TableField("community_name")
    private String communityName;

    @ApiModelProperty("所属社区path")
    @TableField("community_path")
    private String communityPath;

    @ApiModelProperty("保洁班组负责人")
    @TableField("charge_name")
    private String chargeName;

    @ApiModelProperty("保洁班组负责人手机号")
    @TableField("charge_phone")
    private String chargePhone;

    @ApiModelProperty("保洁人员名称")
    @TableField("cleaner_name")
    private String cleanerName;

    @ApiModelProperty("有效期(用于判断展示绿色还是灰色)")
    @TableField("validity_date")
    private LocalDateTime validityDate;

    @ApiModelProperty("上次完成保洁时间")
    @TableField("last_cleaning_time")
    private LocalDateTime lastCleaningTime;

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
