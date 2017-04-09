package com.chuangfeng.pdnl.user.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chuangfeng.pdnl.R;
import com.chuangfeng.pdnl.util.DateUtil;
import com.chuangfeng.pdnl.widget.LazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chuangfeng on 2017/4/8.
 */

public class UserFragment extends LazyFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.text)
    TextView text;

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    private void initToolBar() {
        toolbar.setTitle(getString(R.string.title_user));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        text.setText(DateUtil.timeStamp2Date(String.valueOf(System.currentTimeMillis()), null));
        Log.e("user", "initToolBar:");
    }

    @Override
    protected void initLazyView(Bundle savedInstanceState) {
        initToolBar();
    }
}
