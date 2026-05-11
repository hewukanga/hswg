/*
 * ZtreeDTO.java Created on 2016年10月26日 上午10:22:34
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jiumai.base.sm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
@Data
public class ZtreeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "节点标识",example="0")
    private Long id;

    @ApiModelProperty(value = "父节点标识",example="0")
    private Long pId;

    @ApiModelProperty(value = "节点名称")
    private String name;

    @ApiModelProperty(value = "节点是否展开")
    private boolean open;


}
