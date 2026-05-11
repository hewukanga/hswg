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
 * 整改项视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("整改项视图类")
public class RectificationItemVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("整改单id")
    private Long rectificationFormId;

    @ApiModelProperty("整改项类型 1.消防及安全 2.环境卫生 3.堆物及秩序管理 4.绿化养护 5.工作规范 6.设施设备维护")
    private Integer rectificationItemType;

    @ApiModelProperty("整改项类型名称")
    private String rectificationItemTypeName;

    @ApiModelProperty("整改项描述")
    private String rectificationItemDescription;

    @ApiModelProperty("扣分分值")
    private Double deductScore;

    @ApiModelProperty("状态 false：未处理 true：已处理")
    private Boolean rectificationItemStatus;

    @ApiModelProperty("检查地址id")
    private Long areaId;

    @ApiModelProperty("检查地址名称")
    private String areaName;

    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("处理后图片")
    private String handleImage;

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
}
