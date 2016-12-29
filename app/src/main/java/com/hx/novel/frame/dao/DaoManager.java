/*
package com.hx.novel.frame.dao;

import android.content.Context;

import org.greenrobot.greendao.database.Database
import org.greenrobot.greendao.query.QueryBuilder;


*/
/**
 * Created by 李贺翔 on 2016/12/13.
 * Description:数据库管理
 *//*




public class DaoManager {

    private static final String DB_NAME = "tms_db";//数据库名称
    private static final boolean ENCRYPTED = false;
    private DaoMaster.OpenHelper helper;
    private static Database daoMaster;
    private static DaoSession daoSession;
    private Context mContext;

    private static class DaoManagerHolder {
        static final DaoManager INSTANCE = new DaoManager();
    }

    public static DaoManager getInstance() {
        return DaoManagerHolder.INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
        initData();
        setDebug();
    }


    */
/**
     * 创建数据库
     *//*


    private void initData() {
        if (daoMaster == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
            daoMaster = ENCRYPTED ? devOpenHelper.getEncryptedWritableDb("super-secret") : devOpenHelper.getWritableDb();
        }
    }


    */
/**
     * 完成对数据库的添加、删除、修改、查询的操作
     *
     * @return DaoSession
     *//*


    public static DaoSession getDaoSession() {
        if (daoSession == null && daoMaster != null) {
            daoSession = new DaoMaster(daoMaster).newSession();
        }
        return daoSession;
    }


    */
/**
     * 打开输出日志的操作,默认是关闭的
     *//*


    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }


    */
/**
     * 关闭所有的操作,数据库开启的时候，使用完毕了必须要关闭
     *//*


    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

}

*/
