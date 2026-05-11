package com.jiumai.base.common.core.security;

import java.security.MessageDigest;

import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;

/**
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public class MD5Util {

    private static SysLog log = SysLogFactory.getLogger(MD5Util.class);

    /**
     * MD5加密
     * 
     * @param s
     *            原文
     * @return 加密后的文本
     */
    public static String MD5(String s) {
        try {
            byte[] bInput = s.getBytes("UTF-8");

            return MD5(bInput);
        } catch (Exception e) {
            log.error("======MD5加密失败====", e);
        }
        return null;
    }

    /**
     * MD5加密
     * 
     * @param s
     *            原文
     * @param encoding
     *            编码
     * @return 加密后的文本
     */
    public static String MD5(String s, String encoding) {
        try {
            byte[] bInput = s.getBytes(encoding);

            return MD5(bInput);
        } catch (Exception e) {
            log.error("======MD5加密失败====", e);
        }
        return null;
    }

    /**
     * MD5加密
     * 
     * @param bytes
     * @return
     */
    public static String MD5(byte[] bytes) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] b = md.digest();
            int j = b.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = b[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error("======MD5加密失败====", e);
        }
        return null;
    }

    /**
     * MD5加密
     * 
     * @param s
     * @return byte[]
     */
    public static byte[] Md5Byte(String s) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return md5.digest(s.getBytes("UTF-8"));
        } catch (Exception e) {
            log.error("======MD5加密失败====", e);
        }
        return null;

    }
}
