package com.hx.novel.common.util;

/**
 * Created by Heart on 2016/8/22 0022.
 */
public class StringHelper {

    public static boolean isEmpty(String text) {
        return text == null || text.replace(" ", "").trim().equals("");
    }

}
