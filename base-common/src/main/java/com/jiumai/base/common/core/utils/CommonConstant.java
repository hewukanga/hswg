package com.jiumai.base.common.core.utils;

public class CommonConstant {

    /**============================Redis相关常量start====================================*/
    public static final String DICTIONARY_KEY = "BASE_DICTIONARY";// 字典缓存的KEY
    /**============================Redis相关常量 end ====================================*/

    //接口与菜单id/按钮id关联关系基础池
    public static final String INTERFACE_RESOURCE_BASE_KEY = "INTERFACE.RESOURCE.BASE.KEY";

    //接口与角色的关联关系基础池
    public static final String INTERFACE_ROLE_BASE_KEY = "INTERFACE.ROLE.BASE.KEY";

    //接口权限-token和role的关联关系
    public static final String INTERFACE_AUTH_TOKEN_REL_ROLE_KEY = "INTERFACE.AUTH.TOKEN.REL.ROLE.KEY.%s";

    //接口权限-token和resource的关联关系
    public static final String INTERFACE_AUTH_TOKEN_REL_RESOURCE_KEY = "INTERFACE.AUTH.TOKEN.REL.RESOURCE.KEY.%s";

    //opId和token关系
    public static final String OP_ID_REL_TOKEN_KEY = "op.id.rel.token.key.%s";
}
