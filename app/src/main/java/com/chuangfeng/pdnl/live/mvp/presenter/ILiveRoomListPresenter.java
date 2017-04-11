package com.chuangfeng.pdnl.live.mvp.presenter;

/**
 * Created by chuangfeng on 2017/4/11.
 */

public interface ILiveRoomListPresenter {
    void getAllRoomList(int offset, int limit, String client_sys);//请求全部直播
    void getColumnRoomList(String cate_id, int offset, int limit, String clientSys);//请求不同栏目直播
}
