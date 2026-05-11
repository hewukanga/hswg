package com.jiumai.base.sm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.jiumai.base.sm.enums.EnumTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 枚举
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SmEnumConf对象", description="枚举")
public class SmEnumConf implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "枚举ID")
    @TableId(value = "enum_id", type = IdType.AUTO)
    private Long enumId;

    @ApiModelProperty(value = "枚举编号")
    private String enumCode;

    @ApiModelProperty(value = "枚举名称")
    private String enumName;

    @ApiModelProperty(value = "枚举值名称")
    private String entityName;

    @ApiModelProperty(value = "枚举值")
    private String entityValue;

    @ApiModelProperty(value = "关联枚举值")
    private String refEntityValue;

    @ApiModelProperty(value = "枚举类型 SYS:系统内置，USER:用户定义")
    private EnumTypeEnum enumType;


}
