package com.jiumai.base.common.core.utils.password;
 
public class PwdCheckConfig {

    /** 密码最小长度*/
    public static final int MIN_LENGTH = 6;
    /** 密码最大长度，默认为20 */
    public static final int MAX_LENGTH = 20;
    /** 是否检测密码口令长度 */
    public static final boolean CHECK_LENGTH = true;
    /** 是否包含数字 */
    public static final boolean CHECK_DIGIT = true;
    /** 是否包含字母 */
    public static final boolean CHECK_CONTAIN_CASE = true;
    /** 是否包含小写字母 */
    public static final boolean CHECK_LOWER_CASE = true;
    /** 是否包含大写字母 */
    public static final boolean CHECK_UPPER_CASE = true;
    /** 是否包含特殊符号 */
    public static final boolean CHECK_CONTAIN_SPECIAL_CHAR = false;
    /** 是否区分大小写 */
    public static final boolean CHECK_DISTINGGUISH_CASE = false;
    /** 是否检测键盘按键横向连续 */
    public static final boolean CHECK_HORIZONTAL_KEY_SEQUENTIAL = false;
    /** 键盘物理位置横向不允许最小的连续个数 */
    public static final int LIMIT_HORIZONTAL_NUM_KEY = 4;
    /** 是否检测键盘按键斜向连续 */
    public static final boolean CHECK_SLOPE_KEY_SEQUENTIAL = false;
    /** 键盘物理位置斜向不允许最小的连续个数 */
    public static final int LIMIT_SLOPE_NUM_KEY = 4;
    /** 是否检测逻辑位置连续 */
    public static final boolean CHECK_LOGIC_SEQUENTIAL = false;
    /** 密码口令中字符在逻辑位置上不允许最小的连续个数 */
    public static final int LIMIT_LOGIC_NUM_CHAR = 4;
    /** 是否检测连续字符相同 */
    public static final boolean CHECK_SEQUENTIAL_CHAR_SAME = false;
    /** 密码口令中相同字符不允许最小的连续个数 */
    public static final int LIMIT_NUM_SAME_CHAR = 4;
    /** 是否检测常用词库 */
    public static final boolean CHECK_SIMPLE_WORD = true;

    /**
     * 特殊符号集合
     */
    public static final String SPECIAL_CHAR = "!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~";
    /**
     * 键盘横向方向规则
     */
    public static final String[] KEYBOARD_HORIZONTAL_ARR = {"01234567890", "qwertyuiop", "asdfghjkl", "zxcvbnm",};
    /**
     * 键盘斜线方向规则
     */
    public static final String[] KEYBOARD_SLOPE_ARR = {"wasu","1qaz", "2wsx", "3edc", "4rfv", "5tgb", "6yhn", "7ujm", "8ik,", "9ol.",
            "0p;/", "=[;.", "-pl,", "0okm", "9ijn", "8uhb", "7ygv", "6tfc", "5rdx", "4esz"};
    /**
     * 常用词库
     */
    public static final String[] SIMPLE_WORDS = {"admin", "szim", "epicrouter", "password", "grouter", "dare", "root", "guest",
            "user", "success", "pussy", "mustang", "fuckme", "jordan", "test", "hunter", "jennifer", "batman", "thomas",
            "soccer", "sexy", "killer", "george", "asshole", "fuckyou", "summer", "hello", "secret", "fucker", "enter",
            "cookie", "administrator",
            // 中国网民常用密码
            "xiaoming", "taobao", "iloveyou", "woaini", "982464",
            // 国外网民常用密码
            "monkey", "letmein", "trustno1", "dragon", "baseball", "master", "sunshine", "ashley", "bailey", "shadow",
            "superman", "football", "michael", "qazwsx"};
 
 
}