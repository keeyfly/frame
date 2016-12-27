package com.mylibrary.util;

public class StringHelper {

    public static boolean isNull(String text) {
        return text == null || text.replace(" ", "").trim().equals("");
    }

}
