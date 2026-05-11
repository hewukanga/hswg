/*
 * @(#)StringUtil.java 2014-1-13上午11:16:05
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.common.core.utils;

import com.alibaba.fastjson2.JSONObject;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符处理工具类
 *
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 整数
     */
    private static final String V_INTEGER = "^-?[0-9]\\d*$";

    /**
     * 正整数
     */
    private static final String V_Z_INDEX = "^[1-9]\\d*$";

    /**
     * 负整数
     */
    private static final String V_NEGATIVE_INTEGER = "^-[1-9]\\d*$";

    /**
     * 数字
     */
    private static final String V_NUMBER = "^([+-]?)\\d*\\.?\\d+$";

    /**
     * 正数
     */
    private static final String V_POSITIVE_NUMBER = "^[1-9]\\d*|0$";

    /**
     * 负数
     */
    private static final String V_NEGATINE_NUMBER = "^-[1-9]\\d*|0$";

    /**
     * 浮点数
     */
    private static final String V_FLOAT = "^([+-]?)\\d*\\.\\d+$";

    /**
     * 正浮点数
     */
    private static final String V_POSTTIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";

    /**
     * 负浮点数
     */
    private static final String V_NEGATIVE_FLOAT = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";

    /**
     * 非负浮点数（正浮点数 + 0）
     */
    private static final String V_UNPOSITIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";

    /**
     * 非正浮点数（负浮点数 + 0）
     */
    private static final String V_UN_NEGATIVE_FLOAT = "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";

    /**
     * 邮件
     */
    private static final String V_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    /**
     * 颜色
     */
    private static final String V_COLOR = "^[a-fA-F0-9]{6}$";

    /**
     * url
     */
    private static final String V_URL = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";

    /**
     * 仅中文
     */
    private static final String V_CHINESE = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";

    /**
     * 仅ACSII字符
     */
    private static final String V_ASCII = "^[\\x00-\\xFF]+$";

    /**
     * 邮编
     */
    private static final String V_ZIPCODE = "^\\d{6}$";

    /**
     * 手机
     */
    private static final String V_MOBILE = "^[1][3,4,5,7,8][0-9]{9}$";

    /**
     * ip地址
     */
    private static final String V_IP4 = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

    /**
     * 图片
     */
    private static final String V_PICTURE = "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";

    /**
     * 日期
     */
    private static final String V_DATE = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

    /**
     * QQ号码
     */
    private static final String V_QQ_NUMBER = "^[1-9]*[1-9][0-9]*$";

    /**
     * 电话号码的函数(包括验证国内区号,国际区号,分机号)
     */
    private static final String V_TEL = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";

    /**
     * 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
     */
    private static final String V_USERNAME = "^\\w+$";

    /**
     * 字母
     */
    private static final String V_LETTER = "^[A-Za-z]+$";

    /**
     * 大写字母
     */
    private static final String V_LETTER_U = "^[A-Z]+$";

    /**
     * 小写字母
     */
    private static final String V_LETTER_I = "^[a-z]+$";

    /**
     * 身份证
     */
    private static final String V_IDCARD = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

    /**
     * 验证两位数
     */
    private static final String V_TWO＿POINT = "^[0-9]+(.[0-9]{2})?$";

    /**
     * 验证一个月的31天
     */
    private static final String V_31DAYS = "^((0?[1-9])|((1|2)[0-9])|30|31)$";

    /**
     * 验证是否包含中文、数字和字母
     */
    private static final String V_CHINESE_NUMBER_LETTER = "^[a-zA-Z0-9\u4E00-\u9FA5\uF900-\uFA2D]+$";

    /**
     * 验证是不是整数
     *
     * @param value 要验证的字符串 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Integer(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_INTEGER, value);
    }

    /**
     * 验证是不是正整数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Z_index(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_Z_INDEX, value);
    }

    /**
     * 验证是不是负整数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNegativeInteger(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_NEGATIVE_INTEGER, value);
    }

    /**
     * 验证是不是数字
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNumber(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_NUMBER, value);
    }

    /**
     * 验证是不是正数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPositiveNumber(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_POSITIVE_NUMBER, value);
    }

    /**
     * 验证是不是负数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNegatineNumber(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_NEGATINE_NUMBER, value);
    }

    /**
     * 验证一个月的31天
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean is31Days(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_31DAYS, value);
    }

    /**
     * 验证是不是ASCII
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean ASCII(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_ASCII, value);
    }

    /**
     * 验证是不是中文
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isChinese(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_CHINESE, value);
    }

    /**
     * 验证是不是颜色
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isColor(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_COLOR, value);
    }

    /**
     * 验证是不是日期
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isDate(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_DATE, value);
    }

    /**
     * 验证是不是邮箱地址
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isEmail(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_EMAIL, value);
    }

    /**
     * 验证是不是浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isFloat(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_FLOAT, value);
    }

    /**
     * 验证是不是正确的身份证号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isIDcard(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_IDCARD, value);
    }

    /**
     * 验证是不是正确的IP地址
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isIP4(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_IP4, value);
    }

    /**
     * 验证是不是字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isLetter(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_LETTER, value);
    }

    /**
     * 验证是不是小写字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isLowerLetter(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_LETTER_I, value);
    }

    /**
     * 验证是不是大写字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isUpperLetter(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_LETTER_U, value);
    }

    /**
     * 验证是不是手机号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isMobile(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_MOBILE, value);
    }

    /**
     * 验证是不是负浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNegative_float(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_NEGATIVE_FLOAT, value);
    }

    /**
     * 验证图片
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPicture(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_PICTURE, value);
    }

    /**
     * 验证正浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPosttiveFloat(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_POSTTIVE_FLOAT, value);
    }

    /**
     * 验证QQ号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isQQ(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_QQ_NUMBER, value);
    }

    /**
     * 验证电话
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isTel(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_TEL, value);
    }

    /**
     * 验证两位小数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isTwoPointNumber(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_TWO＿POINT, value);
    }

    /**
     * 验证非正浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isUnNegativeFloat(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_UN_NEGATIVE_FLOAT, value);
    }

    /**
     * 验证非负浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsUnpositiveFloat(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_UNPOSITIVE_FLOAT, value);
    }

    /**
     * 验证用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean UserName(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_USERNAME, value);
    }

    /**
     * 验证邮编
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Zipcode(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_ZIPCODE, value);
    }

    /**
     * 验证是否包含中文、数字和字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean chineseNumberLetter(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return match(V_CHINESE_NUMBER_LETTER, value);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 校验字符串中是否存在${}
     *
     * @param params
     * @param model
     * @return
     */
    public static String match(JSONObject params, String model) {
        // 查询并替换${XXX}
        List<String> matcherList = ValidataUtils.getMatchers(ValidataUtils.V_PARAMS, model);
        for (String str : matcherList) {
            String paramName = str.substring(2, str.length() - 1);
            Object obj = params.get(paramName);
            if (obj == null) {
                continue;
            }
            model = model.replaceAll("\\$\\{" + paramName + "\\}", obj.toString());
        }
        return model;
    }

    /**
     * 检查字符是否为url
     *
     * @param pInput 要检查的字符串
     * @return boolean
     */
    public static boolean isUrl(String pInput) {
        if (pInput == null) {
            return false;
        }
        Pattern p = Pattern.compile(V_URL);
        Matcher matcher = p.matcher(pInput);
        return matcher.find();
    }

    /**
     * 过滤特殊字符
     *
     * @param iputString 原字符
     * @return 过滤后的字符
     */
    public static String stringFilter(String iputString) {
        // 清除掉所有特殊字符
        String regEx = "[`^&|'<>/?‘”“’]";
        if (iputString == null) {
            return "";
        }
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(iputString);
        return matcher.replaceAll("").trim();
    }

    /**
     * 字符过滤器
     *
     * @param iputString 原字符
     * @param regEx      正则表达式，过滤规则
     * @return
     */
    public static String stringFilter(String iputString, String regEx) {
        if (iputString == null) {
            return "";
        }
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(iputString);
        return matcher.replaceAll("").trim();
    }

    /**
     * 字符过滤器
     *
     * @param iputString    原字符
     * @param regEx         正则表达式，过滤规则
     * @param replaceString 替换成的字符
     * @return
     */
    public static String stringFilter(String iputString, String regEx, String replaceString) {
        if (iputString == null) {
            return "";
        }
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(iputString);
        return matcher.replaceAll(replaceString).trim();
    }

    /**
     * 去左空格
     *
     * @param str 原字符
     * @return
     */
    public static String leftTrim(String str) {
        if (str == null || str.equals("")) {
            return str;
        } else {
            return str.replaceAll("^[\40]+", "");
        }
    }

    /**
     * 去右空格
     *
     * @param str 原字符
     * @return
     */
    public static String rightTrim(String str) {
        if (str == null || str.equals("")) {
            return str;
        } else {
            return str.replaceAll("[\40]+$", "");
        }
    }

    /**
     * mysql 特殊字符转移过滤
     *
     * @param str 原字符
     * @return
     */
    public static String mysqlReplace(String str) {
        if (str != null) {
            str = str.replace("\\", "\\\\\\").replace("'", "\\'").replace("%", "\\%").replace("_", "\\_%");
        }
        return str;
    }

    /**
     * 身份证号码校验
     *
     * @param IDStr
     * @return
     * @throws ParseException
     */
    public static String IDCardValidate(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                return errorInfo;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return errorInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return errorInfo;
        }
        Hashtable<String, String> h = getAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return errorInfo;
        }
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equalsIgnoreCase(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        } else {
            return "";
        }
        return "";
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 手机号脱敏
     *
     * @param mobile
     * @return
     */
    public static String mobileConceal(String mobile) {
        if (CommonFuntions.isEmptyObject(mobile)) {
            return "";
        }
        return left(mobile, 3).concat(removeStart(leftPad(right(mobile, 3), length(mobile), "*"), "***"));
    }

    /**
     * 字符串脱敏
     *
     * @param str        脱敏字符串
     * @param beginIndex 从第几个脱敏
     * @param retainNum  尾上保留字符数
     * @return
     */
    public static String strConceal(String str, int beginIndex, int retainNum) {
        if (CommonFuntions.isEmptyObject(str)) {
            return "";
        }
        int strLength = str.length();
        if (strLength <= (beginIndex + retainNum)) {
            retainNum = 0;
        }
        String newStr = str.substring(beginIndex - 1, str.length() - retainNum);
        String desStr = "";
        if (newStr.length() > 0) {
            for (int i = 0; i < newStr.length(); i++) {
                desStr += "*";
            }
        }
        return str.substring(0, beginIndex - 1) + desStr + str.substring(str.length() - retainNum, str.length());
    }

    /**
     * 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
     *
     * @param value
     * @return
     */
    public static int getLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            // 获取一个字符
            String temp = value.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为2
                valueLength += 2;
            } else {
                // 其他字符长度为1
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 省份代码
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Hashtable<String, String> getAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
     * "jar:file:/home/whf/foo.jar!cn/fh" -> "/home/whf/foo.jar"
     */
    public static String getRootPath(URL url) {
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf('!');

        if (-1 == pos) {
            return fileUrl;
        }

        return fileUrl.substring(5, pos);
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * "cn.fh.lightning" -> "cn/fh/lightning"
     *
     * @param name
     * @return
     */
    public static String dotToSplash(String name) {
        return name.replaceAll("\\.", "/");
    }

    /**
     * "Apple.class" -> "Apple"
     */
    public static String trimExtension(String name) {
        int pos = name.indexOf('.');
        if (-1 != pos) {
            return name.substring(0, pos);
        }

        return name;
    }

    /**
     * /application/home -> /home
     *
     * @param uri
     * @return
     */
    public static String trimURI(String uri) {
        String trimmed = uri.substring(1);
        int splashIndex = trimmed.indexOf('/');

        return trimmed.substring(splashIndex);
    }
}
