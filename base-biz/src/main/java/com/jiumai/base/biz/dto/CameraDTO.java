package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * pm_camera拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("pm_camera拓展类")
public class CameraDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("监控名称")
    private String cameraName;

    @ApiModelProperty("监控状态 1.在线 0.不在线")
    private Integer cameraStatus;

    @ApiModelProperty("监控状态名称 在线 不在线")
    private String cameraStatusName;

    @ApiModelProperty("监控地址")
    private String cameraInstallPlace;

    @ApiModelProperty("监控编码")
    private String cameraIndexCode;

    @ApiModelProperty("纬度(WGS84坐标系)")
    private String cameraLatitude;

    @ApiModelProperty("经度(WGS84坐标系)")
    private String cameraLongitude;

    @ApiModelProperty("监控所属区域code")
    private String cameraRegionCode;

    @ApiModelProperty("创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty("修改日期")
    private LocalDateTime updateTime;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
