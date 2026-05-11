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
 * pm_camera
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_camera")
@ApiModel("pm_camera")
public class Camera implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("监控名称")
    @TableField("camera_name")
    private String cameraName;

    @ApiModelProperty("监控状态 1.在线 0.不在线")
    @TableField("camera_status")
    private Integer cameraStatus;

    @ApiModelProperty("监控状态名称 在线 不在线")
    @TableField("camera_status_name")
    private String cameraStatusName;

    @ApiModelProperty("监控地址")
    @TableField("camera_install_place")
    private String cameraInstallPlace;

    @ApiModelProperty("监控编码")
    @TableField("camera_index_code")
    private String cameraIndexCode;

    @ApiModelProperty("纬度(WGS84坐标系)")
    @TableField("camera_latitude")
    private String cameraLatitude;

    @ApiModelProperty("经度(WGS84坐标系)")
    @TableField("camera_longitude")
    private String cameraLongitude;

    @ApiModelProperty("监控所属区域code")
    @TableField("camera_region_code")
    private String cameraRegionCode;

    @ApiModelProperty("创建日期")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("修改日期")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除1：未删除 -1：已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("乐观锁版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
