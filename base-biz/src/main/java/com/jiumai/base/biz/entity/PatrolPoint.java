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
 * 巡查点位表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_patrol_point")
@ApiModel("巡查点位表")
public class PatrolPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("点位名称")
    @TableField("point_name")
    private String pointName;

    @ApiModelProperty("点位描述")
    @TableField("point_description")
    private String pointDescription;

    @ApiModelProperty("点位类型")
    @TableField("point_type")
    private Integer pointType;

    @ApiModelProperty("点位地址")
    @TableField("point_address")
    private String pointAddress;

    @ApiModelProperty("点位地址经度")
    @TableField("point_longitude")
    private Double pointLongitude;

    @ApiModelProperty("点位地址纬度")
    @TableField("point_latitude")
    private Double pointLatitude;

    @ApiModelProperty("点位状态")
    @TableField("point_status")
    private Boolean pointStatus;

    @ApiModelProperty("点位所属区域id")
    @TableField("point_area_id")
    private Long pointAreaId;

    @ApiModelProperty("点位所属区域path")
    @TableField("point_area_path")
    private String pointAreaPath;

    @ApiModelProperty("点位所属区域名称")
    @TableField("point_area_name")
    private String pointAreaName;

    @ApiModelProperty("点位二维码")
    @TableField("point_qr_code")
    private String pointQrCode;

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
