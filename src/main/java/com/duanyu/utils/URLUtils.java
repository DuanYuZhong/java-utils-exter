package com.duanyu.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL工具类
 */
public class URLUtils {

    private URLUtils() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(URLUtils.class);

    private static final String UTF_8 = "utf-8";
    private static final String GBK = "gbk";
    private static final String IOS8859_1 = "iso8859-1";

    public static String encode(String s) {
        return encode(s, UTF_8);
    }

    public static String encode(String s, String enc) {
        if (org.apache.commons.lang3.StringUtils.isBlank(s)) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        }
        try {
            return URLEncoder.encode(s, enc);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("编码失败", e);
        }
        return "";
    }

    public static String decode(String s, String enc) {
        if (org.apache.commons.lang3.StringUtils.isBlank(s)) {
            return StringUtils.EMPTY;
        }
        try {
            return URLDecoder.decode(s, enc);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("解码失败", e);
        }
        return "";
    }

    public static String decode(String s) {
        return decode(s, UTF_8);
    }
}
