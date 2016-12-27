package com.hx.novel.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 李贺翔 on 2016/12/6.
 * Description:正则表达式
 */
public class RegularHelper {

    //验证邮箱
    public final static boolean isEmail(String email) {
        if (StringHelper.isEmpty(email)) {
            return false;
        }
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public final static boolean isPhone(String phone) {
        if (StringHelper.isEmpty(phone)) {
            return false;
        }
        Pattern p = Pattern.compile("^((13[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}|(17[0,5-9])\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    //验证手机号
    public final static boolean isMobilePhone(String cellphone) {
        if (StringHelper.isEmpty(cellphone)) {
            return false;
        }
        Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");//复杂匹配
        Matcher m = p.matcher(cellphone);
        return m.matches();
    }

    //是否为固定电话
    public final static boolean isTelePhone(String cellphone) {

        if (StringHelper.isEmpty(cellphone)) {
            return false;
        }
        Pattern p = Pattern.compile("\\d{3,4}-\\d{7,8}$");//复杂匹配
        Matcher m = p.matcher(cellphone);
        return m.matches();
    }

    //是否为身份证号
    public final static boolean isIDcard(String cellphone) {
        if (StringHelper.isEmpty(cellphone)) {
            return false;
        }
        Pattern p = Pattern.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");//复杂匹配
        Matcher m = p.matcher(cellphone);
        Pattern p2 = Pattern.compile(" ^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$");
        Matcher m2 = p2.matcher(cellphone);


        return m.matches() || m2.matches();
    }


    //验证密码是否合格
    public final static boolean isPwdLegal(String cellphone) {
        if (StringHelper.isEmpty(cellphone)) {
            return false;
        }
        Pattern p = Pattern.compile("^\\w{6,20}$");
        Matcher m = p.matcher(cellphone);
        Pattern p9 = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher m9 = p9.matcher(cellphone);
        return m.matches() && m9.matches();
    }

    //判断用户名是否合法
    public final static boolean isNameLegal(String cellphone) {
        if (StringHelper.isEmpty(cellphone)) {
            return false;
        }
        Pattern p = Pattern.compile("^\\w{6,20}$");
        Matcher m = p.matcher(cellphone);
        Pattern p4 = Pattern.compile("^[0-9]*$");
        Matcher m4 = p4.matcher(cellphone);
        Pattern p9 = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*$");
        Matcher m9 = p9.matcher(cellphone);
        Pattern p5 = Pattern.compile("^[a-zA-Z]*$");
        Matcher m5 = p5.matcher(cellphone);
        return m.matches() && m9.matches();
    }

    //判断车牌号是否合法
    public final static boolean isVehicleLegal(String carnum) {
        String reg = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}[a-zA-Z]{1}[a-zA-Z0-9_]{5}$";
        if (StringHelper.isEmpty(carnum)) {
            return false;
        }
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(carnum);


        return m.matches();
    }

    //MD5加密
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    //根据日期判断星期
    public static String getWeek(String pTime) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(pTime));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "/星期天/";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "/星期一/";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "/星期二/";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "/星期三/";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "/星期四/";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "/星期五/";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "/星期六/";
        }

        return Week;
    }

    public static String getMonth(int yMonth) {
        String month;
        if (yMonth < 10) {
            month = "0" + yMonth;
        } else {
            month = yMonth + "";
        }
        return month;
    }


    public static String getDay(int yDay) {
        String day;
        if (yDay < 10) {
            day = "0" + yDay;
        } else {
            day = yDay + "";
        }
        return day;
    }

}
