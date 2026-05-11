package com.jiumai.base.sm.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.sm.entity.LoginLog;
import com.jiumai.base.sm.mapper.LoginLogMapper;
import com.jiumai.base.sm.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author mysqlGen
 * @since 2023-04-20
 */
@Slf4j
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public void saveLoginLog(Long opId, Integer type, Integer loginPlatform) {
        try {
            LoginLog loginLog = new LoginLog();
            loginLog.setOpId(opId);
            loginLog.setType(type);
            loginLog.setLoginPlatform(loginPlatform);
            loginLog.setLoginTime(LocalDateTime.now());
            this.save(loginLog);
        } catch (Exception e) {
            log.error("记录登录日志失败：", e);
        }
    }
}
