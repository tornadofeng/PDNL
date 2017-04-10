package com.chuangfeng.pdnl.explore.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chuangfeng.pdnl.R;
import com.chuangfeng.pdnl.widget.fragment.LazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chuangfeng on 2017/4/8.
 */

public class ExploreFragment extends LazyFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.text)
    TextView text;

    public static ExploreFragment newInstance() {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    private void initToolBar() {
        toolbar.setTitle(getString(R.string.title_explore));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        Log.e("explore", "initToolBar: ");
    }

    @Override
    protected void initLazyView(Bundle savedInstanceState) {
        initToolBar();
    }
}
