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
        Date date2 = simpleDateFormat.parse("11:25:15.127");

        System.out.println(DateUtil.isBetweenTimes_H_M_S_MS(date2, date1));
    }
}
