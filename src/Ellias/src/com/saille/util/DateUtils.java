package com.saille.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class DateUtils {
    private static final Logger LOGGER = Logger.getLogger(DateUtils.class);

    public static Integer convert2Int(Date date) {
        if(date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.valueOf(Integer.parseInt(sdf.format(date)));
    }

    public static Integer convert2Int(String date) {
        if(StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date d = sdf.parse(date);
            return convert2Int(d);
        } catch(Exception ex) {
            LOGGER.error("日期转换失败：" + date);
        }
        return null;
    }

    public static Date convert2Date(Integer d) {
        if(d == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            return sdf.parse(String.valueOf(d));
        } catch(Exception ex) {
            LOGGER.error("日期转换失败：" + d);
        }
        return null;
    }

    public static Integer addDay(Integer d, int count) {
        if(d == null) {
            return null;
        }
        Date date = convert2Date(d);
        if(date != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(5, count);
            return convert2Int(c.getTime());
        }
        return null;
    }
}