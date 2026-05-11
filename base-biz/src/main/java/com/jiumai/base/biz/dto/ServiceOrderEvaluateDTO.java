package com.jiumai.base.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 服务工单评价表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("服务工单评价表拓展类")
public class ServiceOrderEvaluateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("服务工单id")
    private Long serviceOrderId;

    @ApiModelProperty("工单类型1.垃圾回收 2.家政服务 3.报修")
    private Integer workOrderType;

    @ApiModelProperty("满意度")
    private String satisfaction;

    private String suggest;

    @ApiModelProperty("图片")
    private String images;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("社区path")
    private String communityPath;

    @ApiModelProperty("关联房产名称(英文逗号隔开)")
    private String relHousePropertyName;

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
}
