package com.jiumai.base.sm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.common.core.constant.LoginPlatform;
import com.jiumai.base.sm.entity.LoginLog;

/**
 * <p>
 * 登录日志 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2023-04-20
 */
public interface LoginLogService extends IService<LoginLog> {
    /**
     * 保存登录日志
     * @param opId 用户ID
     * @param type 1-登录，2-退出登录
     * @param loginPlatform 登录类型 {@link LoginPlatform}
     */
    void saveLoginLog(Long opId, Integer type,Integer loginPlatform);


}
