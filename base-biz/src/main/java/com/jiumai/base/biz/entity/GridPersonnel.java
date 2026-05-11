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
 * 网格人员信息
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_grid_personnel")
@ApiModel("网格人员信息")
public class GridPersonnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("所属片区id")
    @TableField("street_id")
    private Long streetId;

    @ApiModelProperty("所属片区名称")
    @TableField("street_name")
    private String streetName;

    @ApiModelProperty("所属片区path")
    @TableField("street_path")
    private String streetPath;

    @ApiModelProperty("所属社区id")
    @TableField("community_id")
    private Long communityId;

    @ApiModelProperty("所属社区名称")
    @TableField("community_name")
    private String communityName;

    @ApiModelProperty("所属社区path")
    @TableField("community_path")
    private String communityPath;

    @ApiModelProperty("所属网格id")
    @TableField("grid_id")
    private Long gridId;

    @ApiModelProperty("所属网格名称")
    @TableField("grid_name")
    private String gridName;

    @ApiModelProperty("所属网格path")
    @TableField("grid_path")
    private String gridPath;

    @ApiModelProperty("姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty("职位(字典POSITION_TYPE) 1.网格长 2.网格员")
    @TableField("position_type")
    private Integer positionType;

    @ApiModelProperty("管辖范围")
    @TableField("jurisdiction")
    private String jurisdiction;

    @ApiModelProperty("是否为党员")
    @TableField("party_member_status")
    private Boolean partyMemberStatus;

    @ApiModelProperty("个人照片")
    @TableField("images")
    private String images;

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
