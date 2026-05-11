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
import com.jiumai.base.common.core.enums.YesOrNoEnum;
import com.jiumai.base.sm.enums.OrgTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 组织
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmOrganization对象", description="组织")
public class SmOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "组织ID")
    @TableId(value = "org_id", type = IdType.AUTO)
    private Long orgId;

    @ApiModelProperty(value = "组织编号")
    private String orgCode;

    @ApiModelProperty(value = "组织类型：GROUP_COMPANY:集团公司，COMPANY:公司，CHILDREN_COMPANY:子公司，PART_COMPANY:分公司，DEPT:部门，GROUP:小组")
    private OrgTypeEnum orgType;

    @ApiModelProperty(value = "组织名称")
    private String orgName;

    @ApiModelProperty(value = "组织描述")
    private String orgDesc;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "父类ID")
    private Long parentId;

    @ApiModelProperty(value = "是否为数据权限控制组织：YES：是，NO：不是")
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

    @ApiModelProperty(value = "状态：有效：VALID，无效：INVALID")
    private IsValidEnum status;

}
