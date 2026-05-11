package com.jiumai.base.common.core.utils;

import java.math.BigDecimal;

/**
 * 金额元分之间转换工具类
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public class MoneyUtils {

    /** 金额为分的格式 */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 将分转换为元
     * 
     * @param amount
     * @return double
     * @throws Exception
     */
    public static String changeF2YString(long amount) throws Exception {
        return BigDecimal.valueOf(amount).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP)
                .toString();
    }

    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     * 
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(Long amount) throws Exception {
        if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }

        int flag = 0;
        String amString = amount.toString();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    result.append(",");
                }
                result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
            }
            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            return "-" + result.toString();
        } else {
            return result.toString();
        }
    }

    /**
     * 将分为单位的转换为元 （除100）
     * 
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(String amount) throws Exception {
        if (!amount.matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100))
                .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 将元为单位的转换为分 （乘100）
     * 
     * @param amount
     * @return
     */
    public static String changeY2F(Long amount) {
        if (amount == null) {
            return "0";
        }
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();
    }

    /**
     * 将元为单位的转换为厘 （乘1000）
     * 
     * @param amount
     * @return
     */
    public static String changeY2L(Long amount) {
        if (amount == null) {
            return "0";
        }
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(1000)).toString();
    }

    /**
     * 将厘为单位的转换为 元（除1000）
     * 
     * @param amount
     * @return
     */
    public static String changeL2Y(Long amount) {
        if (amount == null) {
            return new BigDecimal(0).setScale(2).toString();
        }
        return BigDecimal.valueOf(amount).divide(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 将厘为单位的转换为元（除100）
     * 
     * @param amount
     * @return
     */
    public static String changeL2M(Long amount) {
        if (amount == null) {
            return new BigDecimal(0).setScale(2).toString();
        }

        return BigDecimal.valueOf(amount).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     * 
     * @param amount
     * @return
     */
    public static String changeY2F(String amount) {
        String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
        // 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong.toString();
    }

    /***
     * 根据数值 四舍五入
     * 
     * @param amount
     * @return
     */
    public static Long roundedMoney(Long amount) {
        int value = 0;
        try {
            Float db = Float.valueOf(amount.toString()) / 10;
            value = Math.round(db);
            value = value * 10;
        } catch (Exception e) {
            value = 0;
        }
        return Long.valueOf(value);
    }

    /**
     * 提供（相对）精确的除法运算. 当发生除不尽的情况时,由scale参数指 定精度,以后的数字四舍五入.
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static String divide(String v1, Long v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_CEILING).toString();
    }
    public static void main(String[] args) {
		try {
			String changeF2Y = MoneyUtils.changeF2Y(2000000L);
			System.out.println(changeF2Y);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

