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
 * 公告表
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pm_notice")
@ApiModel("公告表")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("公告标题")
    @TableField("notice_title")
    private String noticeTitle;

    @ApiModelProperty("公告来源")
    @TableField("notice_source")
    private String noticeSource;

    @ApiModelProperty("公告类型 1.社区动态 2.政策文件 3.物业公告")
    @TableField("notice_type")
    private Integer noticeType;

    @ApiModelProperty("公告封面图")
    @TableField("notice_cover_picture")
    private String noticeCoverPicture;

    @ApiModelProperty("公告内容")
    @TableField("notice_content")
    private String noticeContent;

    @ApiModelProperty("公告置顶状态1.未置顶 2.置顶")
    @TableField("notice_top_status")
    private Integer noticeTopStatus;

    @ApiModelProperty("公告状态1.待发布 2.已上架 3.已下架")
    @TableField("notice_status")
    private Integer noticeStatus;

    @ApiModelProperty("区域路径(多个逗号隔开)")
    @TableField("area_path")
    private String areaPath;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty("创建日期")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

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
}
