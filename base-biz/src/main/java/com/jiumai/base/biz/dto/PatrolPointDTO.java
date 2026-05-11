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
 * 巡查点位表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("巡查点位表拓展类")
public class PatrolPointDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("点位名称")
    private String pointName;

    @ApiModelProperty("点位描述")
    private String pointDescription;

    @ApiModelProperty("点位类型")
    private Integer pointType;

    @ApiModelProperty("点位地址")
    private String pointAddress;

    @ApiModelProperty("点位地址经度")
    private Double pointLongitude;

    @ApiModelProperty("点位地址纬度")
    private Double pointLatitude;

    @ApiModelProperty("点位状态")
    private Boolean pointStatus;

    @ApiModelProperty("点位所属区域id")
    private Long pointAreaId;

    @ApiModelProperty("点位所属区域path")
    private String pointAreaPath;

    @ApiModelProperty("点位所属区域名称")
    private String pointAreaName;

    @ApiModelProperty("点位二维码")
    private String pointQrCode;

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
