package com.zhq.util;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final int H_M_S_MS = 0;
    public static final int H_M_S = 1;
    public static final int H_M = 2;

    public static Date dateFromStr(String strDate) {
        // 使用多个重载的方式构建出默认函数的效果
        return dateFromStr(strDate, "yyyy-MM-dd HH:mm:ss");
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
     * @param start 起始时间
     * @param end 结束时间
     * @param second 比较是否包括秒
     * @param millisecond 比较是否包括毫秒
     * @return
     */
    public static boolean isBetweenTimes(Date start, Date end, boolean second, boolean millisecond) {
        Calendar nowCalendar = calendarWithDate(new Date());
        Calendar startCalendar = calendarWithDate(start);
        Calendar endCalendar = calendarWithDate(end);

        LocalTime nowTime = localTimeWithCalendar(nowCalendar, second, millisecond);
        LocalTime startTime = localTimeWithCalendar(startCalendar, second, millisecond);
        LocalTime endTime = localTimeWithCalendar(endCalendar, second, millisecond);

        return (nowTime.compareTo(startTime) >= 0 && nowTime.compareTo(endTime) <= 0);
    }

    public static boolean isBetweenTimes(Date start, Date end, int format) {
        switch (format) {
            case H_M_S:
                return isBetweenTimes(start, end, true, false);

            case H_M:
                return isBetweenTimes(start, end, false, false);

            default:
                return isBetweenTimes(start, end, true, true);
        }
    }

    public static boolean isBetweenTimes(Date start, Date end) {
        return isBetweenTimes(start, end, false, false);
    }

    /**
     * 判断当前时间是否在对应的时间段前 ( hh:mm:ss:SSS )
     * 默认包含相等的情况
     * @param date
     * @return
     */
    public static boolean isBeforeTimes_H_M_S_MS(Date date) {
        Calendar nowCalendar = calendarWithDate(new Date());
        Calendar dateCalendar = calendarWithDate(date);

        LocalTime nowTime = localTimeWithCalendar(nowCalendar, true, true);
        LocalTime dateTime = localTimeWithCalendar(dateCalendar, true, true);

        return (nowTime.compareTo(dateTime) <= 0);
    }


    /**
     * 判断当前时间是否在对应的时间段后 ( hh:mm:ss:SSS )
     * @param date
     * @return
     */
    public static boolean isAfterTimes_H_M_S_MS(Date date) {
        Calendar nowCalendar = calendarWithDate(new Date());
        Calendar dateCalendar = calendarWithDate(date);

        LocalTime nowTime = localTimeWithCalendar(nowCalendar, true, true);
        LocalTime dateTime = localTimeWithCalendar(dateCalendar, true, true);

        return (nowTime.compareTo(dateTime) >= 0);
    }


    /**
     * 返回对应日期时间的日历
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
     * 根据对应的日历返回对应的时间信息
     * @param calendar 传入的日历
     * @param second 是否需要比较秒 ( 默认不要 )
     * @param millisecond 是否需要比较毫秒 ( 默认不要 )
     * @return
     */
    public static LocalTime localTimeWithCalendar(Calendar calendar, boolean second, boolean millisecond) {
        if (second) {
            if (millisecond) {
                return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND),
                        calendar.get(Calendar.MILLISECOND));
            }
            else {
                return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND));
            }
        }
        else {
            return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE));
        }
    }

    public static LocalTime localTimeWithCalendar(Calendar calendar) {
        return localTimeWithCalendar(calendar, false, false);
    }

    public static LocalTime localTimeWithCalendar(Calendar calendar, boolean second) {
        return localTimeWithCalendar(calendar, second, false);
    }
}
