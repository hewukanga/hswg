/*
 * @(#)DateUtil.java 2014-1-10下午4:20:20
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 */
package com.jiumai.base.common.core.utils;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public class DateUtils {

    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String MM_DD_YYYY = "MM-dd-yyyy";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 获取当前时间
     * 
     * @return
     */
    public static String getNowTime() {
        return getNowTime(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前时间
     * 
     * @param format
     *            时间格式（如：yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String getNowTime(String format) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);// 可以方便地修改日期格式
        String dateString = dateFormat.format(now);
        return dateString;
    }

    /**
     * 获取今天日期
     * 
     * @return 返回格式yyyy-MM-dd
     */
    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        return DateFormat.getDateInstance().format(calendar.getTime());
    }

    /**
     * 获取当天零点时间
     * 
     * @return
     */
    public static Date getIntradayZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 小时
        calendar.set(Calendar.MINUTE, 0); // 分钟
        calendar.set(Calendar.SECOND, 0); // 秒
        calendar.set(Calendar.MILLISECOND, 0); // 毫秒

        return calendar.getTime();
    }

    /**
     * 获得当前年份
     * 
     * @return
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获得当前月份
     * 
     * @return
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 判断是否闰月，用于计算当前时间加上分钟后的时间
     * 
     * @param year
     *            年份
     * @return
     */
    public static boolean isLeapYear(int year) {
        // 能被100整除, 不能被400整除的年份, 不是闰年.
        // 能被100整除, 也能被400整除的年份, 是闰年.
        if ((year % 100) == 0) {
            return ((year % 400) == 0);
        } else {// 不能被100整除, 能被4整除的年份是闰年.
            return ((year % 4) == 0);
        }
    }

    /**
     * 获得今天在本年的第几天
     * 
     * @return
     */
    public static int getDayOfYear() {
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得今天在本月的第几天(获得当前日)
     * 
     * @return
     */
    public static int getDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得今天在本周的第几天
     * 
     * @return 星期天1，星期一2 ......星期六7
     */
    public static int getDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得今天是这个月的第几周
     * 
     * @return
     */
    public static int getWeekOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 获取当月第一天
     * 
     * @return
     */
    public static Date getFirstDayOfMonth() {
        return getFirstDayOfMonth(null);
    }

    /**
     * 获取指定日期的当月第一天
     *
     * @return
     */
    public static Date getFirstDayOfMonth(Date theDate) {
        Calendar calendar = Calendar.getInstance();
        if(theDate != null){
            calendar.setTime(theDate);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设为当月的1号
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 小时
        calendar.set(Calendar.MINUTE, 0); // 分钟
        calendar.set(Calendar.SECOND, 0); // 秒
        calendar.set(Calendar.MILLISECOND, 0); // 毫秒

        return calendar.getTime();
    }

    /**
     * 获取当月最后一天
     * 
     * @return
     */
    public static Date getLastDayOfMonth() {
        return getLastDayOfMonth(null);
    }

    /**
     * 获取指定日期的当月最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth(Date theDate) {
        Calendar calendar = Calendar.getInstance();
        if(theDate != null){
            calendar.setTime(theDate);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设为当月的1号
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 小时
        calendar.set(Calendar.MINUTE, 0); // 分钟
        calendar.set(Calendar.SECOND, 0); // 秒
        calendar.set(Calendar.MILLISECOND, 0); // 毫秒

        calendar.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        calendar.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

        return calendar.getTime();
    }

    /**
     * 计算上月第一天
     * 
     * @param format
     *            时间格式（如：yyyy-MM-dd）
     * @return
     */
    public static Date getFirstDayOfPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);// 设为当前月的1号
        calendar.add(Calendar.MONTH, -1);// 减一个月，变为上月的1号

        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设为当月的1号
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 小时
        calendar.set(Calendar.MINUTE, 0); // 分钟
        calendar.set(Calendar.SECOND, 0); // 秒
        calendar.set(Calendar.MILLISECOND, 0); // 毫秒

        return calendar.getTime();
    }

    /**
     * 计算上月最后一天
     * 
     * @param format
     *            时间格式（如：yyyy-MM-dd）
     * @return
     */
    public static Date getLastDayOfPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);// 设为当前月的1号
        calendar.add(Calendar.DATE, -1);// 减去一天，变为上月最后一天

        calendar.set(Calendar.HOUR_OF_DAY, 0); // 小时
        calendar.set(Calendar.MINUTE, 0); // 分钟
        calendar.set(Calendar.SECOND, 0); // 秒
        calendar.set(Calendar.MILLISECOND, 0); // 毫秒

        return calendar.getTime();
    }

    /**
     * 计算下月第一天
     * 
     * @param format
     *            时间格式（如：yyyy-MM-dd）
     * @return
     */
    public static String getFirstDayOfNextMonth(String format) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        return new SimpleDateFormat(format).format(lastDate.getTime());
    }

    /**
     * 计算下月最后一天
     * 
     * @param format
     *            时间格式（如：yyyy-MM-dd）
     * @return
     */
    public static String getLastDayOfNextMonth(String format) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 2); // 加2个月，变为下下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为下月最后一天
        return new SimpleDateFormat(format).format(lastDate.getTime());
    }

    /**
     * 某月第一天
     * 
     * @param num
     *            -1是上个月，1是下个月
     * @param format
     *            时间格式（如：yyyy-MM-dd）
     * @return
     */
    public static String getMonthFirst(int num, String format) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, num);// 加num个月，变为某月的1号
        String date = new SimpleDateFormat(format).format(lastDate.getTime());
        return date;
    }

    /**
     * 某月最后一天
     * 
     * @param num
     *            -1是上个月，1是下个月
     * @param format
     *            时间格式（如：yyyy-MM-dd）
     * @return
     */
    public static String getMonthEnd(int num, String format) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, num + 1);// 加num个月，变为某某月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为某月最后一天
        String date = new SimpleDateFormat(format).format(lastDate.getTime());
        return date;
    }

    /**
     * 获得本周一的日期对象
     * 
     * @return
     */
    public static Date getMondayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.WEEK_OF_MONTH, 0);

        calendar.set(Calendar.HOUR_OF_DAY, 0); // 小时
        calendar.set(Calendar.MINUTE, 0); // 分钟
        calendar.set(Calendar.SECOND, 0); // 秒
        calendar.set(Calendar.MILLISECOND, 0); // 毫秒

        return calendar.getTime();
    }

    /**
     * 获取本周日的日期对象
     * 
     * @return
     */
    public static Date getSundayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.WEEK_OF_MONTH, 0);

        calendar.set(Calendar.HOUR_OF_DAY, 0); // 小时
        calendar.set(Calendar.MINUTE, 0); // 分钟
        calendar.set(Calendar.SECOND, 0); // 秒
        calendar.set(Calendar.MILLISECOND, 0); // 毫秒

        return calendar.getTime();
    }

    /**
     * 获取上周一日期
     * 
     * @return yyyy-MM-dd
     */
    public static String getPreviousMonday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1 * 7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return DateFormat.getDateInstance().format(calendar.getTime());
    }

    /**
     * 获取上周日日期
     * 
     * @return yyyy-MM-dd
     */
    public static String getPreviousSunday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        return DateFormat.getDateInstance().format(calendar.getTime());
    }

    /**
     * 获取下周一日期
     * 
     * @return yyyy-MM-dd
     */
    public static String getNextMonday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1 * 7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return DateFormat.getDateInstance().format(calendar.getTime());
    }

    /**
     * 获取下周日日期
     * 
     * @return yyyy-MM-dd
     */
    public static String getNextSunday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.WEEK_OF_MONTH, 1);
        return DateFormat.getDateInstance().format(calendar.getTime());
    }

    /**
     * 获取相对当前周某周周一日期
     * 
     * @param num
     *            -1 上一周 0 本周 1 下一周
     * @param format
     *            时间格式（如：yyyy-MM-dd）
     * @return
     */
    public static String getWeekMonday(int num, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num * 7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * 获取相对当前周某周周日日期
     * 
     * @param num
     *            -1 上一周 0 本周 1 下一周
     * @param format
     *            时间格式（如：yyyy-MM-dd）
     * @return
     */
    public static String getWeekSunday(int num, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.WEEK_OF_MONTH, num);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     * 
     * @param strDate
     *            日期（格式：yyyy-MM-dd）
     * @return 格式如：星期四
     */
    public static String getWeek(String strDate) {
        // 再转换为时间
        Date date = new SimpleDateFormat(YYYY_MM_DD).parse(strDate, new ParsePosition(0));
        ;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 获得一个日期所在周的星期几的日期
     * 
     * @param strDate
     *            日期
     * @param num
     *            取值范围 0~6，0=星期日、1=星期一、2=星期二，依此类推
     * @param format
     *            "yyyy-MM-dd"
     * @return
     */
    public static String getWeek(String strDate, String num, String format) {
        Date date = getDateParse(strDate, YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if ("1".equals(num)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else if ("2".equals(num)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        } else if ("3".equals(num)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        } else if ("4".equals(num)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        } else if ("5".equals(num)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        } else if ("6".equals(num)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        } else if ("0".equals(num)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * 获得相隔几天后的的时间
     * 
     * @param num
     *            相隔天数
     * @param format
     *            时间格式（yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String addDays(int num, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num);
        String str = sdf.format(calendar.getTime());
        return str;
    }

    /**
     * 获得相隔几天后的的时间
     * 
     * @param num
     *            相隔天数
     * @param format
     *            时间格式（yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static Date addDays(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

    /**
     * 获得相隔几小时后的时间
     * 
     * @param num
     * @param format
     * @return
     */
    public static String addHour(int num, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, num);

        return sdf.format(calendar.getTime());
    }

    /**
     * 取得相对于当前时间增加天数/月数/年数后的日期.
     * 
     * @param field
     *            段,如"year","month","day","hour","minute","second"对大小写不敏感
     * @param num
     *            增量(减少用负数表示),如:5,-1
     * @return 格式化后的字符串 如"2000-02-01 21:10:12"
     */
    public static String addDate(String field, int num) {
        // 当前日期和前一天
        SimpleDateFormat dateformat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Calendar rightNow = Calendar.getInstance();
        java.util.Date dt = new java.util.Date();
        rightNow.setTime(dt);

        String tmpField = field.toUpperCase().trim();

        int intField = Calendar.DATE;
        if (tmpField.equals("YEAR")) {
            intField = Calendar.YEAR;
        } else if (tmpField.equals("MONTH")) {
            intField = Calendar.MONTH;
        } else if (tmpField.equals("DAY")) {
            intField = Calendar.DAY_OF_MONTH;
        } else if (tmpField.equals("HOUR")) {
            intField = Calendar.HOUR_OF_DAY;
        } else if (tmpField.equals("MINUTE")) {
            intField = Calendar.MINUTE;
        } else if (tmpField.equals("SECOND")) {
            intField = Calendar.SECOND;
        }

        rightNow.add(intField, num);
        String day = dateformat.format(rightNow.getTime());
        return day;
    }

    /**
     * 将日期转换成字符串
     * 
     * @param dateTime
     *            日期
     * @param format
     *            转换格式
     * @return
     */
    public static String convertDateToString(Date dateTime, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(dateTime);
    }

    /**
     * 字符串转时间格式
     * 
     * @param strDate
     *            时间字符串
     * @param format
     *            转换格式
     * @return
     */
    public static Date getDateParse(String strDate, String format) {
        return new SimpleDateFormat(format).parse(strDate, new ParsePosition(0));
    }

    /**
     * 两个时间相差的天数
     * 
     * @param startDate 起始时间
     * @param endDate 结束时间
     * @return
     */
    public static long getDiffDate(String startDate, String endDate) {
        if (startDate == null || startDate.equals("")) {
            return 0;
        }
        if (endDate == null || endDate.equals("")) {
            return 0;
        }
        // 转换为标准时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateBegin = null;
        java.util.Date dateEnd = null;
        try {
            dateBegin = df.parse(startDate);
            dateEnd = df.parse(endDate);
            return getDiffDate(dateBegin,dateEnd);
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getDiffDate(Date dateBegin, Date dateEnd) {
        return (dateEnd.getTime() - dateBegin.getTime()) / (24 * 3600 * 1000);
    }

    /**
     * 两个字符时间相隔的天数
     * 
     * @param startDate
     *            起始时间
     * @param endDate
     *            结束时间
     * @return
     */
    public static long getSpanDays(String startDate, String endDate) {
        long spanDay = Math.abs(getDiffDate(startDate, endDate));
        return spanDay;
    }

    /**
     * 比较两个时间的大小 strDate1 > strDate2 返回true
     * 
     * @param strDate1
     * @param strDate2
     * @param format
     *            时间格式
     * @return
     */
    public static boolean getTimeStepSize(String strDate1, String strDate2, String format) {
        Date date1 = getDateParse(strDate1, format);
        Date date2 = getDateParse(strDate2, format);
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        return time1 > time2 ? true : false;
    }

    /**
     * 时间相隔月数
     * 
     * @param date1
     * @param date2
     * @return
     * @throws Exception
     */
    public static int getMonthSpace(Date date1, Date date2) {
        int result = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);

        int startYear = c1.get(Calendar.YEAR);
        int startMonth = c1.get(Calendar.MONTH);
        int endYear = c2.get(Calendar.YEAR);
        int endMonth = c2.get(Calendar.MONTH);
        result = (endYear - startYear) * 12 + endMonth - startMonth;

        return result = Math.abs(result);
    }

    /**
     * 把结束时间末尾替换成23：59：59
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static Date addEndTime(Date date) throws Exception {
        if (CommonFuntions.isNotEmptyObject(date)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return calendar.getTime();
        }
        return date;
    }

    public static String getMonday(String date) {
        if (date == null || date.equals("")) {
            System.out.println("date is null or empty");
            return "0000-00-00 00:00:00";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try  {
            d = format.parse(date);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        //set the first day of the week is Monday
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        return format.format(cal.getTime())+ " 00:00:00";
    }
    /**
     * 测试
     * 
     * @param args
     */
    public static void main(String[] args) {
        // DateTimeUtil tt = new DateTimeUtil();
        String monday = DateUtils.getMonday("2019-08-09");
        System.out.println(monday);
//        System.out.println("获取当天日期:" + DateUtils.getNowTime(DateUtils.YYYY_MM_DD_HH_MM_SS));
//        System.out.println("获取今天日期:" + DateUtils.getToday());
//        System.out.println("获取当前年份：" + DateUtils.getYear());
//        System.out.println("获取当前月份：" + DateUtils.getMonth());
//        System.out.println("获取今天在本年的第几天：" + DateUtils.getDayOfYear());
//        System.out.println("获得今天在本月的第几天(获得当前日)：" + DateUtils.getDayOfMonth());
//        System.out.println("获得今天在本周的第几天：" + DateUtils.getDayOfWeek());
//        System.out.println("获取当前月的第几周：" + DateUtils.getWeekOfMonth());
//        System.out.println("获取下月第一天日期:" + DateUtils.getFirstDayOfNextMonth("yyyy-MM-dd"));
//        System.out.println("获取下月最后一天日期:" + DateUtils.getLastDayOfNextMonth("yyyy-MM-dd"));
//        System.out.println("获取某月第一天日期:" + DateUtils.getMonthFirst(0, "yyyy-MM-dd"));
//        System.out.println("获取某月最后一天的日期:" + DateUtils.getMonthEnd(0, "yyyy-MM-dd"));
//        System.out.println("获取上周一的日期:" + DateUtils.getPreviousMonday());
//        System.out.println("获取上周日的日期:" + DateUtils.getPreviousSunday());
//        System.out.println("获取下周一的日期:" + DateUtils.getNextMonday());
//        System.out.println("获取下周日的日期:" + DateUtils.getNextSunday());
//        System.out.println("获取上周一的日期:" + DateUtils.getWeekMonday(-1, "yyyy-MM-dd"));
//        System.out.println("获取上周日的日期:" + DateUtils.getWeekSunday(-1, "yyyy-MM-dd"));
//        System.out.println("2012-10-25是星期几：" + DateUtils.getWeek("2012-10-25"));
//        System.out.println("获得一个日期所在周的星期几的日期:" + DateUtils.getWeek("2012-10-25", "0", DateUtils.YYYY_MM_DD_HH_MM_SS));
//        System.out.println("添加日期天数:" + DateUtils.addDays(2, "yyyy-MM-dd HH:mm:ss"));
//        System.out.println("获得新日期:" + DateUtils.addDate("month", -2));
//        System.out.println("将日期转换成字符串:" + DateUtils.convertDateToString(new Date(), "yyyy-MM-dd"));
//        System.out.println("字符串转时间:" + DateUtils.getDateParse("2012-10-25", "yyyy-MM-dd"));
//        System.out.println("两个时间相差的天数2012-10-25~2012-10-27: " + DateUtils.getDiffDate("2012-10-25", "2012-10-27"));
//        System.out.println("获取两个日期间隔天数2012-10-25~2012-10-27: " + DateUtils.getSpanDays("2012-10-25", "2012-10-27"));
//        System.out.println(DateUtils.getTimeStepSize("2012-10-25 12:25:50", "2012-10-25 13:00:50",
//                "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(getMonthSpace(getLastDayOfMonth(), getFirstDayOfPreviousMonth()));
//        System.out.println(getIntradayZeroTime());
//        System.out.println(addDays(-15));
    }

}
