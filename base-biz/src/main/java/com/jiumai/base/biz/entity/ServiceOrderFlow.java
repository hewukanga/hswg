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
 * 服务工单处理流程表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_service_order_flow")
@ApiModel("服务工单处理流程表")
public class ServiceOrderFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("服务工单id")
    @TableField("service_order_id")
    private Long serviceOrderId;

    @ApiModelProperty("处理流程类型 1.提交 2.派发 3.签收 4.完成 5.关闭")
    @TableField("flow_type")
    private Integer flowType;

    @ApiModelProperty("反馈照片")
    @TableField("images")
    private String images;

    @ApiModelProperty("处理内容")
    @TableField("handle_content")
    private String handleContent;

    @ApiModelProperty("接收人手机号码")
    @TableField("receive_phone")
    private String receivePhone;

    @ApiModelProperty("接收人姓名")
    @TableField("receive_name")
    private String receiveName;

    @ApiModelProperty("耗时")
    @TableField("time_consuming")
    private String timeConsuming;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    @TableField("create_phone")
    private String createPhone;

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

    @ApiModelProperty("是否微信通知")
    @TableField("wx_notice")
    private Boolean wxNotice;
}
