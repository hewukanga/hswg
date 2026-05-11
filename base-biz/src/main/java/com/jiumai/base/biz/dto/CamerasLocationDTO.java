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
 * 执法仪经纬度记录表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("执法仪经纬度记录表拓展类")
public class CamerasLocationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("纬度(wgs84)")
    private String latitude;

    @ApiModelProperty("经度(wgs84)")
    private String longitude;

    @ApiModelProperty("设备编号")
    private String cameraIndexCode;

    @ApiModelProperty("ip地址")
    private String ipAddress;

    @ApiModelProperty("端口号")
    private String portNo;

    @ApiModelProperty("接收时间")
    private LocalDateTime recvTime;
}
