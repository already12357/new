package com.zhq.util;

import java.text.SimpleDateFormat;
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
}
