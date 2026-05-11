/*
 * AuthServiceImpl.java Created on 2016年9月28日 下午2:30:15
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
package com.jiumai.base.sm.service.impl;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.security.MD5Util;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.RandomUtils;
import com.jiumai.base.common.core.utils.StringUtils;
import com.jiumai.base.common.core.utils.password.PasswordCheckUtil;
import com.jiumai.base.sm.dto.OrganizationDTO;
import com.jiumai.base.sm.entity.SmOperator;
import com.jiumai.base.sm.enums.CodeStatusEnum;
import com.jiumai.base.sm.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台操作员登录及退出管理类
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private RelOpOrgService relOpOrgService;

    @Resource
    private RelOpRoleService relOpRoleService;

    @Resource
    private OperatorService operService;

    @Resource
    private RelOpAreaService relOpAreaService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private ResourceService resourceService;

    private static final String LOGIN_ERROR_MSG = "账号或密码错误！";

    @Value("${sys.sm4.key}")
    public String key;

    @Override
    public LoginOperator login(String opLoginName, String inputPwd) throws Exception {
        SmOperator op = operService.getByOpLoginName(opLoginName);
        if (op == null || op.getOpLoginName() == null) {
            throw new BizException(LOGIN_ERROR_MSG);
        }
        if (op.getStatus() == CodeStatusEnum.LOCKED) {
            throw new BizException(LOGIN_ERROR_MSG);
        }
        if (op.getStatus() == CodeStatusEnum.INVALID) {
            throw new BizException(LOGIN_ERROR_MSG);
        }
        String md5InputPwd = "";
        boolean needUpdatePwd = false;
        if (StringUtils.isNotBlank(inputPwd)) {
            SM4 sm4 = SmUtil.sm4(key.getBytes(StandardCharsets.UTF_8));
            String pwd = sm4.decryptStr(inputPwd, StandardCharsets.UTF_8);
            if (StringUtils.isBlank(pwd)) {
                throw new BizException("登录失败，PC端请使用 Ctrl+F5 或 Fn+Ctrl+F5 刷新后重试！");
            }
            String errMsg = PasswordCheckUtil.checkPwdStrong(pwd);
            if (!"OK".equals(errMsg)) {
                needUpdatePwd = true;
            }
            ;
            String salt = op.getSalt();
            if (CommonFuntions.isEmptyObject(salt)) {
                // 若当前用户密码无盐值，使用MD5进行校验，并添加随机盐值更新原始密码
                md5InputPwd = MD5Util.MD5(pwd);
                String newSalt = RandomUtils.generateString(8);
                String newPwd = SmUtil.sm3WithSalt(newSalt.getBytes()).digestHex(pwd);
                LambdaUpdateWrapper<SmOperator> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(SmOperator::getOpId, op.getOpId())
                        .set(SmOperator::getSalt, newSalt)
                        .set(SmOperator::getPassword, newPwd)
                        .set(SmOperator::getResetPwdDate, new Date())
                        .set(SmOperator::getModifyDate, LocalDateTime.now());
                operService.update(updateWrapper);
            } else {
                md5InputPwd = SmUtil.sm3WithSalt(salt.getBytes()).digestHex(pwd);
            }

        }
        String dbPwd = op.getPassword();
        if (!dbPwd.equals(md5InputPwd)) {
            throw new BizException(LOGIN_ERROR_MSG);
        }

        LoginOperator loginOperator = new LoginOperator();
        loginOperator.setNeedUpdatePwd(needUpdatePwd);
        BeanUtils.copyProperties(op, loginOperator);

        OrganizationDTO org = organizationService.getOrganizationByOrgId(op.getOrgId());
        loginOperator.setOrgName(org.getOrgName());

        // 用户额外信息
        Map<String, Object> exetInfo = new HashMap<>();

        List<Long> accOrgList = relOpOrgService.findRelOpOrgIdsByOpId(op.getOpId());
        List<Long> relRoleList = relOpRoleService.findRelOpRoleIdsByOpId(op.getOpId());
        List<Long> accAreaList = relOpAreaService.findRelOpAreaIdsByOpId(op.getOpId());

        exetInfo.put("accAreaList", accAreaList);
        exetInfo.put("accOrgList", accOrgList);
        exetInfo.put("relRoleList", relRoleList);
        loginOperator.setExetInfo(exetInfo);
        loginOperator.setRoleIds(relRoleList);

        return loginOperator;
    }

    @Override
    public LoginOperator autoLogin(String code) throws Exception {
        // 获取第三方用户信息， 需要时可以补充
//        JSONObject jsonObject = new JSONObject();
//        String dingUserId = jsonObject.getString("dingUserId");
//        SmOperator op = operService.getByZzdId(dingUserId);

        SmOperator op = new SmOperator();
        if (op == null || op.getOpLoginName() == null) {
            throw new BizException(LOGIN_ERROR_MSG);
        }
        if (op.getStatus() == CodeStatusEnum.LOCKED) {
            throw new BizException(LOGIN_ERROR_MSG);
        }
        if (op.getStatus() == CodeStatusEnum.INVALID) {
            throw new BizException(LOGIN_ERROR_MSG);
        }

        LoginOperator opDTO = new LoginOperator();
        BeanUtils.copyProperties(op, opDTO);

        OrganizationDTO org = organizationService.getOrganizationByOrgId(op.getOrgId());
        opDTO.setOrgName(org.getOrgName());

        // 用户额外信息
        Map<String, Object> exetInfo = new HashMap<>();

        List<Long> accOrgList = relOpOrgService.findRelOpOrgIdsByOpId(op.getOpId());
        List<Long> relRoleList = relOpRoleService.findRelOpRoleIdsByOpId(op.getOpId());
        List<Long> accAreaList = relOpAreaService.findRelOpAreaIdsByOpId(op.getOpId());

        exetInfo.put("accAreaList", accAreaList);
        exetInfo.put("accOrgList", accOrgList);
        exetInfo.put("relRoleList", relRoleList);
        opDTO.setExetInfo(exetInfo);
        opDTO.setRoleIds(relRoleList);
        return opDTO;
    }
}
