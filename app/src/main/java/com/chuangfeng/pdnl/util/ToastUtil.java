package com.chuangfeng.pdnl.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 用于维护唯一Toast的工具类
 * Created by chuangfeng on 2017/4/10.
 */

public class ToastUtil {

    private static Context context;
    private static Toast toast;
    private static boolean isShow = false;

    public static void init(Context applicationContext) {
        context = applicationContext;
        initToast(context);
    }

    private static void initToast(Context context) {
        if (toast == null) {
            synchronized (ToastUtil.class) {
                toast = new Toast(context);
            }
        }
    }

    public static void show(CharSequence content, int duration){
        initToast(context);
        if (isShow == true){
            isShow = false;
            toast.cancel();
        }
        toast = Toast.makeText(context, content, duration);
        toast.show();
        isShow = true;
    }

    public static void show(CharSequence content){
        show(content, Toast.LENGTH_SHORT);
    }

    public static void show(int resId){
        show(context.getString(resId), Toast.LENGTH_SHORT);
    }
}
