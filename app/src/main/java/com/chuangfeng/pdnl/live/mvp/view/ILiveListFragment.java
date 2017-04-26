package com.chuangfeng.pdnl.live.mvp.view;

import com.chuangfeng.pdnl.live.mvp.model.bean.LiveListItemBean;

import java.util.List;

/**
 * Created by chuangfeng on 2017/4/11.
 */

public interface ILiveListFragment {

    void updateRecyclerView(List<LiveListItemBean> roomBeanList);//更新列表
    void showError(String message);//异常错误
}
