package com.jiumai.base.sm.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

import com.jiumai.base.common.core.enums.IsValidEnum;
import com.jiumai.base.common.core.enums.YesOrNoEnum;
import com.jiumai.base.sm.enums.CodeStatusEnum;
import com.jiumai.base.sm.enums.IdentityTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 操作员
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmOperator对象", description="操作员")
public class SmOperator implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "操作员ID")
    @TableId(value = "op_id", type = IdType.AUTO)
    private Long opId;

    @ApiModelProperty(value = "账号登陆名")
    private String opLoginName;

    @ApiModelProperty(value = "外部账号")
    private String outId;

    @ApiModelProperty(value = "账号登陆密码")
    private String password;

    @ApiModelProperty(value = "密码盐值")
    private String salt;

    @ApiModelProperty(value = "账号归属组织")
    private Long orgId;

    @ApiModelProperty(value = "账号状态：VALID:有效，INVALID:无效，LOCKED：锁定")
    private CodeStatusEnum status;

    @ApiModelProperty(value = "上次同步时间")
    private Date lastSyncDate;

    @ApiModelProperty(value = "是否为系统管理人员：YES：是，NO：不是")
    private YesOrNoEnum isAdmin;

    @ApiModelProperty(value = "重置密码时间")
    private Date resetPwdDate;

    @ApiModelProperty(value = "姓名")
    private String opName;

    @ApiModelProperty(value = "证件类型：ID_CARD：身份证")
    private IdentityTypeEnum identityType;

    @ApiModelProperty(value = "证件号")
    private String identityNo;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "手机")
    private String mobilePhone;

    @ApiModelProperty(value = "照片")
    private String photo;

    @ApiModelProperty(value = "备注")
    private String memo;

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
