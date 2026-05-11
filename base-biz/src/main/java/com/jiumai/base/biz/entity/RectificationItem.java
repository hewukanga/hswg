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
 * 整改项
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_rectification_item")
@ApiModel("整改项")
public class RectificationItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("整改单id")
    @TableField("rectification_form_id")
    private Long rectificationFormId;

    @ApiModelProperty("整改项类型 1.消防及安全 2.环境卫生 3.堆物及秩序管理 4.绿化养护 5.工作规范 6.设施设备维护")
    @TableField("rectification_item_type")
    private Integer rectificationItemType;

    @ApiModelProperty("整改项类型名称")
    @TableField("rectification_item_type_name")
    private String rectificationItemTypeName;

    @ApiModelProperty("整改项描述")
    @TableField("rectification_item_description")
    private String rectificationItemDescription;

    @ApiModelProperty("扣分分值")
    @TableField("deduct_score")
    private Double deductScore;

    @ApiModelProperty("状态 false：未处理 true：已处理")
    @TableField("rectification_item_status")
    private Boolean rectificationItemStatus;

    @ApiModelProperty("检查地址id")
    @TableField("area_id")
    private Long areaId;

    @ApiModelProperty("检查地址名称")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty("图片")
    @TableField("image")
    private String image;

    @ApiModelProperty("处理后图片")
    @TableField("handle_image")
    private String handleImage;

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
