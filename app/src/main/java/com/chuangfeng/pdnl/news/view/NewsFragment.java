package com.chuangfeng.pdnl.news.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuangfeng.pdnl.R;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chuangfeng on 2017/4/8.
 */

public class NewsFragment extends SupportFragment {

//    @BindView(R.id.top_bar)
//    TopBar topBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);

//        intiTopBar();
        return view;
    }

//    private void intiTopBar() {
//        TopBarConfig config = new TopBarConfig.Buider()
//                .setTitleTextViewVisible(true)
//                .setTitleTextViewText(getString(R.string.title_news))
//                .create();
//        topBar.init(config);
//    }
}
