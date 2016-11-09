package com.uto.djf.test.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by djf on 2016/8/16.
 */
public class MessageDatabaseManager  {
    private MessageDatabaseHelper dbHelper;

    public MessageDatabaseManager(Context context) {
        this.context = context;
        dbHelper = new MessageDatabaseHelper(context, "invite.db", 1);
    }

    private static MessageDatabaseManager instance;

    public static MessageDatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new MessageDatabaseManager(context);
        }
        return instance;
    }

    /**
     * 群组数据库，变化通知的URI
     */
    private Uri uri = Uri.parse("content://com.djf.message.update");
    private Context context;

    /**
     * 添加记录到数据库
     */
    public void addProduct(String hostId, String hostName, String pic,
                           String time, String branchId, String branchName,
                           String roomId,String myId,String myName,String state ) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

//        db.delete("pruducts", " pid = ? ", new String[] { pid });

        /**
         *  + "hostId varchar(40),"
         + "hostName varchar(40),"
         + "pic varchar(80),"
         + "time int,"
         + "branchId varchar(40),"
         + "branchName varchar(40),"
         + "roomId varchar(40),"
         + "myId varchar(40),"
         + "myName varchar(40),"
         + "state int);"
         */
        ContentValues values = new ContentValues();
        values.put("hostId", hostId);
        values.put("hostName", hostName);
        values.put("pic", pic);
        values.put("time", time);
        values.put("branchId", branchId);
        values.put("branchName", branchName);
        values.put("roomId", roomId);
        values.put("myId", myId);
        values.put("myName", myName);
        values.put("state", state);

        db.insert("messages", null, values);
        context.getContentResolver().notifyChange(uri, null);
    }
    /**
     * 获取所有记录
     *
     * @return
     */
    public Cursor getAllMessages(Context context) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("messages", null, null, null, null, null,
                "_id desc");
        // 设置一个用于通知的 uri
        cursor.setNotificationUri(context.getContentResolver(), uri);
        return cursor;
    }

    /**
     * 根据被邀请用户id获取浏览记录
     *
     * @param myId
     * @return
     */
    public Cursor getAllMessagesByMyId(Context context, String myId) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("messages", null, "myId=?",
                new String[] { myId }, null, null, "_id desc");
        // 设置一个用于通知的 uri
        cursor.setNotificationUri(context.getContentResolver(), uri);
        return cursor;
    }

    /**
     * 删除所有记录数据
     *
     * @param context
     */
    public void deleteHistory(Context context) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        MessageDatabaseManager dao0 = MessageDatabaseManager.getInstance(context);

        Cursor cursor0 = dao0.getAllMessages(context);

        if (cursor0 != null && cursor0.getCount() > 0) {
            for (int i = 0; i < cursor0.getCount(); i++) {
                cursor0.moveToNext();
                String myId = cursor0.getString(cursor0.getColumnIndex("myId"));
                db.delete("messages", " myId = ? ", new String[] { myId });
            }
        }

        // 发出通知，数据库数据库发生变化了
        context.getContentResolver().notifyChange(uri, null);

    }

    /**
     * 根据用户id删除浏览记录数据
     *
     * @param context
     * @param
     */
    public void deleteHistoryWithUserId(Context context, String userId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        MessageDatabaseManager dao = MessageDatabaseManager.getInstance(context);

        Cursor cursor = dao.getAllMessagesByMyId(context, userId);

        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String myId = cursor.getString(cursor.getColumnIndex("myId"));
                db.delete("pruducts", " myId = ? ", new String[] { myId });
            }
        }

        // 发出通知，数据库数据库发生变化了
        context.getContentResolver().notifyChange(uri, null);
    }

}
