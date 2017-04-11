package com.chuangfeng.pdnl.live.mvp.presenter.impl;

import android.content.Context;

import com.chuangfeng.pdnl.live.api.LiveAPI;
import com.chuangfeng.pdnl.live.bean.LiveRoomBean;
import com.chuangfeng.pdnl.live.mvp.presenter.ILiveRoomListPresenter;
import com.chuangfeng.pdnl.live.mvp.view.ILiveRoomListFragment;
import com.chuangfeng.pdnl.util.retrofit.HttpSubscriber;
import com.chuangfeng.pdnl.util.retrofit.RetrofitHelper;

import java.util.List;

/**
 * Created by chuangfeng on 2017/4/11.
 */

public class LiveRoomListPresenterImpl implements ILiveRoomListPresenter {

    private Context context;
    private ILiveRoomListFragment view;

    public LiveRoomListPresenterImpl(Context context, ILiveRoomListFragment view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getAllRoomList(int offset, int limit, String client_sys) {
        RetrofitHelper.getLiveHelper().create(LiveAPI.class)
                .getAllLiveList(offset, limit, client_sys)
                .compose(RetrofitHelper.<List<LiveRoomBean>>handleLiveResult())
                .subscribe(new HttpSubscriber<List<LiveRoomBean>>() {
                    @Override
                    public void _onNext(List<LiveRoomBean> roomBeanList) {
                        view.updateRecyclerView(roomBeanList);
                    }

                    @Override
                    public void _onError(String message) {
                        view.showError(message);
                    }
                });
    }

    @Override
    public void getColumnRoomList(String cate_id, int offset, int limit, String client_sys) {
        RetrofitHelper.getLiveHelper().create(LiveAPI.class)
                .getColumnLiveList(cate_id, offset, limit, client_sys)
                .compose(RetrofitHelper.<List<LiveRoomBean>>handleLiveResult())
                .subscribe(new HttpSubscriber<List<LiveRoomBean>>() {
                    @Override
                    public void _onNext(List<LiveRoomBean> roomBeanList) {
                        view.updateRecyclerView(roomBeanList);
                    }

                    @Override
                    public void _onError(String message) {
                        view.showError(message);
                    }
                });
    }
}
