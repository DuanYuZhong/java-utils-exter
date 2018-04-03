package com.duanyu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
    private ValidateUtils() {}

    private static final String PHONE = "^1\\d{10}$";
    private static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final String NUM = "^[0-9]*$|^(-?\\d+)(\\.\\d+)?$";
    private static final String ID_CORD = "(^\\d{15})|(\\d{}18$)";
    private static final String CN = "^[\\u4e00-\\u9fa5]{0,}$";
    private static final String EN = "^[A-Za-z]+$";
    private static final String URL = "((http[s]{0,1}://)|(HTTP[S]{0,1}://))[_a-zA-Z0-9\\\\:\\\\.\\\\/]*";


    /**
     * 手机号校验(弱)
     * @param content 输入的字符串
     * @return 校验是否成功
     */
    public static boolean isPhone(String content) {
        return validate(content, PHONE);
    }

    /**
     * 邮箱校验
     * @param content 输入的字符串
     * @return 校验是否成功
     */
    public static boolean isEmail(String content) {
        return validate(content, EMAIL);
    }

    /**
     * 数字校验
     * @param content 输入的字符串
     * @return 校验是否成功
     */
    public static boolean isNum(String content) {
        return validate(content, NUM);
    }

    /**
     * 身份证校验
     * @param content 输入的字符串
     * @return 校验是否成功
     */
    public static boolean isIdCard(String content) {
        return validate(content, ID_CORD);
    }

    /**
     * 汉字校验
     * @param content 输入的字符串
     * @return 校验是否成功
     */
    public static boolean isCN(String content) {
        return validate(content, CN);
    }

    /**
     * 英文字母校验
     * @param content 输入的字符串
     * @return 校验是否成功
     */
    public static boolean isEn(String content) {
        return validate(content, EN);
    }

    /**
     * http请求地址校验
     * @param content 输入的字符串
     * @return 校验是否成功
     */
    public static boolean isURL(String content) {
        return validate(content, URL);
    }

    private static boolean validate(String content, String reg) {
        if (StringUtils.isBlank(content)) {
            return false;
        }
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }

}
