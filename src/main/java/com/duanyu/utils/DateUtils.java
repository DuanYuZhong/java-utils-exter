package com.duanyu.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private DateUtils() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式yyyy-MM-dd,这个格式固定，不要改
     */
    public static final String YMD_FORMAT = "yyyy-MM-dd";

    public static final String YM_FORMAT = "yyyy-MM";

    public static final String NO_SECOND_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String YMDHM_FORMAT = "yyyyMMddHHmm";

    public static long SENCOD_IN_MILLS = 1000L;
    public static long MINUTE_IN_MILLS = SENCOD_IN_MILLS * 60;
    public static long HOUR_IN_MILLS = MINUTE_IN_MILLS * 60;
    public static long DAY_IN_MILLS = HOUR_IN_MILLS * 24;

    private static final String[] PARSE_PATTERNS = new String[]{
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm",
            "yyyyMMdd",
            "yyyy-MM-dd",
            "yyyy-MM",
            "HH:mm",
            "MM/dd/yyyy HH:mm:ss",
            "MM/dd/yyyy",
            "yyyy/MM/dd"
            // 这里可以增加更多的日期格式，用得多的放在前面
    };

    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat(org.apache.commons.lang3.StringUtils.defaultIfEmpty(
                format, DEFAULT_FORMAT));
        return df.format(date);
    }

    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    }

    public static Date parse(String date) {
        if (Objects.isNull(date)) {
            return null;
        }
        try {
            return parseDate(date, PARSE_PATTERNS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse(String date, String format) {
        if (org.apache.commons.lang3.StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return parseDate(date, format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date defaultParse(String date) {
        return parse(date, DEFAULT_FORMAT);
    }

    /**
     * 判断是否是昨天的某个时间
     */
    public static boolean isYesterdaySomeTime(Date date) {
        return isSomeTimeInDay(date, -1);
    }

    public static boolean isSomeTimeInDay(Date date, int dayIndex) {
        if (date == null) {
            return false;
        }

        Date d2 = addDays(new Date(), dayIndex);
        return isSameDay(date, d2);
    }


    /**
     * 获取某一天及其前后几天的零点
     */
    public static Date getZeroTime(Date date, int n) {
        if (Objects.isNull(date)) {
            return null;
        }
        return addDays(truncate(date, Calendar.DAY_OF_MONTH), n);
    }

    /**
     * 判断两个日期是否是同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        return truncatedEquals(date1, date2, Calendar.DAY_OF_MONTH);
    }

    /**
     * @param type {@link Calendar#HOUR_OF_DAY} etc..
     */
    public static int timeBetween(Date early, Date late, int type) {
        long v = 1L;
        switch (type) {
            case Calendar.DAY_OF_MONTH:
                v = DAY_IN_MILLS;
                break;
            case Calendar.HOUR:
            case Calendar.HOUR_OF_DAY:
                v = HOUR_IN_MILLS;
                break;
            case Calendar.MINUTE:
                v = MINUTE_IN_MILLS;
                break;
            case Calendar.SECOND:
                v = SENCOD_IN_MILLS;
                break;
            case Calendar.MILLISECOND:
                // default 1
                break;
            default:
                throw new RuntimeException("Unsupported type of:" + type);
        }

        return (int) ((late.getTime() - early.getTime()) / v);
    }


    /**
     * 数据库定义 1 SOLAR 阳历; 2 LUNAR 阴历; 增加START 占位enum
     *
     * @author iacdp
     */
    public enum Type {
        START(""), SOLAR("阳历"), LUNAR("阴历");

        private String description;

        private Type(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    /**
     * 判断当前日期是星期几
     *
     * @param dateTime 要判断的时间
     * @return dayForWeek 判断结果
     */
    public static String dayForWeek(Date dateTime) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return String.valueOf(dayForWeek);
    }

    /**
     * 根据日期格式，返回指定日期指定格式转换后的字符串
     *
     * @param date    日期对象
     * @param pattern 指定转换格式
     * @return 格式化后的日期字符串
     */
    public static String getDate(Date date, String pattern) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (date != null) {
            df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     *
     * @param aMask   输入字符串的格式
     * @param strDate 一个按aMask格式排列的日期的字符串描述
     * @return Date 对象
     * @see java.text.SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return (date);
    }


    /**
     * 获取当前时间的毫秒
     *
     * @return 返回毫秒
     */
    public static long getMillis() {
        Date date = new Date();
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 获取当前日期所在月份的所有日期
     *
     * @param date 当前日期
     * @return List<Date>
     */
    public static List<Date> getAllTheDateOftheMonth(Date date) {
        List<Date> list = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        int month = cal.get(Calendar.MONTH);
        while (cal.get(Calendar.MONTH) == month) {
            list.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

    /**
     * 获取指定日期是周几
     *
     * @param date 指定日期
     * @return 指定日期对应的周几
     */
    public static String getDayOfWeekByDate(Date date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(myFormatter.format(date));
            SimpleDateFormat formatter = new SimpleDateFormat("E", Locale.CHINA);
            String str = formatter.format(myDate);
            dayOfweek = str.replace("星期", "周");

        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }

    /**
     * 比较两个日期大小
     *
     * @param date1 日期1
     * @param date2 日期2
     */
    public static int compareTo(Date date1, Date date2) {
        if (Objects.isNull(date1) || Objects.isNull(date2)) {
            return 0;
        }
        return truncatedCompareTo(date1, date2, Calendar.SECOND);
    }

    /**
     * 比较日期1是否小于日期2
     *
     * @param date1 日期1
     * @param date2 日期2
     */
    public static boolean lessThan(Date date1, Date date2) {
        return compareTo(date1, date2) == -1;
    }

    /**
     * 比较日期1是否大于日期2
     *
     * @param date1 日期1
     * @param date2 日期2
     */
    public static boolean moreThan(Date date1, Date date2) {
        return compareTo(date1, date2) == 1;
    }

    /**
     * 获取输入日期的23:59:59秒
     */
    public static Date getLastSecondByDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return addMilliseconds(truncate(date, Calendar.DAY_OF_MONTH), (int) DAY_IN_MILLS - 1);
    }

    /**
     * 获取当天的23:59:59
     */
    public static Date getLastSecondOfToDay() {
        return getLastSecondByDate(new Date());
    }

    /**
     * 获取输入日期的月份的第一天
     *
     * @param date date
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取两个时间差
     *
     * @param date1 时间一
     * @param date2 时间二
     * @param field 参数 天
     */
    public static long getDateDiff(Date date1, Date date2, int field) {
        if (Objects.isNull(date1) || Objects.isNull(date2)) {
            return -1;
        }
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diffTime = time1 - time2;
        long diff = diffTime > 0 ? diffTime : -diffTime;
        switch (field) {
            case Calendar.DAY_OF_YEAR:
                return diff / DAY_IN_MILLS;
            case Calendar.HOUR:
                return diff / HOUR_IN_MILLS;
            case Calendar.MINUTE:
                return diff / MINUTE_IN_MILLS;
            case Calendar.SECOND:
                return diff / SENCOD_IN_MILLS;
            default:
                return -1;
        }
    }

    public static long getNowDiff(Date date, int field) {
        return getDateDiff(date, new Date(), field);
    }

    /**
     * 当前日期加一天
     *
     * @param date 输入日期
     * @return 返回当前日期加一天
     */
    public static Date addOneDay(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return addDays(date, 1);
    }

    /**
     * 当前日期减一天
     *
     * @param date 输入日期
     * @return 返回当前日期减一天
     */
    public static Date minusOneDay(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return addDays(date, -1);
    }

}
