package com.hx.novel.common.util;

import com.anupcowkur.reservoir.Reservoir;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 李贺翔 on 2016/9/18.
 */
public class PreferenceHelper {

    public static void put(String key, Object object) {
        try {
            Reservoir.put(key, object);
        } catch (Exception e) {
            LoggerHelper.LLog().e("PreferenceHelper---(put) : " + e.getMessage());
        }
    }


    public static <T> T get(String key, Class<T> c) {
        try {
            if (Reservoir.contains(key)) {
                return Reservoir.get(key, c);
            }
        } catch (Exception e) {
            LoggerHelper.LLog().e("PreferenceHelper---(get) : " + e.getMessage());
        }
        return null;
    }

    public static List<?> getList(String key, List<?> list) {
        try {
            if (Reservoir.contains(key)) {
                Type resultType = new TypeToken<List<?>>() {
                }.getType();
                return Reservoir.get(key, resultType);
            }
        } catch (Exception e) {
            LoggerHelper.LLog().e("PreferenceHelper---(get) : " + e.getMessage());
        }
        return null;
    }

    public static String getString(String key) {
        try {
            if (Reservoir.contains(key)) {
                return Reservoir.get(key, String.class);
            }
        } catch (Exception e) {
            LoggerHelper.LLog().e("PreferenceHelper---(get) : " + e.getMessage());
        }
        return "";
    }

    public static boolean getBoolean(String key) {
        try {
            if (Reservoir.contains(key)) {
                return Reservoir.get(key, boolean.class);
            }
        } catch (Exception e) {
            LoggerHelper.LLog().e("PreferenceHelper---(get) : " + e.getMessage());
        }
        return false;
    }

    public static int getInt(String key) {
        try {
            if (Reservoir.contains(key)) {
                return Reservoir.get(key, int.class);
            }
        } catch (Exception e) {
            LoggerHelper.LLog().e("PreferenceHelper---(get) : " + e.getMessage());
        }
        return 0;
    }

    public static long getLong(String key) {
        try {
            if (Reservoir.contains(key)) {
                return Reservoir.get(key, long.class);
            }
        } catch (Exception e) {
            LoggerHelper.LLog().e("PreferenceHelper---(get) : " + e.getMessage());
        }
        return 0;
    }


    public static void delete(String key) {
        try {
            Reservoir.delete(key);
        } catch (Exception e) {
            LoggerHelper.LLog().e("PreferenceHelper---(delete) : " + e.getMessage());
        }
    }


    public static void clear() {
        try {
            Reservoir.clear();
        } catch (Exception e) {
            LoggerHelper.LLog().e("PreferenceHelper---(clear) : " + e.getMessage());
        }
    }
}
