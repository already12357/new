package com.zhq.util;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    /**
     * 获取特定时间之前的时间内容
     * @param objDate 时间基准对象
     * @param calendarTimeBase 日历时间单位 ( 如 Calendar.HOUR_OF_DAY )
     * @param interval 时间间隔
     * @return
     */
    public static Date dateBeforeTimes(Date objDate, int calendarTimeBase, int interval) {
        Calendar calendar = calendarWithDate(objDate);
        calendar.add(calendarTimeBase, Math.negateExact(interval));
        return calendar.getTime();
    }

    public static Date nowBeforeTimes(int calendarTimeBase, int interval) {
        return dateBeforeTimes(new Date(), calendarTimeBase, interval);
    }


    /**
     * 获取特定时间之后的时间内容
     * @param objDate 时间基准对象
     * @param calendarTimeBase 日历时间单位 ( 如 Calendar.HOUR_OF_DAY )
     * @param interval 时间间隔
     * @return
     */
    public static Date dateAfterTimes(Date objDate, int calendarTimeBase, int interval) {
        Calendar calendar = calendarWithDate(objDate);
        calendar.add(calendarTimeBase, interval);
        return calendar.getTime();
    }

    public static Date nowAfterTimes(int calendarTimeBase, int interval) {
        return dateAfterTimes(new Date(), calendarTimeBase, interval);
    }


    /**
     * 根据提供的字符串格式, 解析为对应的日期
     * @param strDate 对应的日期字符串
     * @param format 解析的格式字符串
     * @return 返回解析成功的日期时间
     */
    public static Date dateFromStr(String strDate, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        Date parsedDate;

        try {
            simpleDateFormat.setLenient(false);
            synchronized (DateUtil.class) {
                parsedDate = simpleDateFormat.parse(strDate);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            parsedDate = null;
        }

        return parsedDate;
    }

    public static Date dateFromStr(String strDate) {
        // 使用多个重载的方式构建出默认函数的效果
        return dateFromStr(strDate, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 根据时间段获取对应的小时数
     * @param date 日期时间
     * @param calendarTimeBase Calendar 中的时间进制
     * @return
     */
    public static int getDateHour(Date date, int calendarTimeBase) {
        Calendar calendar = calendarWithDate(date);
        return calendar.get(calendarTimeBase);
    }

    public static int getDateHour(Date date) {
        return getDateHour(date, Calendar.HOUR_OF_DAY);
    }


    /**
     * 查看对应的日期对象的时间 (HH:mm:ss:SSS) 是否在对应的时间之间 ( 仅比较时间 )
     * @param objDate 需要判断的日期对象
     * @param start 比较的起始日期对象
     * @param end 比较的结束日期对象
     * @param timeUnit 比较的颗粒度
     * @return
     */
    public static boolean onlyTimeBetween(Date objDate, Date start, Date end, TimeUnit timeUnit) {
        Calendar nowCalendar = calendarWithDate(objDate);
        Calendar startCalendar = calendarWithDate(start);
        Calendar endCalendar = calendarWithDate(end);

        LocalTime nowTime = localTimeWithCalendar(nowCalendar, timeUnit);
        LocalTime startTime = localTimeWithCalendar(startCalendar, timeUnit);
        LocalTime endTime = localTimeWithCalendar(endCalendar, timeUnit);

        return (nowTime.compareTo(startTime) >= 0 && nowTime.compareTo(endTime) <= 0);
    }

    public static boolean onlyTimeBetween(Date start, Date end, TimeUnit timeUnit) {
        return onlyTimeBetween(new Date(), start, end, timeUnit);
    }


    /**
     * 判断传入日期中的时间, 是否在当前时间之后
     * @param objDate 当前判断的时间
     * @param target 需要对比的时间
     * @param timeUnit 比较时间的颗粒度 ( 分 / 秒 / 毫秒 )
     * @return
     */
    public static boolean onlyTimeAfter(Date objDate, Date target, TimeUnit timeUnit) {
        Calendar nowCalendar = calendarWithDate(objDate);
        Calendar targetCalendar = calendarWithDate(target);
        LocalTime nowTime = localTimeWithCalendar(nowCalendar, timeUnit);
        LocalTime targetTime = localTimeWithCalendar(targetCalendar, timeUnit);

        return (nowTime.compareTo(targetTime) > 0);
    }

    public static boolean onlyTimeAfter(Date target, TimeUnit timeUnit) {
        return onlyTimeAfter(new Date(), target,  timeUnit);
    }


    /**
     * 判断传入日期中的时间, 是否在当前时间之后
     * @param objDate 当前判断的时间
     * @param target 比较的时间对象
     * @param timeUnit 比较时间单位的颗粒度 ( 分 / 秒 / 毫秒 )
     * @return
     */
    public static boolean onlyTimeBefore(Date objDate, Date target, TimeUnit timeUnit) {
        Calendar nowCalendar = calendarWithDate(objDate);
        Calendar targetCalendar = calendarWithDate(target);
        LocalTime nowTime = localTimeWithCalendar(nowCalendar, timeUnit);
        LocalTime targetTime = localTimeWithCalendar(targetCalendar, timeUnit);

        return (nowTime.compareTo(targetTime) < 0);
    }

    public static boolean onlyTimeBefore(Date target, TimeUnit timeUnit) {
        return onlyTimeBefore(new Date(), target, timeUnit);
    }


    /**
     * 获取当前日期时间对应的日历
     * @param date 传入的日期
     * @return
     */
    public static Calendar calendarWithDate(Date date) {
        synchronized (Calendar.class) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }
    }


    /**
     * 根据日历返回当前的时间信息
     * @param calendar 传入的日历
     * @param timeUnit 返回时间的颗粒度 ( 精确到 秒 / 毫秒 / 分钟 )
     * @return
     */
    public static LocalTime localTimeWithCalendar(Calendar calendar, TimeUnit timeUnit) {
        switch (timeUnit) {
            case SECONDS:
                return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND));

            case MILLISECONDS:
                return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND),
                        calendar.get(Calendar.MILLISECOND));

            default:
                return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE));
        }
    }
}
