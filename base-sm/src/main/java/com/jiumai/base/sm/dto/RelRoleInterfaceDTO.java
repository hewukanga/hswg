package com.jiumai.base.sm.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* <p>
* 接口权限关联管理拓展类
* </p>
*
* @author mysqlGen
* @since 2023-08-21
*/
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("接口权限关联管理拓展类")
public class RelRoleInterfaceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("接口id")
    private Long interfaceId;

    @ApiModelProperty("接口路径")
    private String interfaceUri;

    @ApiModelProperty("接口名称")
    private String interfaceName;
}

