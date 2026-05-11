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
 * 物业设备表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("物业设备表拓展类")
public class PropertyDeviceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("设备编号")
    private String deviceNumber;

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("设备类型（字典DEVICE_TYPE）")
    private Integer deviceType;

    @ApiModelProperty("所属街道id")
    private Long streetId;

    @ApiModelProperty("所属街道名称")
    private String streetName;

    @ApiModelProperty("所属街道path")
    private String streetPath;

    @ApiModelProperty("关联人员ID")
    private Long opId;

    @ApiModelProperty("关联人员姓名")
    private String opName;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
