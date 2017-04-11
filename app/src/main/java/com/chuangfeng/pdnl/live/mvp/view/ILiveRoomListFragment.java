package com.chuangfeng.pdnl.live.mvp.view;

import com.chuangfeng.pdnl.live.bean.LiveRoomBean;

import java.util.List;

/**
 * Created by chuangfeng on 2017/4/11.
 */

public interface ILiveRoomListFragment {

    void updateRecyclerView(List<LiveRoomBean> roomBeanList);//更新列表
    void showError(String message);//异常错误
}
