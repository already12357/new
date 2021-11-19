package com.zhq;

import com.zhq.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtilTest {
    @Test
    public void test() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        Date date1 = simpleDateFormat.parse("9:10:15.127");
        Date date2 = simpleDateFormat.parse("9:20:15.127");
        Date dateTest = DateUtil.nowBeforeTimes(Calendar.HOUR_OF_DAY, 20);

        System.out.println(dateTest);
    }
}
