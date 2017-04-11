package com.chuangfeng.pdnl.live.mvp.presenter.impl;

import android.content.Context;

import com.chuangfeng.pdnl.live.api.LiveAPI;
import com.chuangfeng.pdnl.live.bean.LiveIndicatorBean;
import com.chuangfeng.pdnl.live.mvp.presenter.ILivePresenter;
import com.chuangfeng.pdnl.live.mvp.view.ILiveFragment;
import com.chuangfeng.pdnl.util.retrofit.HttpSubscriber;
import com.chuangfeng.pdnl.util.retrofit.RetrofitHelper;

import java.util.List;

/**
 * Created by chuangfeng on 2017/4/11.
 */

public class LivePresenterImpl implements ILivePresenter {

    private Context context;
    private ILiveFragment view;

    public LivePresenterImpl(Context context, ILiveFragment view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getColumnList() {
        RetrofitHelper.getLiveHelper().create(LiveAPI.class)
                .getColumnList()
                .compose(RetrofitHelper.<List<LiveIndicatorBean>>handleLiveResult())
                .subscribe(new HttpSubscriber<List<LiveIndicatorBean>>() {

                    @Override
                    public void _onNext(List<LiveIndicatorBean> columnBeanList) {
                        view.updateIndicator(columnBeanList);
                    }

                    @Override
                    public void _onError(String message) {
                        view.showError(message);
                    }
                });
    }
}
