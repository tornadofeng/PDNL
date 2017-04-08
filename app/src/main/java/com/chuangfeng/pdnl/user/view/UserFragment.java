package com.chuangfeng.pdnl.user.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuangfeng.pdnl.R;
import com.chuangfeng.pdnl.widget.TopBar;
import com.chuangfeng.pdnl.widget.TopBarConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chuangfeng on 2017/4/8.
 */

public class UserFragment extends SupportFragment {

    @BindView(R.id.top_bar)
    TopBar topBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);

        intiTopBar();
        return view;
    }

    private void intiTopBar() {
        TopBarConfig config = new TopBarConfig.Buider()
                .setTitleTextViewVisible(true)
                .setTitleTextViewText(getString(R.string.title_user))
                .create();
        topBar.init(config);
    }
}
