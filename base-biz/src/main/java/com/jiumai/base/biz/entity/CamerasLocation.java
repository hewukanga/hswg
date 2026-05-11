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
 * 执法仪经纬度记录表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_cameras_location")
@ApiModel("执法仪经纬度记录表")
public class CamerasLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty("纬度(wgs84)")
    @TableField("latitude")
    private String latitude;

    @ApiModelProperty("经度(wgs84)")
    @TableField("longitude")
    private String longitude;

    @ApiModelProperty("设备编号")
    @TableField("camera_index_code")
    private String cameraIndexCode;

    @ApiModelProperty("ip地址")
    @TableField("ip_address")
    private String ipAddress;

    @ApiModelProperty("端口号")
    @TableField("port_no")
    private String portNo;

    @ApiModelProperty("接收时间")
    @TableField("recv_time")
    private LocalDateTime recvTime;
}
