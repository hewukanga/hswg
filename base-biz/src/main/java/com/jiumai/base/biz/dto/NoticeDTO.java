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
 * 公告表拓展类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("公告表拓展类")
public class NoticeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("公告标题")
    private String noticeTitle;

    @ApiModelProperty("公告来源")
    private String noticeSource;

    @ApiModelProperty("公告类型 1.社区动态 2.政策文件 3.物业公告")
    private Integer noticeType;

    @ApiModelProperty("公告封面图")
    private String noticeCoverPicture;

    @ApiModelProperty("公告内容")
    private String noticeContent;

    @ApiModelProperty("公告置顶状态1.未置顶 2.置顶")
    private Integer noticeTopStatus;

    @ApiModelProperty("公告状态1.待发布 2.已上架 3.已下架")
    private Integer noticeStatus;

    @ApiModelProperty("区域路径(多个逗号隔开)")
    private String areaPath;

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
