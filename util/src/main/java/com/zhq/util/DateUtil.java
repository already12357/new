package com.zhq.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static Date dateFromStr(String strDate) {
        // 使用多个重载的方式构建出默认函数的效果
        return dateFromStr(strDate, "yyyy-MM-dd HH:mm:ss");
    }

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
}
