package com.hx.novel.frame.presenter;

import com.hx.novel.frame.view.FrameView;

/**
 * Class Name:com.hx.novel.frame.presenter
 * Created by 李贺翔 on 2017/7/28.
 * Description: 优化Presenter
 */
public interface IPresenter<V extends FrameView> {

    /**
     * 绑定
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 防止内存的泄漏, 清除Presenter与Activity之间的绑定
     */
    void detachView();

    /**
     * @return 获取View
     */
    V getView();
}
