package com.jiumai.base.common.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务操作类型
 * 
 * @author ruoyi
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "业务操作类型" ,description="LOGIN:登录，LOGOUT:退出登录，INSERT:新增，UPDATE:修改，INSERT_OR_UPDATE:新增或修改，" +
        "DELETE:删除，QUERY:查询，GRANT:授权，IMPORT:导入，EXPORT:导出，FORCE:强制退出，GEN_CODE:生成代码，CLEAN:清空，OTHER:其它")
public enum BusinessTypeEnum implements  BaseEnum {

    LOGIN("登录"),
    LOGOUT("退出登录"),
    INSERT("新增"),
    UPDATE("修改"),
    INSERT_OR_UPDATE("新增或修改"),
    DELETE("删除"),
    QUERY("查询"),
    GRANT("授权"),
    IMPORT("导入"),
    EXPORT("导出"),
    FORCE("强制退出"),
    GEN_CODE("生成代码"),
    CLEAN("清空"),
    OTHER("其它"),
    ;

    @ApiModelProperty(value = "状态名称")
    private String name;

    @JsonCreator
    public static BusinessTypeEnum jsonInit(String value) {
        for (BusinessTypeEnum item : values()) {
            if (item.toString().equals(value)) {
                return item;
            }
        }
        return null;
    }

    private BusinessTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "状态编码")
    @Override
    public String getValue() {
        return this.name();
    }


}
