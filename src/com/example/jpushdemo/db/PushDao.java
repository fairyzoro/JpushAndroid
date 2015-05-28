package com.example.jpushdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyong on 2015/5/25.
 */
public class PushDao {
    public static final String TABLE_NAME = "pushdata";
    public static final String PD = "JG";

    public static final String ID = "id";
    public static final String MESSAGE = "message";
    public static final String RECTIME = "recTime";
    public static final String RECTIME2 = "recTime2";
    public static final String CHANNEL = "channel";
    public static final String UUID = "uuid";
    public static final String PRODUCT = "product";
    public static final String MESSAGE_ID = "messageId";
    public static final String PHONE = "phoneCaption";


    private SQLiteDatabase db;
    private String uuid;
    public PushDao(Context context) {
        db = PushSqliteHelper.getInstance(context).getWritableDatabase();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        uuid = tm.getDeviceId();
    }

    private static PushDao pushDao;
    public static PushDao getInstance(Context context) {
        if (pushDao == null) {
            pushDao = new PushDao(context);
        }
        return pushDao;
    }
    public void insert(Map<String, String> map) {
        ContentValues values = new ContentValues();
        values.put(ID, MU.i(map, ID));
        values.put(MESSAGE, MU.s(map, MESSAGE));
        values.put(RECTIME, MU.s(map, RECTIME));
        values.put(RECTIME2, MU.s(map, RECTIME2));
        values.put(UUID, uuid);
        values.put(PRODUCT, PD);
        values.put(MESSAGE_ID, MU.s(map, MESSAGE_ID));
        values.put(PHONE, MU.s(map, PHONE));

        db.insert(TABLE_NAME, null, values);
    }

    public void insert(String ... args) {
        ContentValues values = new ContentValues();
        values.put(MESSAGE, args[0]);
        values.put(RECTIME, args[1]);
        values.put(RECTIME2, args[2]);
        values.put(MESSAGE_ID, args[3]);
        values.put(UUID, uuid);
        values.put(PRODUCT, PD);
        values.put(PHONE, Build.BRAND + Build.MODEL);

        db.insert(TABLE_NAME, null, values);
    }


    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor != null) {
            Map<String, Object> map = null;
            while (cursor.moveToNext()) {
                map = new HashMap<>();
                map.put(ID, cursor.getString(cursor.getColumnIndex(ID)));
                map.put(MESSAGE, cursor.getString(cursor.getColumnIndex(MESSAGE)));
                map.put(RECTIME, cursor.getString(cursor.getColumnIndex(RECTIME)));
                map.put(RECTIME2, cursor.getString(cursor.getColumnIndex(RECTIME2)));
                map.put(CHANNEL, cursor.getString(cursor.getColumnIndex(CHANNEL)));
                map.put(UUID, cursor.getString(cursor.getColumnIndex(UUID)));
                map.put(PRODUCT, cursor.getString(cursor.getColumnIndex(PRODUCT)));
                map.put(MESSAGE_ID, cursor.getString(cursor.getColumnIndex(MESSAGE_ID)));
                map.put(PHONE, cursor.getString(cursor.getColumnIndex(PHONE)));
                list.add(map);
            }
            cursor.close();
        }

        return list;
    }

    public void clear() {
        db.delete(TABLE_NAME, null, null);
    }
}