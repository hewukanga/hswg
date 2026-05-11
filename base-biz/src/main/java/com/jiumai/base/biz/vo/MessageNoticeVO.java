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
 * 消息通知表视图类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("消息通知表视图类")
public class MessageNoticeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("通知标题")
    private String noticeTitle;

    @ApiModelProperty("发送对象1.居民端 2.员工端")
    private Integer sendObject;

    @ApiModelProperty("社区path(多个逗号隔开)")
    private String communityPath;

    @ApiModelProperty("社区名称(多个逗号隔开)")
    private String communityName;

    @ApiModelProperty("通知内容")
    private String noticeContent;

    @ApiModelProperty("创建人姓名")
    private String createName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("创建人手机号")
    private String createPhone;

    @ApiModelProperty("创建人所属社区path/绑定房屋所属社区path")
    private String createAreaPath;

    @ApiModelProperty("修改人姓名")
    private String modifyName;

    @ApiModelProperty("修改日期")
    private LocalDateTime modifyDate;

    @ApiModelProperty("乐观锁版本号")
    private Integer version;
}
