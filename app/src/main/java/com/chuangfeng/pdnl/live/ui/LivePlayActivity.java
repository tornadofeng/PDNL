package com.chuangfeng.pdnl.live.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chuangfeng.pdnl.R;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by chuangfeng on 2017/4/13.
 * 观看直播Activity
 */

public class LivePlayActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_play);
    }
}
