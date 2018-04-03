package com.duanyu.utils;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 */
public class StringUtils extends  org.apache.commons.lang3.StringUtils {
    private StringUtils() {}

    /**
     * 转换sql
     * @param content 输入
     * @return 输出
     */
    public static String escapeSQL(String content){
        if(content == null || "".equals(content.trim())){
            return content;
        }
        return content.replaceAll("'", "''")
            .replaceAll("/", "//")
            .replaceAll("%", "/%")
            .replaceAll("_", "/_")
            .replaceAll("&", "' || chr(38) || '");
    }

    /**
     * 将字符串转成集合
     * @param string 字符串
     * @param split 分隔符号
     * @return 集合
     */
    public static List<String> stringtoList(String string, String split) {
        if (isBlank(string) || isBlank(split)) {
            return null;
        }
        return Lists.newArrayList(string.split(split));
    }

    /**
     * String转int集合
     * @param s 字符串
     * @param split 分隔符
     * @return 集合
     */
    public static List<Integer> stringToIntList(String s,  String split) {
        List<Integer> list = new ArrayList<>();
        if (isBlank(s)) {
            return null;
        }
        String[] strings = s.split(split);
        for (String i : strings) {
            list.add(Integer.parseInt(i));
        }
        return list;
    }

    /**
     * String转long集合
     * @param s 字符串
     * @param split 分隔符
     * @return 集合
     */
    public static List<Long> stringToLongList(String s,  String split) {
        List<Long> list = new ArrayList<>();
        if (isBlank(s)) {
            return null;
        }
        String[] strings = s.split(split);
        for (String i : strings) {
            list.add(Long.parseLong(i));
        }
        return list;
    }
}
