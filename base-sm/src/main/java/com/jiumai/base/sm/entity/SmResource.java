package com.jiumai.base.sm.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.jiumai.base.common.core.enums.IsValidEnum;
import com.jiumai.base.sm.enums.ResourceTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 功能权限
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmResource对象", description="功能权限")
public class SmResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "功能ID")
    @TableId(value = "resource_id", type = IdType.AUTO)
    private Long resourceId;

    @ApiModelProperty(value = "父类ID")
    private Long parentId;

    @ApiModelProperty(value = "功能名称")
    private String resourceTitle;

    @ApiModelProperty(value = "功能类型：RESOURCE_SYS:系统权限，RESOURCE_DIR:功能目录，RESOURCE_MENU:功能菜单，RESOURCE_BTN:功能按钮，RESOURCE_ROUTER:路由数据")
    private ResourceTypeEnum resourceType;

    @ApiModelProperty(value = "功能编号")
    private String resourceCode;

    @ApiModelProperty(value = "功能路径")
    private String resourcePath;

    @ApiModelProperty(value = "菜单URL")
    private String menuUrl;

    @ApiModelProperty(value = "菜单图片")
    private String menuImage;

    @ApiModelProperty(value = "菜单排序")
    private Long menuSort;

    @ApiModelProperty(value = "创建人")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty(value = "修改人")
    private Long modifyId;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @ApiModelProperty(value = "过期时间")
    private Date expireDate;

    @ApiModelProperty(value = "状态：有效：VALID，无效：INVALID")
    private IsValidEnum status;


}
