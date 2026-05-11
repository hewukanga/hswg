/*
 * @(#)CookieUtil.java 2014-1-10下午3:31:00
 * Copyright (c)  2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.common.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类 
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public class CookieUtils {

    /**
     * 添加cookie
     * 
     * @param response
     *            HttpServletResponse
     * @param name
     *            cookie名称
     * @param value
     *            cookie值
     * @param path
     *            路径（服务器内多个应用共享cookie以及设置cookie的作用范围）
     * @param maxage
     *            最大失效时间(秒)(如果为0，就说明立即删除；如果是负数就表明当浏览器关闭时自动删除)
     * @param domain
     *            域名（跨域共享cookie时设置该值）
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
            String path, int maxage, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (path != null && path.length() > 0) {
            cookie.setPath(path);
        } else {
            path = StringUtils.isBlank(request.getContextPath()) ? "/" : request.getContextPath();
            cookie.setPath(path);
        }
        if (domain != null && domain.length() > 0) {
            cookie.setDomain(domain);
        }
        if (maxage >= 0) {
            cookie.setMaxAge(maxage);
        }
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * 
     * @param request
     *            HttpServletRequest
     * @param name
     *            cookie名称
     * @return cookie值
     */
    public static String getCookie(HttpServletRequest request, String name) {
        String returnValue = null;
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (name.equalsIgnoreCase(cookie.getName().trim())) {
                    returnValue = cookie.getValue();
                    break;
                }
            }
        }
        return returnValue;
    }

    /**
     * 删除cookie
     * 
     * @param response
     *            HttpServletResponse
     * @param name
     *            cookie名称
     * @param path
     *            路径
     * @param domain
     *            域名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name, String path,
            String domain) {
        addCookie(request, response, name, null, path, 0, domain);
    }
}
