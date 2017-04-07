package com.chuangfeng.pdnl.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 统一操作SharedPreferences
 * Created by chuangfeng on 2017/4/6.
 */

public class SPUtil {

    private static Context context;
    private static SPUtil instance = null;

    /*************默认保存为String类型（int与boolean在末尾添加说明）**************/
    public static final String BOOLEAN_XX= "booleanXX";//boolean型value
    public static final String STRING_XX = "string_XX";//String型value
    public static final String INT_XX = "intXX"; //int型value

    private SPUtil(Context ctx) {
        context = ctx;
    }

    public static SPUtil getInstance(Context ctx) {
        if (instance == null) {
            synchronized (SPUtil.class) {
                if (instance == null) {
                    instance = new SPUtil(ctx.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private SharedPreferences getSP() {
        return context.getSharedPreferences("PDNL_sp", Context.MODE_PRIVATE);
    }

    /************字符串String(key-value)*************/
    public void setString(String key, String value) {
        getSP().edit().putString(key, value).commit();
    }

    public String getString(String key) {
        return getSP().getString(key, "");
    }

    /***************整型int(key-value)****************/
    public void setInt(String key, int value) {
        getSP().edit().putInt(key, value).commit();
    }

    public int getInt(String key) {
        return getSP().getInt(key, 0);
    }

    /**********布尔值boolean(key-value)****************/
    public void setBoolean(String key, boolean value) {
        getSP().edit().putBoolean(key, value).commit();
    }

    public Boolean getBoolean(String key) {
        return getSP().getBoolean(key, false);
    }

    /**********浮点值float(key-value)****************/
    public void setFloat(String key, float value) {
        getSP().edit().putFloat(key, value).commit();
    }

    public float getFloat(String key) {
        return getSP().getFloat(key, 0);
    }

    /**********长浮点值long(key-value)****************/
    public void setLong(String key, long value) {
        getSP().edit().putLong(key, value).commit();
    }

    public float getLong(String key) {
        return getSP().getLong(key, 0);
    }
}
