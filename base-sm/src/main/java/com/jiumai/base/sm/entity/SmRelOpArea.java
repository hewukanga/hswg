package com.jiumai.base.sm.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

import com.jiumai.base.sm.enums.RelTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 操作员可访问区域
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmRelOpArea对象", description="操作员可访问区域")
public class SmRelOpArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关联ID")
    @TableId(value = "rel_id", type = IdType.AUTO)
    private Long relId;

    @ApiModelProperty(value = "操作员ID")
    private Long opId;

    @ApiModelProperty(value = "组织ID")
    private Long areaId;

    @ApiModelProperty(value = "关系类型")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private RelTypeEnum relType;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifyId;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty(value = "过期时间")
    private Date expireDate;


}
