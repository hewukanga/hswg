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
 * 居民意见表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("居民意见表视图类")
public class UserOpinionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("意见内容")
    private String content;

    @ApiModelProperty("图片")
    private String images;

    @ApiModelProperty("社区path")
    private String communityPath;

    @ApiModelProperty("类型 1.保洁（字典OPINION_TYPE）")
    private Integer type;

    @ApiModelProperty("采纳状态")
    private Boolean adoptState;

    @ApiModelProperty("采纳意见")
    private String adoptIdea;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    private String createPhone;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;
}
