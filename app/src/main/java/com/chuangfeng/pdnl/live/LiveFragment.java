package com.chuangfeng.pdnl.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuangfeng.pdnl.R;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chuangfeng on 2017/4/8.
 */

public class LiveFragment extends SupportFragment {

//    @BindView(R.id.top_bar)
//    TopBar topBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        ButterKnife.bind(this, view);

//        initTopBar();
        return view;
    }

//    private void initTopBar() {
//        TopBarConfig config = new TopBarConfig.Buider()
//                .setTitleTextViewVisible(true)
//                .setTitleTextViewText(getString(R.string.title_live))
//                .create();
//        topBar.init(config);
//
//    }
}
