package com.uto.djf.test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import greendao.Contents;
import greendao.DaoMaster;
import greendao.DaoSession;


/**
 * Created by black on 2016/4/26 0026.
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    public DaoSession mDaoSession;
    public SQLiteDatabase mDB;
    public DaoMaster.DevOpenHelper mHelper;
    public DaoMaster mDaoMaster;
    private Context mContext;

    private DatabaseManager(){}

    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void setContext(Context context){
        mContext = context;
    }

    public void initDatabase(){
        mHelper = new DaoMaster.DevOpenHelper(mContext, Contents.DateBaseName,null);
        mDB = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDB);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public DaoMaster getDaoMaster(){
        return mDaoMaster;
    }

}
