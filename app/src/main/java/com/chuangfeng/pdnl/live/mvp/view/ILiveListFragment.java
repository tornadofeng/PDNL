package com.chuangfeng.pdnl.live.mvp.view;

import com.chuangfeng.pdnl.live.mvp.model.bean.LiveListItemBean;
import com.chuangfeng.pdnl.util.retrofit.exception.IErrorView;

import java.util.List;

/**
 * Created by chuangfeng on 2017/4/11.
 */

public interface ILiveListFragment extends IErrorView {

    void updateRecyclerView(List<LiveListItemBean> roomBeanList);//更新列表

}
