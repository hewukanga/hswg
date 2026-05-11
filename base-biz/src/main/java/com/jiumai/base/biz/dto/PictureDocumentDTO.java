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
 * 文档管理表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("文档管理表拓展类")
public class PictureDocumentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("标题")
    private String pictureTitle;

    @ApiModelProperty("类型 1.保绿 2.会议 3.迎检")
    private Integer pictureType;

    @ApiModelProperty("子类型 1.修剪 2.浇水 3.打药 4.除草 5.其他")
    private Integer pictureSubType;

    @ApiModelProperty("所属街道id")
    private Long streetId;

    @ApiModelProperty("所属街道名称")
    private String streetName;

    @ApiModelProperty("描述")
    private String introduce;

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
