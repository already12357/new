package com.zhq;

import com.zhq.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {
    @Test
    public void test() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        Date date1 = simpleDateFormat.parse("19:56:15.127");
        Date date2 = simpleDateFormat.parse("12:40:15.127");

//        System.out.println(DateUtil.isBetweenTimes(date2, date1, DateUtil.H_M_S_MS));
    }
}
