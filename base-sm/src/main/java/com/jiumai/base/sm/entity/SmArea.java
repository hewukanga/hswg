package com.jiumai.base.sm.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.jiumai.base.common.core.enums.IsValidEnum;
import com.jiumai.base.common.core.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 区域
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmArea对象", description="区域")
public class SmArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域ID")
    @TableId(value = "area_id", type = IdType.AUTO)
    private Long areaId;

    @ApiModelProperty(value = "区域编号")
    private String areaCode;

    @ApiModelProperty(value = "区域类型：集体公司，公司，子公司，分公司，部门，小组")
    private String areaType;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @ApiModelProperty(value = "状态：VALID有效，INVALID失效")
    private IsValidEnum status;

    @ApiModelProperty(value = "区域描述")
    private String areaDesc;

    @ApiModelProperty(value = "区域路径")
    private String areaPath;

    @ApiModelProperty(value = "全名")
    private String fullName;

    @ApiModelProperty(value = "父类ID")
    private Long parentId;

    @ApiModelProperty(value = "是否为数据权限控制区域:YES:是，NO:不是")
    private YesOrNoEnum isDataControl;

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
