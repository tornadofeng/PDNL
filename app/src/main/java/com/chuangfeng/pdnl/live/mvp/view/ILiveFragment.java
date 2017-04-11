package com.chuangfeng.pdnl.live.mvp.view;

import com.chuangfeng.pdnl.live.bean.LiveIndicatorBean;

import java.util.List;

/**
 * Created by chuangfeng on 2017/4/11.
 */

public interface ILiveFragment {
    void updateIndicator(List<LiveIndicatorBean> columnBeanList);//更新分类的Indicator
    void showError(String message);//异常错误
}
