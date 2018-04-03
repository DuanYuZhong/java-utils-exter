package com.duanyu.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 基于新版LocalDateTime的时间工具类
 * @author forip on 2018/2/10.
 */
public class DateTimeUtils {

    /**
     * 获取指定时间的0点
     */
    public static Date getBeginTimeOfDay() {
        return toDate(LocalDate.now());
    }

    public static Date getBeginTimeOfDay(LocalDate localDate) {
        return toDate(localDate);
    }

    /**
     * 获取指定时间的23点59
     */
    public static Date getEndTimeOfDay() {
        return toDate(LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
    }

    public static Date getEndTimeOfDay(LocalDate localDate) {
        return toDate(LocalDateTime.of(localDate, LocalTime.MAX));
    }

    /**
     * Local 转 Date
     * @param ldt 需要转换的具体时间
     * @return 日期Date
     */
    public static Date toDate(LocalDateTime ldt) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = ldt.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * Local 转 Date
     * @param ld 需要转换的日期
     * @return 日期Date
     */
    public static Date toDate(LocalDate ld) {
        return toDate(LocalDateTime.of(ld, LocalTime.MIN));
    }

    /**
     * date 转 LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * date 转 LocalDate
     */
    public static LocalDate toLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * date 转 LocalTime
     */
    public static LocalTime toLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalTime();
    }

}
