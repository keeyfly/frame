package com.hx.novel.frame.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;


/**
 * Created by 李贺翔 on 2016/9/26.
 */
public abstract class BaseFragment extends RxFragment {

    public Context mContext;
    //是否已经加载过数据
    protected boolean isLoadData = false;
    //首个fragment是否需要被默认初始化
    protected boolean isLoadShow = false;
    //控件是否已经初始化
    private boolean isCreateView = false;
    //加载的布局
    private View mRootView;

    public BaseFragment() {
        super();
    }

    //挂载Activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    //创建布局视图
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isCreateView = true;
        mRootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        initView(mRootView);
        return mRootView;
    }


    //此方法在控件初始化前调用，所以不能在此方法中直接操作控件会出现空指针
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isCreateView) {
            lazyLoad();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //第一个fragment会调用
        if (getUserVisibleHint() && !isLoadShow)
            lazyLoad();
    }

    private void lazyLoad() {
        //如果没有加载过就加载，否则就不再加载了
        if (!isLoadData) {
            //加载数据操作
            loadData();
            isLoadData = true;
        }
    }

    //布局文件
    public abstract int getLayoutId();

    //初始化
    public abstract void initView(View view);

    //加载数据
    public abstract void loadData();

}
