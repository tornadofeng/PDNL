package com.chuangfeng.pdnl.live.mvp.presenter.impl;

import android.content.Context;

import com.chuangfeng.pdnl.live.api.LiveAPI;
import com.chuangfeng.pdnl.live.bean.LiveListItemBean;
import com.chuangfeng.pdnl.live.mvp.presenter.ILiveListPresenter;
import com.chuangfeng.pdnl.live.mvp.view.ILiveListFragment;
import com.chuangfeng.pdnl.util.retrofit.HttpSubscriber;
import com.chuangfeng.pdnl.util.retrofit.RetrofitHelper;

import java.util.List;

/**
 * Created by chuangfeng on 2017/4/13.
 */

public class LiveListPresenterImpl implements ILiveListPresenter {

    private Context context;
    private ILiveListFragment view;

    public LiveListPresenterImpl(Context context, ILiveListFragment view) {
        this.context = context;
        this.view = view;
    }


    @Override
    public void getLiveList(int offset, int limit, String game_type) {
        RetrofitHelper.getLiveHelper().create(LiveAPI.class)
                .getLiveList(
                        offset,
                        limit,
                        game_type
                )
                .compose(RetrofitHelper.<List<LiveListItemBean>>handleLiveResult())
                .subscribe(new HttpSubscriber<List<LiveListItemBean>>() {
                    @Override
                    public void _onNext(List<LiveListItemBean> roomBeanList) {
                        view.updateRecyclerView(roomBeanList);
                    }

                    @Override
                    public void _onError(String message) {
                        view.showError(message);
                    }
                });
    }
}
