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
 * 巡查记录表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("巡查记录表拓展类")
public class PatrolRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("巡查单id")
    private Long patrolListId;

    @ApiModelProperty("巡查单名称")
    private String patrolListName;

    @ApiModelProperty("点位所属区域id")
    private Long pointAreaId;

    @ApiModelProperty("点位所属区域path")
    private String pointAreaPath;

    @ApiModelProperty("点位所属区域名称")
    private String pointAreaName;

    @ApiModelProperty("点位id")
    private Long pointId;

    @ApiModelProperty("点位名称")
    private String pointName;

    @ApiModelProperty("点位类型")
    private Integer pointType;

    @ApiModelProperty("点位地址")
    private String pointAddress;

    @ApiModelProperty("巡查时间")
    private LocalDateTime patrolDate;

    @ApiModelProperty("巡查详情")
    private String patrolDetails;

    @ApiModelProperty("巡查人姓名")
    private String patrolUserName;

    @ApiModelProperty("巡查附件")
    private String patrolImages;

    @ApiModelProperty("巡查情况 1:正常 2:异常 3.异常已处理")
    private Integer patrolSituation;

    @ApiModelProperty("巡查上报类型(字典PATROL_REPORT_TYPE)")
    private Integer patrolReportType;

    @ApiModelProperty("处理后附件")
    private String handleAfterImages;

    @ApiModelProperty("处理详情")
    private String handleDetails;

    @ApiModelProperty("巡查来源1.二维码")
    private Integer patrolSource;

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
