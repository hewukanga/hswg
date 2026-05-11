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

import com.jiumai.base.sm.enums.ResourceTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* <p>
* 接口管理拓展类
* </p>
*
* @author mysqlGen
* @since 2023-08-21
*/
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("接口管理拓展类")
public class InterfaceConfDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long interfaceId;

    @ApiModelProperty("接口名称")
    private String interfaceName;

    @ApiModelProperty("接口路径")
    private String interfaceUri;

    @ApiModelProperty("接口组名")
    private String interfaceGroupName;

    @ApiModelProperty("资源id")
    private Long resourceId;

    @ApiModelProperty("资源名称")
    private String resourceName;

    @ApiModelProperty(value = "资源类型：RESOURCE_SYS:系统权限，RESOURCE_DIR:功能目录，RESOURCE_MENU:功能菜单，RESOURCE_BTN:功能按钮，RESOURCE_ROUTER:路由数据")
    private String resourceType;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改时间")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;


}

