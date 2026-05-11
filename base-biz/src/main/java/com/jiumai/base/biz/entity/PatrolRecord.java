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
 * 巡查记录表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_patrol_record")
@ApiModel("巡查记录表")
public class PatrolRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("巡查单id")
    @TableField("patrol_list_id")
    private Long patrolListId;

    @ApiModelProperty("巡查单名称")
    @TableField("patrol_list_name")
    private String patrolListName;

    @ApiModelProperty("点位所属区域id")
    @TableField("point_area_id")
    private Long pointAreaId;

    @ApiModelProperty("点位所属区域path")
    @TableField("point_area_path")
    private String pointAreaPath;

    @ApiModelProperty("点位所属区域名称")
    @TableField("point_area_name")
    private String pointAreaName;

    @ApiModelProperty("点位id")
    @TableField("point_id")
    private Long pointId;

    @ApiModelProperty("点位名称")
    @TableField("point_name")
    private String pointName;

    @ApiModelProperty("点位类型")
    @TableField("point_type")
    private Integer pointType;

    @ApiModelProperty("点位地址")
    @TableField("point_address")
    private String pointAddress;

    @ApiModelProperty("巡查时间")
    @TableField("patrol_date")
    private LocalDateTime patrolDate;

    @ApiModelProperty("巡查详情")
    @TableField("patrol_details")
    private String patrolDetails;

    @ApiModelProperty("巡查人姓名")
    @TableField("patrol_user_name")
    private String patrolUserName;

    @ApiModelProperty("巡查附件")
    @TableField("patrol_images")
    private String patrolImages;

    @ApiModelProperty("巡查情况 1:正常 2:异常 3.异常已处理")
    @TableField("patrol_situation")
    private Integer patrolSituation;

    @ApiModelProperty("巡查上报类型(字典PATROL_REPORT_TYPE)")
    @TableField("patrol_report_type")
    private Integer patrolReportType;

    @ApiModelProperty("处理后附件")
    @TableField("handle_after_images")
    private String handleAfterImages;

    @ApiModelProperty("处理详情")
    @TableField("handle_details")
    private String handleDetails;

    @ApiModelProperty("巡查来源1.二维码")
    @TableField("patrol_source")
    private Integer patrolSource;

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
