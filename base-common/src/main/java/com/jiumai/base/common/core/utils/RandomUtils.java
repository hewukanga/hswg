/*
 * RandomUtils.java Created on 2017年1月9日 下午1:12:27
 * Copyright (c)  2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
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
package com.jiumai.base.common.core.utils;

import java.util.Random;

/**
 * 随机数生成工具类
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public class RandomUtils {
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "0123456789";
    public static final String NUM_CAPITAL_CHAR = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /**
     * 生成n位随机数字
     * 
     * @param n
     * @return
     */
    public static String getRandomCode(int n) {
        // 生成n位随机数字
        String rcode = "";
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            rcode += random.nextInt(10) + "";
        }
        return rcode;
    }
    
    /**
     * 返回一个定长的随机字符串(只包含大写字母、数字,去除O和I)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateStringByNumCapital(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUM_CAPITAL_CHAR.charAt(random.nextInt(NUM_CAPITAL_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     * 
     * @param letLength
     *            字母长度
     * @param numLength
     *            数字长度
     * @return
     */
    public static String generateString(int letLength, int numLength) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < letLength; i++) {
            sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        for (int i = 0; i < numLength; i++) {
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    /**
     * 返回一个定长的随机字符串(只包含数字)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateNumString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 生成一个定长的纯0字符串
     * 
     * @param length
     *            字符串长度
     * @return 纯0字符串
     */
    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     * 
     * @param num
     *            数字
     * @param fixdlenth
     *            字符串长度
     * @return 定长的字符串
     */
    public static String toFixdLengthString(long num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 每次生成的len位数都不相同
     * 
     * @param param
     * @return 定长的数字
     */
    public static int getNotSimple(int[] param, int len) {
        Random rand = new Random();
        for (int i = param.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result * 10 + param[i];
        }
        return result;
    }

    /**
     * 随机指定范围内N个不重复的数 最简单最基本的方法
     * 
     * @param min
     *            指定范围最小值
     * @param max
     *            指定范围最大值
     * @param n
     *            随机数个数
     */
    public static int[] randomCommon(int min, int max, int n) {
    	if(n > max) {
    		return null;
    	}
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        Integer[] result = new Integer[n];
        int[] result2 = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
            	if(result[j]  == null) {
            		break;
            	}
                if (num == result[j].intValue()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                result2[count] = result[count].intValue();
                count++;
            }
        }
        return result2;
    }

    public static void main(String[] args) {
    	for (int i : randomCommon(0,3,2)) {
			System.out.println(i);
		}
    	
//        System.out.println("返回一个定长的随机字符串(只包含大小写字母、数字):" + randomCommon(0,3,3));
//        System.out.println("返回一个定长的随机字符串(只包含大小写字母、数字):" + generateString(10));
//        System.out.println("返回一个定长的随机字符串(只包含大小写字母、数字):" + generateString(1, 6).toUpperCase());
//        System.out.println("返回一个定长的随机纯字母字符串(只包含大小写字母):" + generateMixString(10));
//        System.out.println("返回一个定长的随机纯大写字母字符串(只包含大小写字母):" + generateLowerString(10));
//        System.out.println("返回一个定长的随机纯小写字母字符串(只包含大小写字母):" + generateUpperString(10));
//        System.out.println("返回一个定长的随机纯数字字符串(只包含数字):" + generateNumString(6));
//        System.out.println("生成一个定长的纯0字符串:" + generateZeroString(10));
//        System.out.println("根据数字生成一个定长的字符串，长度不够前面补0:" + toFixdLengthString(123, 10));
//        int[] in = { 1, 2, 3, 4, 5, 6, 7 };
//        System.out.println("每次生成的len位数都不相同:" + getNotSimple(in, 3));
    }
}
