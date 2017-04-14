package com.chuangfeng.pdnl.live.mvp.presenter;

/**
 * Created by chuangfeng on 2017/4/13.
 */

public interface ILiveListPresenter {
    void getLiveList(int offset, int limit, String game_type);//请求不同游戏的直播列表
}
