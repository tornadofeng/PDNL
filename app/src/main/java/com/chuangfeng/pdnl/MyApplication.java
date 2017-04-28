package com.chuangfeng.pdnl;

import android.app.Application;

import com.chuangfeng.pdnl.util.ToastUtil;

/**
 * Created by chuangfeng on 2017/4/10.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);
        ToastUtil.init(getApplicationContext());
    }
}
