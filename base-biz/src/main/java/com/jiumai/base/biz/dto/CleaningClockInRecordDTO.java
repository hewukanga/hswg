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
 * 保洁打卡记录拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("保洁打卡记录拓展类")
public class CleaningClockInRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("保洁区域id")
    private Long cleaningAreaId;

    @ApiModelProperty("打卡点位名称")
    private String clockInPointName;

    @ApiModelProperty("保洁打卡地址")
    private String address;

    @ApiModelProperty("保洁打卡地址经度")
    private Double longitude;

    @ApiModelProperty("保洁打卡地址纬度")
    private Double latitude;

    @ApiModelProperty("创建人组织path")
    private String createOrgPath;

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
