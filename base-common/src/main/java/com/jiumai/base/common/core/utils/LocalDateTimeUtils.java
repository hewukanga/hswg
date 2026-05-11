package com.jiumai.base.common.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
import java.util.Date;

/**
 * 时间处理工具类
 * @author wang jian
 * @date 2022-01-06
 * @version 1.0
 */
@Slf4j
public class LocalDateTimeUtils {


    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String MM_DD_YYYY = "MM-dd-yyyy";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);

    /**
     * 获取指定时间的指定格式
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String formatTime(LocalDateTime localDateTime,String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     * @param pattern
     * @return
     */
    public static String formatNow(String pattern) {
        return  formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 获取指定日期所属周的周一的日期
     * @param localDate
     * @return
     */
    public static LocalDateTime getMondayForThisWeek(LocalDate localDate) {
        LocalDateTime monday = LocalDateTime.of(localDate, LocalTime.MIN).with(DayOfWeek.MONDAY);
        return monday;
    }

    /**
     * 获取指定日期所属周的周日的日期
     * @param localDate
     * @return
     */
    public static LocalDateTime getSundayForThisWeek(LocalDate localDate) {
        LocalDateTime sunday = LocalDateTime.of(localDate, LocalTime.MIN).with(DayOfWeek.SUNDAY);
        return sunday;
    }

    /**
     * 获取指定日期所属周的下周一的日期
     * @param localDate
     * @return
     */
    public static LocalDateTime getMondayForNextWeek(LocalDate localDate) {
        LocalDateTime monday = LocalDateTime.of(localDate, LocalTime.MIN).plusWeeks(1).with(DayOfWeek.MONDAY);
        return monday;
    }

    /**
     * 获取指定日期所属周的下周日的日期
     * @param localDate
     * @return
     */
    public static LocalDateTime getSundayForNextWeek(LocalDate localDate) {
        LocalDateTime sunday = LocalDateTime.of(localDate, LocalTime.MIN).plusWeeks(1).with(DayOfWeek.SUNDAY);
        return sunday;
    }

    /**
     * 指定格式为的字符串时间转化为LocalDateTime类型
     * @param dateStr 时间字符串
     * @param pattern 格式
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromString(String dateStr,String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        return localDateTime;
    }

    /**
     * LocalDateTime类型转化为格式为指定格式的字符串时间类型
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String getStringFromLocalDateTime(LocalDateTime localDateTime,String pattern) {
        String localDateTimeStr = localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        return localDateTimeStr;
    }

    /**
     * Date类型时间转化为LocalDateTime类型
     * @param date
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromDate(Date date) {
        LocalDateTime localDateTime = date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        return localDateTime;
    }

    /**
     * LocalDateTime类型转化为Date类型时间
     * @param localDateTime
     * @return
     */
    public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        Date date = Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
        return date;
    }

    /**
     * 获取指定时间的00:00:00
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getLocalDateTimeForBegin(LocalDateTime localDateTime) {
        LocalDateTime begin = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
        return begin;
    }

    /**
     * 获取指定时间的23:59:59
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getLocalDateTimeForEnd(LocalDateTime localDateTime) {
        LocalDateTime end = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
        return end;
    }

    /**
     * 时间戳(毫秒)转化为LocalDateTime格式
     * @param timestamp
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromTimestamp(Long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp/1000, 0, ZoneOffset.ofHours(8));
        return localDateTime;
    }

    /**
     * LocalDateTime格式转化为时间戳(毫秒)
     * @param localDateTime
     * @return
     */
    public static Long getTimestampFromLocalDateTime(LocalDateTime localDateTime) {
        Long timestamp = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return timestamp;
    }


    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     * @param localDateTime
     * @param number
     * @param field 时间单位
     * "nanos" : 纳秒
     * "Micros" : 微秒
     * "Millis" : 毫秒
     * "Seconds" : 秒
     * "Minutes" : 分钟
     * "Hours" : 小时
     * "HalfDays" : 半天
     * "Days" : 天
     * "Weeks" : 星期
     * "Months" : 月
     * "Years" : 年
     * "Decades" : 十年
     * "Centuries" : 世纪
     * "Millennia" : 千年
     * "Eras" : 十亿年
     * "Forever" : 永远
     * @return
     */
    public static LocalDateTime plus(LocalDateTime localDateTime, long number, ChronoUnit field) {
        return localDateTime.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     * @param localDateTime 时间
     * @param number 值
     * @param field 时间单位
     * "nanos" : 纳秒
     * "Micros" : 微秒
     * "Millis" : 毫秒
     * "Seconds" : 秒
     * "Minutes" : 分钟
     * "Hours" : 小时
     * "HalfDays" : 半天
     * "Days" : 天
     * "Weeks" : 星期
     * "Months" : 月
     * "Years" : 年
     * "Decades" : 十年
     * "Centuries" : 世纪
     * "Millennia" : 千年
     * "Eras" : 十亿年
     * "Forever" : 永远
     * @return
     */
    public static LocalDateTime minu(LocalDateTime localDateTime, long number, ChronoUnit field){
        return localDateTime.minus(number,field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     * @param startTime
     * @param endTime
     * @param field  时间单位
     * "nanos" : 纳秒
     * "Micros" : 微秒
     * "Millis" : 毫秒
     * "Seconds" : 秒
     * "Minutes" : 分钟
     * "Hours" : 小时
     * "HalfDays" : 半天
     * "Days" : 天
     * "Weeks" : 星期
     * "Months" : 月
     * "Years" : 年
     * "Decades" : 十年
     * "Centuries" : 世纪
     * "Millennia" : 千年
     * "Eras" : 十亿年
     * "Forever" : 永远
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取指定日期为当年的第几周
     * @param localDateTime
     * @return
     */
    public static int weekOfYear(LocalDateTime localDateTime){

        return localDateTime.get(weekFields.weekOfYear());
    }

    /**
     * 获取指定日志为当年的第几天
     * @param localDateTime
     * @return
     */
    public static int dayOfWeek(LocalDateTime localDateTime){
        return localDateTime.get(weekFields.dayOfWeek());
    }

    /**
     * 获取指定日期为当月的第几周
     * @param localDateTime
     * @return
     */
    public static int weekOfMonth(LocalDateTime localDateTime){
        return localDateTime.get(weekFields.weekOfMonth());
    }


    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        LocalDateTime minu = LocalDateTimeUtils.minu(LocalDateTime.now(), -1, ChronoUnit.HALF_DAYS);
        log.info("输出为={}",LocalDateTimeUtils.formatTime(minu,YYYY_MM_DD_HH_MM_SS));

        LocalDateTime start = LocalDateTimeUtils.getLocalDateTimeFromString("1993-10-13 11:11", YYYY_MM_DD_HH_MM);
        LocalDateTime end = LocalDateTimeUtils.getLocalDateTimeFromString("1994-11-13 13:13", YYYY_MM_DD_HH_MM);
        log.info("年:{}" , LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.YEARS));
        log.info("月:{}" , LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MONTHS));
        log.info("日:{}" , LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.DAYS));
        log.info("半日:{}" , LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.HALF_DAYS));
        log.info("小时:{}" , LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.HOURS));
        log.info("分钟:{}" , LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MINUTES));
        log.info("秒:{}" , LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.SECONDS));
        log.info("毫秒:{}" , LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MILLIS));

    }
}
