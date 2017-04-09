package com.chuangfeng.pdnl.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chuangfeng on 2017/4/8.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<SupportFragment> fragmentList;

    public MainFragmentPagerAdapter(FragmentManager fm, List<SupportFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
