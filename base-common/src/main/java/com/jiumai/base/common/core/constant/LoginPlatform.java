package com.jiumai.base.common.core.constant;

/**
 * @author ：cz
 * @date ：Created in 2023-04-20
 * @description：登录类型
 */
public interface LoginPlatform {
    /** PC后台 */
    Integer LOGIN_PC = 1;
    /** 大屏 */
    Integer LOGIN_SCREEN = 2;
    /** 移动端 */
    Integer LOGIN_MOBILE = 3;
    /** 微信授权 */
    Integer LOGIN_WX = 4;
    /** 浙里办 */
    Integer LOGIN_ZLB = 5;
    /** 浙政钉 */
    Integer LOGIN_ZZD = 6;
    /** 第三方授权 */
    Integer LOGIN_THIRD_AUTO = 99;
}
