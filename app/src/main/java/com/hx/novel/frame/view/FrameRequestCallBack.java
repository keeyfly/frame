package com.hx.novel.frame.view;

/**
 * 网络请求
 */
public interface FrameRequestCallBack<T> {
    void requestSuccessListener(T response);
}
