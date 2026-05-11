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
 * 帮扶
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_help")
@ApiModel("帮扶")
public class Help implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("类型1.养老 2.助残 3.扶困")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("子类型")
    @TableField("sub_type")
    private Integer subType;

    @ApiModelProperty("帮扶对象")
    @TableField("help_object")
    private String helpObject;

    @ApiModelProperty("帮扶时间")
    @TableField("help_date")
    private LocalDateTime helpDate;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("图片")
    @TableField("images")
    private String images;

    @ApiModelProperty("所属片区path")
    @TableField("street_path")
    private String streetPath;

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

    @ApiModelProperty("帮扶来源1.移动端 2.一键服务")
    @TableField("help_source")
    private Integer helpSource;
}
