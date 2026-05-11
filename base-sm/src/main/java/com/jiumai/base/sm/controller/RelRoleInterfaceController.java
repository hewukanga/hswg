package com.jiumai.base.sm.controller;

import com.jiumai.base.sm.service.RelRoleInterfaceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 接口权限关联管理 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2023-08-21
 */
@RestController
@RequestMapping("/d-admin/rel-role-interface")
@Api(tags = {"接口权限关联管理"})
public class RelRoleInterfaceController {

    @Resource
    private RelRoleInterfaceService relRoleInterfaceService;

}
