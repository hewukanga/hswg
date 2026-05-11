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
 * 执法仪管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_law_enforcement_instrument_manage")
@ApiModel("执法仪管理")
public class LawEnforcementInstrumentManage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("执法仪code")
    @TableField("law_enforcement_instrument_code")
    private String lawEnforcementInstrumentCode;

    @ApiModelProperty("负责人id")
    @TableField("op_id")
    private Long opId;

    @ApiModelProperty("负责人姓名")
    @TableField("op_name")
    private String opName;

    @ApiModelProperty("服务范围")
    @TableField("org_name")
    private String orgName;

    @ApiModelProperty("服务范围id")
    @TableField("org_id")
    private Long orgId;

    @ApiModelProperty("联系方式")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("岗位")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("岗位id")
    @TableField("role_id")
    private Long roleId;

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

    @ApiModelProperty("执法仪编码")
    @TableField("law_enforcement_instrument_index_code")
    private String lawEnforcementInstrumentIndexCode;
}
