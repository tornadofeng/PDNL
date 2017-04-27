package com.chuangfeng.pdnl.live.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.chuangfeng.pdnl.live.api.LiveAPI;
import com.chuangfeng.pdnl.live.mvp.model.bean.LiveDetailBean;
import com.chuangfeng.pdnl.live.mvp.model.bean.LiveDetailDouyuBean;
import com.chuangfeng.pdnl.live.mvp.presenter.ILivePlayPresenter;
import com.chuangfeng.pdnl.live.mvp.view.ILivePlayChatFragment;
import com.chuangfeng.pdnl.util.retrofit.HttpSubscriber;
import com.chuangfeng.pdnl.util.retrofit.RetrofitHelper;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chuangfeng on 2017/4/26.
 */

public class LivePlayPresenterImpl implements ILivePlayPresenter {

    private Context context;
    private ILivePlayChatFragment view;

    public LivePlayPresenterImpl(Context context, ILivePlayChatFragment view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getLiveDetail(String live_type, String live_id, String game_type) {
        RetrofitHelper.getLiveHelper().create(LiveAPI.class)
                .getLiveDetail(live_type, live_id, game_type)
                .compose(RetrofitHelper.<LiveDetailBean>handleLiveResult())
                .subscribe(new HttpSubscriber<LiveDetailBean>() {
                    @Override
                    public void _onNext(LiveDetailBean liveDetailBean) {
                        view.updateLiveDetail(liveDetailBean);
                    }

                    @Override
                    public void _onError(String message) {
                        view.showError(message);
                    }
                });
    }

    @Override
    public void getDanmuDetail(String url) {
        if (TextUtils.isEmpty(url) == false) {
            RetrofitHelper.getLiveHelper().create(LiveAPI.class)
                    .getDouyuDetail(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LiveDetailDouyuBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showError("弹幕服务器接口已过期，请刷新直播列表！");
                        }

                        @Override
                        public void onNext(LiveDetailDouyuBean detailDouyuBean) {
                            if (detailDouyuBean.getError() == 0) {
                                view.updateDouyuDetail(detailDouyuBean);
                            } else {
                                view.showError("弹幕服务器接口已过期，请刷新直播列表！");
                            }
                        }
                    });
        } else {
            view.showError("弹幕服务器接口链接无效！");
        }
    }
}
