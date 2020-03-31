package com.czl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zerechen
 * @description 日期转换类
 */
public class DateUtil {

    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public static String getStringTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
    public static Date getDateTime() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date date = new Date();
        return simpleDateFormat.parse(simpleDateFormat.format(date));
    }
}
