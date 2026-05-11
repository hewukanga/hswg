package com.jiumai.base.common.core.utils.password;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PasswordCheckUtil {

    public static void main(String[] args) {
        while (true) {
            String password = null;
            Scanner sn = new Scanner(System.in);
            password = sn.nextLine();
            String errorMsg = PasswordCheckUtil.checkPwdStrong(password);
            System.out.println(errorMsg);
        }
    }

    /**
     * @return 符合要求 返回true
     * @brief 评估密码中包含的字符类型是否符合要求
     * @param[in] password            密码字符串
     */
    public static String checkPwdStrong(String password) {
        if (password == null || "".equals(password)) {
            return "请输入密码！";
        }
        // 替换空白字符：空格，tab，回车符，换行符
        password = password.replaceAll("\\s","");
        boolean flag = false;

        // 检测长度
        if (PwdCheckConfig.CHECK_LENGTH) {
            flag = checkPasswordLength(password);
            if (!flag) {
                return "密码长度在" + PwdCheckConfig.MIN_LENGTH + " ~ " + PwdCheckConfig.MAX_LENGTH + "之间！";
            }
        }
        // 检测包含数字
        if (PwdCheckConfig.CHECK_DIGIT) {
            flag = checkContainDigit(password);
            if (!flag) {
                return "密码未包含数字！";
            }
        }
        // 检测包含字母
        if (PwdCheckConfig.CHECK_CONTAIN_CASE) {
            flag = checkContainCase(password);
            if (!flag) {
                return "密码未包含字母！";
            }
        }
        //检测包含小写字母
        if (PwdCheckConfig.CHECK_LOWER_CASE) {
            flag = checkContainLowerCase(password);
            if (!flag) {
                return "密码未包含小写字母！";
            }
        }
        // 检测包含大写字母
        if (PwdCheckConfig.CHECK_UPPER_CASE) {
            flag = checkContainUpperCase(password);
            if (!flag) {
                return "密码未包含大写字母！";
            }
        }
        // 检测包含特殊符号
        if (PwdCheckConfig.CHECK_CONTAIN_SPECIAL_CHAR) {
            flag = checkContainSpecialChar(password);
            if (!flag) {
                return "密码未包含特殊字符！";
            }
        }
        // 检测键盘横向连续
        if (PwdCheckConfig.CHECK_HORIZONTAL_KEY_SEQUENTIAL) {
            flag = checkLateralKeyboardSite(password);
            if (flag) {
                return "密码不能有连续字符！";
            }
        }
        // 检测键盘斜向连续
        if (PwdCheckConfig.CHECK_SLOPE_KEY_SEQUENTIAL) {
            flag = checkKeyboardSlantSite(password);
            if (flag) {
                return "密码不能有连续字符！";
            }
        }
        // 检测逻辑位置连续
        if (PwdCheckConfig.CHECK_LOGIC_SEQUENTIAL) {
            flag = checkSequentialChars(password);
            if (flag) {
                return "密码不能有连续字符！";
            }
        }
        // 检测相邻字符是否相同
        if (PwdCheckConfig.CHECK_SEQUENTIAL_CHAR_SAME) {
            flag = checkSequentialSameChars(password);
            if (flag) {
                return "密码包含过多相同字符！";
            }
        }
        // 检测常用词库
        if (PwdCheckConfig.CHECK_SIMPLE_WORD) {
            flag = checkSimpleWord(password);
            if (flag) {
                return "密码不能为常用密码！";
            }
        }

        return "OK";
    }

    /**
     * @return 符合长度要求 返回true
     * @brief 检测密码中字符长度
     * @param[in] password            密码字符串
     */
    private static boolean checkPasswordLength(String password) {
        boolean flag = false;

        if (PwdCheckConfig.MAX_LENGTH <= 0) {
            if (password.length() >= PwdCheckConfig.MIN_LENGTH) {
                flag = true;
            }
        } else {
            if (password.length() >= PwdCheckConfig.MIN_LENGTH && password.length() <= PwdCheckConfig.MAX_LENGTH) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * @return 包含数字 返回true
     * @brief 检测密码中是否包含数字
     * @param[in] password            密码字符串
     */
    private static boolean checkContainDigit(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int num_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isDigit(chPass[i])) {
                num_count++;
            }
        }

        if (num_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return 包含字母 返回true
     * @brief 检测密码中是否包含字母（不区分大小写）
     * @param[in] password            密码字符串
     */
    private static boolean checkContainCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLetter(chPass[i])) {
                char_count++;
            }
        }

        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return 包含小写字母 返回true
     * @brief 检测密码中是否包含小写字母
     * @param[in] password            密码字符串
     */
    private static boolean checkContainLowerCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLowerCase(chPass[i])) {
                char_count++;
            }
        }

        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return 包含大写字母 返回true
     * @brief 检测密码中是否包含大写字母
     * @param[in] password            密码字符串
     */
    private static boolean checkContainUpperCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isUpperCase(chPass[i])) {
                char_count++;
            }
        }

        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return 包含特殊符号 返回true
     * @brief 检测密码中是否包含特殊符号
     * @param[in] password            密码字符串
     */
    private static boolean checkContainSpecialChar(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int special_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (PwdCheckConfig.SPECIAL_CHAR.indexOf(chPass[i]) != -1) {
                special_count++;
            }
        }

        if (special_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return 含有横向连续字符串 返回true
     * @brief 键盘规则匹配器 横向连续检测
     * @param[in] password            密码字符串
     */
    private static boolean checkLateralKeyboardSite(String password) {
        String t_password = new String(password);
        //将所有输入字符转为小写
        t_password = t_password.toLowerCase();
        int n = t_password.length();
        /**
         * 键盘横向规则检测
         */
        boolean flag = false;
        int arrLen = PwdCheckConfig.KEYBOARD_HORIZONTAL_ARR.length;
        int limit_num = PwdCheckConfig.LIMIT_HORIZONTAL_NUM_KEY;

        for (int i = 0; i + limit_num <= n; i++) {
            String str = t_password.substring(i, i + limit_num);
            String distinguishStr = password.substring(i, i + limit_num);

            for (int j = 0; j < arrLen; j++) {
                String configStr = PwdCheckConfig.KEYBOARD_HORIZONTAL_ARR[j];
                String revOrderStr = new StringBuffer(PwdCheckConfig.KEYBOARD_HORIZONTAL_ARR[j]).reverse().toString();

                //检测包含字母(区分大小写)
                if (PwdCheckConfig.CHECK_DISTINGGUISH_CASE) {
                    //考虑 大写键盘匹配的情况
                    String UpperStr = PwdCheckConfig.KEYBOARD_HORIZONTAL_ARR[j].toUpperCase();
                    if (configStr.contains(distinguishStr) || UpperStr.contains(distinguishStr)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                    if (revOrderStr.contains(distinguishStr) || revUpperStr.contains(distinguishStr)) {
                        flag = true;
                        return flag;
                    }
                } else {
                    if (configStr.contains(str)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    if (revOrderStr.contains(str)) {
                        flag = true;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @return 含有斜向连续字符串 返回true
     * @brief 键盘规则匹配器 斜向规则检测
     * @param[in] password            密码字符串
     */
    private static boolean checkKeyboardSlantSite(String password) {
        String t_password = new String(password);
        t_password = t_password.toLowerCase();
        int n = t_password.length();
        /**
         * 键盘斜线方向规则检测
         */
        boolean flag = false;
        int arrLen = PwdCheckConfig.KEYBOARD_SLOPE_ARR.length;
        int limit_num = PwdCheckConfig.LIMIT_SLOPE_NUM_KEY;

        for (int i = 0; i + limit_num <= n; i++) {
            String str = t_password.substring(i, i + limit_num);
            String distinguishStr = password.substring(i, i + limit_num);
            for (int j = 0; j < arrLen; j++) {
                String configStr = PwdCheckConfig.KEYBOARD_SLOPE_ARR[j];
                String revOrderStr = new StringBuffer(PwdCheckConfig.KEYBOARD_SLOPE_ARR[j]).reverse().toString();
                //检测包含字母(区分大小写)
                if (PwdCheckConfig.CHECK_DISTINGGUISH_CASE) {

                    //考虑 大写键盘匹配的情况
                    String UpperStr = PwdCheckConfig.KEYBOARD_SLOPE_ARR[j].toUpperCase();
                    if (configStr.contains(distinguishStr) || UpperStr.contains(distinguishStr)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                    if (revOrderStr.contains(distinguishStr) || revUpperStr.contains(distinguishStr)) {
                        flag = true;
                        return flag;
                    }
                } else {
                    if (configStr.contains(str)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    if (revOrderStr.contains(str)) {
                        flag = true;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @return 含有a-z,z-a连续字符串 返回true
     * @brief 评估a-z,z-a这样的连续字符
     * @param[in] password            密码字符串
     */
    private static boolean checkSequentialChars(String password) {
        String t_password = new String(password);
        boolean flag = false;
        int limit_num = PwdCheckConfig.LIMIT_LOGIC_NUM_CHAR;
        int normal_count = 0;
        int reversed_count = 0;

        //检测包含字母(区分大小写)
        if (PwdCheckConfig.CHECK_DISTINGGUISH_CASE) {

        } else {
            t_password = t_password.toLowerCase();
        }
        int n = t_password.length();
        char[] pwdCharArr = t_password.toCharArray();

        for (int i = 0; i + limit_num <= n; i++) {
            normal_count = 0;
            reversed_count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if (pwdCharArr[i + j + 1] - pwdCharArr[i + j] == 1) {
                    normal_count++;
                    if (normal_count == limit_num - 1) {
                        return true;
                    }
                }

                if (pwdCharArr[i + j] - pwdCharArr[i + j + 1] == 1) {
                    reversed_count++;
                    if (reversed_count == limit_num - 1) {
                        return true;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @return 含有aaaa, 1111等连续字符串 返回true
     * @brief 评估aaaa, 1111这样的相同连续字符
     * @param[in] password            密码字符串
     */
    private static boolean checkSequentialSameChars(String password) {
        String t_password = new String(password);
        int n = t_password.length();
        char[] pwdCharArr = t_password.toCharArray();
        boolean flag = false;
        int limit_num = PwdCheckConfig.LIMIT_NUM_SAME_CHAR;
        int count = 0;
        for (int i = 0; i + limit_num <= n; i++) {
            count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if (pwdCharArr[i + j] == pwdCharArr[i + j + 1]) {
                    count++;
                    if (count == limit_num - 1) {
                        return true;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 检测常用词库
     *
     * @param password
     * @return
     */
    private static boolean checkSimpleWord(String password) {
        List<String> simpleWords = Arrays.asList(PwdCheckConfig.SIMPLE_WORDS);
        return simpleWords.contains(password.toLowerCase());
    }


}