package com.zhq.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
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
            parsedDate = simpleDateFormat.parse(strDate);
        }
        catch (Exception e) {
            e.printStackTrace();
            parsedDate = null;
        }

        return parsedDate;
    }

    /**
     * 获取对应日期的小时 ( 24 )
     * @param date
     * @return
     */
    public static int getHour_24(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取对应日期的小时 ( 12 )
     * @param date
     * @return
     */
    public static int getHour_12(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }


    /**
     * 判断当前时间是否在对应的时间段内 ( hh:mm:ss:SSS )
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenTimes_H_M_S_MS(Date start, Date end) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        startCalendar.setTime(start);
        endCalendar.setTime(end);

        // 满足条件时
        if (nowCalendar.get(Calendar.HOUR_OF_DAY) <= endCalendar.get(Calendar.HOUR_OF_DAY) &&
                nowCalendar.get(Calendar.HOUR_OF_DAY) >= startCalendar.get(Calendar.HOUR_OF_DAY)) {
            // 不用判断分钟的情况
            if (nowCalendar.get(Calendar.HOUR_OF_DAY) < endCalendar.get(Calendar.HOUR_OF_DAY) &&
                    nowCalendar.get(Calendar.HOUR_OF_DAY) > startCalendar.get(Calendar.HOUR_OF_DAY)) {
                return true;
            }
            // 需要判断分钟的三种情况
            if (nowCalendar.get(Calendar.HOUR_OF_DAY) == endCalendar.get(Calendar.HOUR_OF_DAY) &&
                    nowCalendar.get(Calendar.HOUR_OF_DAY) == startCalendar.get(Calendar.HOUR_OF_DAY)) {
                return isBetweenTimes_M_S_MS(start, end);
            }
            if (nowCalendar.get(Calendar.HOUR_OF_DAY) < endCalendar.get(Calendar.HOUR_OF_DAY) &&
                    nowCalendar.get(Calendar.HOUR_OF_DAY) == startCalendar.get(Calendar.HOUR_OF_DAY)) {
                return isAfterTimes_M_S_MS(start);
            }
            if  (nowCalendar.get(Calendar.HOUR_OF_DAY) == endCalendar.get(Calendar.HOUR_OF_DAY) &&
                    nowCalendar.get(Calendar.HOUR_OF_DAY) > startCalendar.get(Calendar.HOUR_OF_DAY)) {
                return isBeforeTimes_M_S_MS(end);
            }
        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段前 ( hh:mm:ss:SSS )
     * @param date
     * @return
     */
    public static boolean isBeforeTimes_H_M_S_MS(Date date) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        dateCalendar.setTime(date);

        // 可能满足条件的情况
        if (nowCalendar.get(Calendar.HOUR_OF_DAY) <= dateCalendar.get(Calendar.HOUR_OF_DAY)) {
            if (nowCalendar.get(Calendar.HOUR_OF_DAY) == dateCalendar.get(Calendar.HOUR_OF_DAY)) {
                return isBeforeTimes_M_S_MS(date);
            }
            else {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段后 ( hh:mm:ss:SSS )
     * @param date
     * @return
     */
    public static boolean isAfterTimes_H_M_S_MS(Date date) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        dateCalendar.setTime(date);

        // 可能满足条件的情况
        if (nowCalendar.get(Calendar.HOUR_OF_DAY) >= dateCalendar.get(Calendar.HOUR_OF_DAY)) {
            if (nowCalendar.get(Calendar.HOUR_OF_DAY) == dateCalendar.get(Calendar.HOUR_OF_DAY)) {
                return isAfterTimes_M_S_MS(date);
            }
            else {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段内 ( mm:ss:SSS )
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenTimes_M_S_MS(Date start, Date end) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        startCalendar.setTime(start);
        endCalendar.setTime(end);

        // 满足条件时
        if (nowCalendar.get(Calendar.MINUTE) <= endCalendar.get(Calendar.MINUTE) &&
                nowCalendar.get(Calendar.MINUTE) >= startCalendar.get(Calendar.MINUTE)) {
            // 不用判断毫秒的情况
            if (nowCalendar.get(Calendar.MINUTE) < endCalendar.get(Calendar.MINUTE) &&
                    nowCalendar.get(Calendar.MINUTE) > startCalendar.get(Calendar.MINUTE)) {
                return true;
            }
            // 需要判断分钟的三种情况
            if (nowCalendar.get(Calendar.MINUTE) == endCalendar.get(Calendar.MINUTE) &&
                    nowCalendar.get(Calendar.MINUTE) == startCalendar.get(Calendar.MINUTE)) {
                return isBetweenTimes_S_MS(start, end);
            }
            if (nowCalendar.get(Calendar.MINUTE) < endCalendar.get(Calendar.MINUTE) &&
                    nowCalendar.get(Calendar.MINUTE) == startCalendar.get(Calendar.MINUTE)) {
                return isAfterTimes_S_MS(start);
            }
            if  (nowCalendar.get(Calendar.MINUTE) == endCalendar.get(Calendar.MINUTE) &&
                    nowCalendar.get(Calendar.MINUTE) > startCalendar.get(Calendar.MINUTE)) {
                return isBeforeTimes_S_MS(end);
            }
        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段前 ( mm:ss:SSS )
     * @param date
     * @return
     */
    public static boolean isBeforeTimes_M_S_MS(Date date) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        dateCalendar.setTime(date);

        // 满足条件时
        if (nowCalendar.get(Calendar.MINUTE) <= dateCalendar.get(Calendar.MINUTE)) {
            if (nowCalendar.get(Calendar.MINUTE) == dateCalendar.get(Calendar.MINUTE)) {
                return isBeforeTimes_S_MS(date);
            }
            else {
                return false;
            }
        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段后 ( mm:ss:SSS )
     * @param date
     * @return
     */
    public static boolean isAfterTimes_M_S_MS(Date date) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        dateCalendar.setTime(date);

        // 满足条件时
        if (nowCalendar.get(Calendar.MINUTE) >= dateCalendar.get(Calendar.MINUTE)) {
            if (nowCalendar.get(Calendar.MINUTE) == dateCalendar.get(Calendar.MINUTE)) {
                return isAfterTimes_S_MS(date);
            }
            else {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段内 ( ss:SSS )
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenTimes_S_MS(Date start, Date end) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        startCalendar.setTime(start);
        endCalendar.setTime(end);

        // 满足条件时
        if (nowCalendar.get(Calendar.SECOND) <= endCalendar.get(Calendar.SECOND) &&
                nowCalendar.get(Calendar.SECOND) >= startCalendar.get(Calendar.SECOND)) {
            // 不用判断毫秒的情况
            if (nowCalendar.get(Calendar.SECOND) < endCalendar.get(Calendar.SECOND) &&
                    nowCalendar.get(Calendar.SECOND) > startCalendar.get(Calendar.SECOND)) {
                return true;
            }
            // 需要判断分钟的三种情况
            if (nowCalendar.get(Calendar.SECOND) == endCalendar.get(Calendar.SECOND) &&
                    nowCalendar.get(Calendar.SECOND) == startCalendar.get(Calendar.SECOND)) {
                return isBetweenTimes_MS(start, end);
            }
            if (nowCalendar.get(Calendar.SECOND) < endCalendar.get(Calendar.SECOND) &&
                    nowCalendar.get(Calendar.SECOND) == startCalendar.get(Calendar.SECOND)) {
                return isAfterTimes_MS(start);
            }
            if  (nowCalendar.get(Calendar.SECOND) == endCalendar.get(Calendar.SECOND) &&
                    nowCalendar.get(Calendar.SECOND) > startCalendar.get(Calendar.SECOND)) {
                return isBeforeTimes_MS(end);
            }
        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段前 ( ss:SSS )
     * @param date
     * @return
     */
    public static boolean isBeforeTimes_S_MS(Date date) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        dateCalendar.setTime(date);

        if (nowCalendar.get(Calendar.SECOND) <= dateCalendar.get(Calendar.SECOND)) {
            // 需要比较秒时
            if (nowCalendar.get(Calendar.SECOND) == dateCalendar.get(Calendar.SECOND)) {
                return isBeforeTimes_MS(date);
            }
            else {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段后 ( ss:SSS )
     * @param date
     * @return
     */
    public static boolean isAfterTimes_S_MS(Date date) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        dateCalendar.setTime(date);

        // 当满足条件时
        if (nowCalendar.get(Calendar.SECOND) >= dateCalendar.get(Calendar.SECOND)) {
            // 需要比较秒时
            if (nowCalendar.get(Calendar.SECOND) == dateCalendar.get(Calendar.SECOND)) {
                return isAfterTimes_MS(date);
            }
            else {
                return true;
            }

        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段内 ( SSS )
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenTimes_MS(Date start, Date end) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        startCalendar.setTime(start);
        endCalendar.setTime(end);

        if (nowCalendar.get(Calendar.MILLISECOND) >= startCalendar.get(Calendar.MILLISECOND) &&
                nowCalendar.get(Calendar.MILLISECOND) <= startCalendar.get(Calendar.MILLISECOND)) {
            return true;
        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段前 ( SSS )
     * @param date 判断对比的时间
     * @return
     */
    public static boolean isBeforeTimes_MS(Date date) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        dateCalendar.setTime(date);

        if (nowCalendar.get(Calendar.MILLISECOND) <= dateCalendar.get(Calendar.MILLISECOND)) {
            return true;
        }

        return false;
    }

    /**
     * 判断当前时间是否在对应的时间段后 ( SSS )
     * @param date 判断对比的时间
     * @return
     */
    public static boolean isAfterTimes_MS(Date date) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();

        nowCalendar.setTime(new Date());
        dateCalendar.setTime(date);

        if (nowCalendar.get(Calendar.MILLISECOND) >= dateCalendar.get(Calendar.MILLISECOND)) {
            return true;
        }

        return false;
    }
}
