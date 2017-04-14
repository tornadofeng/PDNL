package com.chuangfeng.pdnl.live.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.chuangfeng.pdnl.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by chuangfeng on 2017/4/13.
 * 观看直播Activity
 */

public class LivePlayActivity extends SwipeBackActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_play);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onClick() {
    }
}
