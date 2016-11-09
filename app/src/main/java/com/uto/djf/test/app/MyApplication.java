package com.uto.djf.test.app;

import android.app.Application;

import com.uto.djf.test.database.DatabaseManager;

/**
 * Created by djf on 2016/10/25.
 */
public class MyApplication extends Application {

    private DatabaseManager mDatabaseManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabaseManager = DatabaseManager.getInstance();
        mDatabaseManager.setContext(this);
        mDatabaseManager.initDatabase();
    }
}
