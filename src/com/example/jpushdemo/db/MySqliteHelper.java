package com.example.jpushdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by zhangyong on 2015-05-01.
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    private static MySqliteHelper helper;
    public static final String CREATE_TABLE = "create table if not exists " + JpushDao.TABLE_NAME + "(" +
            "id integer primary key autoincrement," +
            JpushDao.C_RECTIME + " varchar(50)," +
            JpushDao.C_RECTIME2 + " varchar(50)," +
            JpushDao.C_MSG_ID + " varchar(20)," +
            JpushDao.C_MSG + " varchar(400)," +
            JpushDao.C_CHANNEL + " varchar(10) default 'ANDROID' )";

    private MySqliteHelper(Context context) {
        super(context, Environment.getExternalStorageDirectory().getAbsolutePath() + "/Jpush/jpush.db", null, 1);
    }

    public static MySqliteHelper getInstance(Context context) {
        if (helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
