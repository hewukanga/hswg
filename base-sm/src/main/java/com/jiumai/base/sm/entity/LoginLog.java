package com.jiumai.base.sm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录日志
 * </p>
 *
 * @author mysqlGen
 * @since 2023-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sm_login_log")
@ApiModel(value = "LoginLog对象", description = "登录日志")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long opId;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "类型 1-登录  2-退出登录")
    private Integer type;

    @ApiModelProperty(value = "登录平台 1-PC后台 2-大屏 3-移动端 4-微信授权 5-浙里办 6-浙政钉 99-第三方免密")
    private Integer loginPlatform;


}
