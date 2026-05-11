package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.query.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 接口管理查询条件
 * </p>
 *
 * @author mysqlGen
 * @since 2023-08-21
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("接口管理查询条件")
public class InterfaceConfQuery extends BasePage {

    @ApiModelProperty("接口名称")
    private String interfaceName;

    @ApiModelProperty("接口组名")
    private String interfaceGroupName;
}
