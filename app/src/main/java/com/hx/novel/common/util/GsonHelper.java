package com.hx.novel.common.util;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class GsonHelper {

    /**
     * @param json
     * @param target
     * @return <T>
     * description 将json格式字符串，转化成T数据结构类型的对象
     */

    public static <T> T readJsonObject(String json, Class<T> target) {

        T data = null;
        try {
            Gson gson = new Gson();
            data = gson.fromJson(json, target);
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
        }
        return data;
    }

    /**
     * @param sou
     * @param target
     * @return <T>
     * description 将T类型的数据对象，转化为json格式字符串
     */
    public static <T> String toJsonString(T sou, Class<T> target) {

        String data = null;
        try {
            Gson gson = new Gson();
            data = gson.toJson(sou, target);
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
        }
        return data;
    }

    /*
    **Type type = new TypeToken<ArrayList<City>>() {}.getType();
     *
     * @param sou
     * @param type
     * @return <T>
     * description  将对象数组格式的数据对象，转化为jsonArray字符串
     */
    public static <T> String toJsonArrayString(T sou, Type type) {

        String data = null;
        try {
            Gson gson = new Gson();
            data = gson.toJson(sou, type);
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
        }
        return data;
    }


    public static <T> ArrayList<T> readJsonArray(String json, Class<T> cls) {

        ArrayList<T> data = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<T>>() {
            }.getType();
            data = gson.fromJson(json, listType);
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
        }
        return data;
    }


}
