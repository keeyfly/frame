package com.hx.novel.frame.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @param <T>
 */
public class FrameJson<T> implements Serializable {

    @SerializedName("data")
    public T data;
    @SerializedName("status")
    public boolean status;
    @SerializedName("message")
    public String message;
    @SerializedName("code")
    public int code;
}
