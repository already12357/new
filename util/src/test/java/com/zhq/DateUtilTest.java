package com.zhq;

import com.zhq.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtilTest {
    @Test
    public void test() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        Date date1 = simpleDateFormat.parse("9:10:15.127");
        Date date2 = simpleDateFormat.parse("9:20:15.127");

        System.out.println(DateUtil.onlyTimeBetween(new Date(), date1, date2, TimeUnit.SECONDS));
    }
}
