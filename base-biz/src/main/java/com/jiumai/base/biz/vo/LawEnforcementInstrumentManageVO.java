package com.jiumai.base.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 执法仪管理视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("执法仪管理视图类")
public class LawEnforcementInstrumentManageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("执法仪code")
    private String lawEnforcementInstrumentCode;

    @ApiModelProperty("负责人id")
    private Long opId;

    @ApiModelProperty("负责人姓名")
    private String opName;

    @ApiModelProperty("服务范围")
    private String orgName;

    @ApiModelProperty("服务范围id")
    private Long orgId;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("岗位")
    private String roleName;

    @ApiModelProperty("岗位id")
    private Long roleId;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;

    @ApiModelProperty("执法仪编码")
    private String lawEnforcementInstrumentIndexCode;
}
