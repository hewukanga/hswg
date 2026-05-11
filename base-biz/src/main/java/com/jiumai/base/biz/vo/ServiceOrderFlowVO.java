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
 * 服务工单处理流程表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("服务工单处理流程表视图类")
public class ServiceOrderFlowVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("服务工单id")
    private Long serviceOrderId;

    @ApiModelProperty("处理流程类型 1.提交 2.派发 3.签收 4.完成 5.关闭")
    private Integer flowType;

    @ApiModelProperty("反馈照片")
    private String images;

    @ApiModelProperty("处理内容")
    private String handleContent;

    @ApiModelProperty("接收人手机号码")
    private String receivePhone;

    @ApiModelProperty("接收人姓名")
    private String receiveName;

    @ApiModelProperty("耗时")
    private String timeConsuming;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    private String createPhone;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;

    @ApiModelProperty("是否微信通知")
    private Boolean wxNotice;
}
