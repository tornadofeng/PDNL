package com.chuangfeng.pdnl.live.mvp.presenter;

/**
 * Created by chuangfeng on 2017/4/26.
 */

public interface ILivePlayPresenter {
    void getLiveDetail(String live_type, String live_id, String game_type);//请求直播详情
    void getDanmuDetail(String url);//请求弹幕聊天细节参数
}
