package com.jiumai.base.common.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommQueryReturnDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;//通用下拉查询返回的名称值

    private String id;//通用下拉查询返回的ID值


}
