package com.example.jpushdemo.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhangyong on 2015/5/27.
 * Map Util
 */
public class MU {


    /**
     * long
     *
     * @param map
     * @param key
     * @return
     */
    public static long l(Map map, String key) {
        Object object = map.get(key);
        long val = 0;
        if (object == null) {
            return val;
        }
        try {
            val = Long.valueOf(object.toString());
        } catch (Exception e) {
        }

        return val;
    }


    /**
     * ing
     * @param map
     * @param key
     * @return
     */
    public static int i(Map map, String key) {
        Object object = map.get(key);
        int val = 0;
        if (object == null) {
            return val;
        }
        try {
            val = Integer.valueOf(object.toString());
        } catch (Exception e) { }

        return val;
    }


    /**
     * string
     * @param map
     * @param key
     * @return
     */
    public static String s(Map map, String key) {
        Object object = map.get(key);
        return String.valueOf(object);
    }

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getStrTime(Date date) {
        return DATE_FORMAT.format(date);
    }
}
