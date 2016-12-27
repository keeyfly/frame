package com.hx.novel.common.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateHelper {
    public static String getTime() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date curDate = new Date(System.currentTimeMillis());

        String reStr = timeFormatter.format(curDate);
        return reStr;
    }

    public static String getCurrentDate() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());

        String reStr = timeFormatter.format(curDate);
        return reStr;
    }

    //获取当前的时间戳
    public static String currentTimeTamp() {

        long  timestamp = (System.currentTimeMillis());//获取系统当前时间

        return String.valueOf(timestamp);

    }

    public static String convertTamp2Time(String timetamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = null;
        try {
            Long timestamp = Long.parseLong(timetamp);
            time = sdf.format(new java.util.Date(timestamp));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return time;

    }

    public static String convertTamp2TimeHour(String timetamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = null;
        try {
            Long timestamp = Long.parseLong(timetamp);
            time = sdf.format(new java.util.Date(timestamp));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return time;

    }
}
