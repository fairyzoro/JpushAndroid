package com.example.jpushdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

/**
 * Created by zhangyong on 2015-05-01.
 */
public class JpushDao {
    public static JpushDao jpushDao;
    public static final String TABLE_NAME = "jpush";
    public static final String C_RECTIME = "recTime";
    public static final String C_RECTIME2 = "recTime2";
    public static final String C_MSG_ID = "messageId";
    public static final String C_MSG = "message";
    public static final String C_CHANNEL = "channel";


    private SQLiteDatabase db;
    private JpushDao(Context context) {
        db = MySqliteHelper.getInstance(context).getWritableDatabase();
    }

    public static JpushDao getInstance(Context context) {
        if (jpushDao == null) {
            jpushDao = new JpushDao(context);
        }
        return jpushDao;
    }

    public void insert(Map<String, String> map) {
        ContentValues values = new ContentValues();
        values.put(C_RECTIME, String.valueOf(map.get(C_RECTIME)));
        values.put(C_RECTIME2, String.valueOf(map.get(C_RECTIME2)));
        values.put(C_MSG_ID, String.valueOf(map.get(C_MSG_ID)));
        values.put(C_MSG, String.valueOf(map.get(C_MSG)));

        db.insert(TABLE_NAME, null, values);
    }
}