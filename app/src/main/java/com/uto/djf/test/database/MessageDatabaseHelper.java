package com.uto.djf.test.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by djf on 2016/8/16.
 */
public class MessageDatabaseHelper extends SQLiteOpenHelper {

    public MessageDatabaseHelper(Context context, String name,  int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table messages(_id integer primary key autoincrement,"
                + "hostId varchar(40),"
                + "hostName varchar(40),"
                + "pic varchar(80),"
                + "time int,"
                + "branchId varchar(40),"
                + "branchName varchar(40),"
                + "roomId varchar(40),"
                + "myId varchar(40),"
                + "myName varchar(40),"
                + "state int);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
