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
import com.jiumai.base.sm.enums.RoleTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmRole对象", description="角色")
public class SmRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编号")
    private String roleCode;

    @ApiModelProperty(value = "角色描述")
    private String roleDesc;

    @ApiModelProperty(value = "角色类型:SYS_INNER:系统内置，USER_DEFINED:用户定义")
    private RoleTypeEnum roleType;

    @ApiModelProperty(value = "角色路径")
    private String rolePath;

    @ApiModelProperty(value = "父类ID")
    private Long parentId;

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
