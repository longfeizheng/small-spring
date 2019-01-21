package com.niocoder.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author zhangxuan01
 * 2018-06-13
 */
public class DateUtil {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    private static final Calendar CALENDAR = Calendar.getInstance();


    /**
     * 获取当前时间的字符串
     * 格式:yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNowTimeStr() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * 获取当前日期的字符串
     * 格式:yyyy-MM-dd
     *
     * @return
     */
    public static String getNowDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * 获取一个date的日期字符串
     *
     * @param date
     * @return
     */
    public static String getDateStrByDate(Date date) {
        if (null == date) {
            return "";
        }
        return dateToLocalDate(date).format(DATE_FORMATTER);
    }

    /**
     * 获取一个date的日期字符串
     *
     * @param date
     * @return
     */
    public static String getDateTimerStrByDate(Date date) {
        if (null == date) {
            return "";
        }
        return dateToLocalDateTime(date).format(DATETIME_FORMATTER);
    }

    /**
     * 获得当月1号零时零分零秒
     *
     * @return
     */
    public static Date initDateByMonth() {
        CALENDAR.setTime(new Date());
        CALENDAR.set(Calendar.DAY_OF_MONTH, 1);
        CALENDAR.set(Calendar.HOUR_OF_DAY, 0);
        CALENDAR.set(Calendar.MINUTE, 0);
        CALENDAR.set(Calendar.SECOND, 0);
        return CALENDAR.getTime();
    }

    /**
     * 获得当天1号零时零分零秒
     *
     * @return
     */
    public static Date initDateByDay() {
        CALENDAR.setTime(new Date());
        CALENDAR.set(Calendar.HOUR_OF_DAY, 0);
        CALENDAR.set(Calendar.MINUTE, 0);
        CALENDAR.set(Calendar.SECOND, 0);
        return CALENDAR.getTime();
    }

    public static String getTimeByFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getTimeStrByDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN);
        return sdf.format(date);
    }

    public static Date getTimeByStr(String str) {
        return localDateTimeToDate(LocalDateTime.parse(str, DATETIME_FORMATTER));
    }

    /**
     * @param str 2018-12-13 00:00:00
     * @return 2018-12-13
     */
    public static String getYMDByStr(String str) {
        if (null != str && str.length() > 10) {
            return str.substring(0, 10);
        }
        return "";
    }

    public static Date getDateBySr(String str) {
        return localDateToDate(LocalDate.parse(str, DATE_FORMATTER));
    }

    public static Date getDateBySr(String str, DateTimeFormatter formatter) {
        return localDateToDate(LocalDate.parse(str, formatter));
    }


    public static String getFirstDayOfMonth() {
        LocalDate now = LocalDate.now();
        return now.with(TemporalAdjusters.firstDayOfMonth()).format(DATE_FORMATTER);
    }

    public static String getLastDayOfMonth() {
        LocalDate now = LocalDate.now();
        return now.with(TemporalAdjusters.lastDayOfMonth()).format(DATE_FORMATTER);
    }

    /**
     * Date 转 LocalDate
     *
     * @return LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZONE_ID).toLocalDate();
    }

    /**
     * Date 转 LocalDateTime
     *
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZONE_ID).toLocalDateTime();
    }

    /**
     * LocalDate 转 Date
     *
     * @return LocalDate
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZONE_ID);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDateTime 转 Date
     *
     * @return LocalDateTime
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZONE_ID);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 当前日期加n天
     *
     * @param date Date类型 当前日期
     * @return Date类型 加n天后的日期
     */
    public static Date plusDays(Date date, long days) {
        return localDateToDate(dateToLocalDate(date).plusDays(days));
    }

    /**
     * 当前日期减n天
     *
     * @param date Date类型 当前日期
     * @return Date类型 减n天后的日期
     */
    public static Date minusDays(Date date, long days) {
        return localDateToDate(dateToLocalDate(date).minusDays(days));
    }

    /**
     * 计算两日期相差天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return endDate - startDate
     */
    public static int compareTo(Date startDate, Date endDate) {
        LocalDate startLocalDate = dateToLocalDate(startDate);
        LocalDate endLocalDate = dateToLocalDate(endDate);
        Long days = endLocalDate.toEpochDay() - startLocalDate.toEpochDay();
        return days.intValue();
    }

    /**
     * 计算两日期相差天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return endDate - startDate
     */
    public static int compareTo(String startDate, String endDate) {
        LocalDate startLocalDate = LocalDate.parse(startDate, DATE_FORMATTER);
        LocalDate endLocalDate = LocalDate.parse(endDate, DATE_FORMATTER);
        Long days = endLocalDate.toEpochDay() - startLocalDate.toEpochDay();
        return days.intValue();
    }


    public static int getDaysBetweenttwoDays(Date start, Date end) {
        if (start == null || end == null)
            return 0;
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();

        calst.setTime(start);
        caled.setTime(end);

        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 2); // use 2，避免差一点 如（0.9999999999 被认为是0）
        // 得到两个日期相差的天数
//		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
        long daysLong = (caled.getTime().getTime() - calst.getTime().getTime()) / (1000 * 3600 * 24);
        int days = (int) daysLong;
        return days;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getYMDByStr("2018-12-13 00:00:00"));
    }
}
