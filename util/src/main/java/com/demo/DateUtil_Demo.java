package com.demo;

import com.zhq.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil 类的 Demo
 */
public class DateUtil_Demo {
    public static void main(String[] args) {
        DateUtil_Demo instance = new DateUtil_Demo();

        instance.demo1();
    }


    public void demo1() {
        // 使用 dateBeforeTimes 获取指定时间 2 小时前的时间
        System.out.println(DateUtil.dateBeforeTimes(new Date(), Calendar.HOUR_OF_DAY, 2));
        //                                50 分钟前的时间
        System.out.println(DateUtil.dateBeforeTimes(new Date(), Calendar.MINUTE, 50));
        //                                20 天前的时间
        System.out.println(DateUtil.dateBeforeTimes(new Date(), Calendar.DAY_OF_YEAR, 20));

        // 使用 dateAfterTimes 获取指定时间 4 小时后的时间
        System.out.println(DateUtil.dateAfterTimes(new Date(), Calendar.HOUR_OF_DAY, 4));
        //                                90 分钟后的时间
        System.out.println(DateUtil.dateAfterTimes(new Date(), Calendar.MINUTE, 90));
        //                                16 天后的时间
        System.out.println(DateUtil.dateAfterTimes(new Date(), Calendar.DAY_OF_YEAR, 16));
    }

    public void demo2() {
        // 使用 nowBeforeTimes 获取现在 2 小时前的时间
        System.out.println(DateUtil.nowBeforeTimes(Calendar.HOUR_OF_DAY, 2));
        //                            50 分钟前的时间
        System.out.println(DateUtil.nowBeforeTimes(Calendar.MINUTE, 50));
        //                            20 天前的时间
        System.out.println(DateUtil.nowBeforeTimes(Calendar.DAY_OF_YEAR, 20));

        // 使用 nowAfterTimes 获取现在 4 小时后的时间
        System.out.println(DateUtil.nowAfterTimes(Calendar.HOUR_OF_DAY, 4));
        //                           90 分钟后的时间
        System.out.println(DateUtil.nowAfterTimes(Calendar.MINUTE, 90));
        //                           16 天后的时间
        System.out.println(DateUtil.nowAfterTimes(Calendar.DAY_OF_YEAR, 16));
    }

    public void demo3() {
        // 使用 dateFromStr 将 特定格式的字符串 转化为 日期
        System.out.println(DateUtil.dateFromStr("2020-03-12 23:10:11"));

    }
}
