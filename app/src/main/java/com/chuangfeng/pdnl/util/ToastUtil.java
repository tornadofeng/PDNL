package com.chuangfeng.pdnl.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chuangfeng on 2017/4/10.
 */

public class ToastUtil {

    private static Toast toast;
    private static boolean isShow = false;

    public static void initToast(Context context) {
        if (toast == null) {
            synchronized (ToastUtil.class) {
                toast = new Toast(context.getApplicationContext());
            }
        }
    }

    public static void show(Context context, String content){
        initToast(context);
        if (isShow == true){
            isShow = false;
            toast.cancel();
        }
        toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        toast.show();
        isShow = true;
    }
}
