package com.hx.novel.frame.presenter;

import com.hx.novel.frame.model.IModel;
import com.hx.novel.frame.view.FrameView;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Class Name:com.hx.novel.frame.presenter
 * Created by 李贺翔 on 2017/7/28.
 * Description: 防止Activity 无法被回收（内存泄漏）
 */
public abstract class BasePresenter<V extends FrameView> implements IPresenter {

    private WeakReference<V> mViewRef; // View接口类型的弱引用

    /**
     * 建立关联
     */
    @Override
    public void attachView(FrameView frameView) {
        mViewRef = new WeakReference(frameView);
    }

    /**
     * 解除关联
     */
    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 获取View
     */
    @Override
    public V getView() {
        return mViewRef.get();
    }

    /**
     * 判断是否与View建立了关联
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public abstract HashMap<String, IModel> getModelMap();

    /**
     * @param models 添加多个model,如有需要
     */
    public abstract HashMap<String, IModel> loadModelMap(IModel... models);
}
