package com.example.jpushdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import static com.example.jpushdemo.db.PushDao.*;

/**
 * Created by zhangyong on 2015/4/28.
 */
public class PushSqliteHelper extends SQLiteOpenHelper {
    private static PushSqliteHelper instance;

    private static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME + "(" +
            ID + " integer primary key autoincrement," +
            MESSAGE + " varchar(200)," +
            RECTIME + " varchar(50)," +
            RECTIME2 + " varchar(50) default ''," +
            CHANNEL + " varchar(50) default 'ANDROID'," +
            UUID + " varchar(50) default ''," +
            PRODUCT + " varchar(50) default ''," +
            MESSAGE_ID + " varchar(50) default ''," +
            PHONE + " varchar(50) default '')";


    public PushSqliteHelper(Context context) {
        super(context, Environment.getExternalStorageDirectory().getAbsolutePath()+"/jgpush/jgpush.db",null, 1);

    }


    public static PushSqliteHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PushSqliteHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
