package com.jiumai.base.biz.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiumai.base.common.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "活动状态", description = "NOT_SIGN_UP:未开始报名，SIGN_UP:报名中，SIGN_UP_END:报名结束，FINISHED:已结束，OFFLINE:已下架")
public enum ActivityStatusEnum implements BaseEnum {
    /**
     * 未开始报名
     */
    NOT_SIGN_UP(1, "未开始报名"),
    /**
     * 报名中
     */
    SIGN_UP(2, "报名中"),
    /**
     * 报名结束
     */
    SIGN_UP_END(3, "报名结束"),
    /**
     * 已结束
     */
    FINISHED(4, "已结束"),
    /**
     * 已下架
     */
    OFFLINE(5, "已下架");

    @ApiModelProperty(value = "状态编码")
    private Integer code;

    @ApiModelProperty(value = "状态名称")
    private String name;

    @JsonCreator
    public static ActivityStatusEnum jsonInit(String value) {
        for (ActivityStatusEnum item : values()) {
            if (item.toString().equals(value)) {
                return item;
            }
        }
        return null;
    }

    ActivityStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "状态值")
    @Override
    public String getValue() {
        return this.name();
    }
}
