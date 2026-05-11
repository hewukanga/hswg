package com.jiumai.base.common.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工厂
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public class SysLogFactory {

    public static SysLog getLogger(Class<?> clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        return new SysLog(logger);
    }

    public static SysLog getLogger(String logName) {
        Logger logger = LoggerFactory.getLogger(logName);
        return new SysLog(logger);
    }
}
