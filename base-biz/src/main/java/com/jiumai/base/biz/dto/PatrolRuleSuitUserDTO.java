package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 巡查单规则适用人员表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("巡查单规则适用人员表拓展类")
public class PatrolRuleSuitUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("巡查单规则id")
    private Long ruleId;

    @ApiModelProperty("人员id")
    private Long opId;

    @ApiModelProperty("人员姓名")
    private String opName;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
