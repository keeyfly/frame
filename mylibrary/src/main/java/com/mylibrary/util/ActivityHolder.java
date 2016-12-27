package com.mylibrary.util;

import android.app.Activity;

import java.util.Stack;

/**
 * activity 容器
 * ActivityHolder
 */
public class ActivityHolder {

    private static Stack<Activity> activityStack;
    private static ActivityHolder instance;

    private ActivityHolder() {
    }

    public static ActivityHolder getInstance() {
        if (instance == null) {
            instance = new ActivityHolder();
        }
        return instance;
    }

    /**
     * add Activity 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 容器大小
     * size(这里用一句话描述这个方法的作用)
     * (这里描述这个方法适用条件 – 可选)
     *
     * @return int
     * @throws
     * @since 1.0.0
     */
    public int size() {
        return activityStack.size();
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}
