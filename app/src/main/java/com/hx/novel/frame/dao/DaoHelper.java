/*
package com.hx.novel.frame.dao;


import org.greenrobot.greendao.query.Query;

*/
/**
 * Created by 李贺翔 on 2016/12/13.
 * Description:增删改查功能
 *//*



public class DaoHelper<T> {
    private DaoSession daoSession;

    public DaoHelper() {
        daoSession = DaoManager.getDaoSession();
    }

    public void insert(T tClass) {
        try {
            if (daoSession != null) {
                daoSession.insert(tClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id, T object) {
        try {

  daoSession.getDao(object).queryBuilder().where(object.getClass().)
            List<User> list = daoSession.getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(id)).build().list();
            for (User user : list) {
                daoSession.getUserDao().delete(user);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Class<T> object) {
        try {
            daoSession.getDao(object).queryBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(Class<T> object) {
        try {
            daoSession.getDao(object).deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Class<T> object1, Object object2) {
        try {
            // daoSession.getDao(object1).update(object2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> Query<?> search(Class<T> object) {
        try {
            Query<?> build = daoSession.getDao(object).queryBuilder().build();
            return build;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

*/
