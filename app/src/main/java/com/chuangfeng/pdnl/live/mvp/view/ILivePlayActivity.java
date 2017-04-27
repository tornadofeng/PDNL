package com.chuangfeng.pdnl.live.mvp.view;

import com.chuangfeng.pdnl.live.mvp.model.bean.LiveDetailBean;
import com.chuangfeng.pdnl.live.mvp.model.bean.LiveDetailDouyuBean;

/**
 * Created by chuangfeng on 2017/4/26.
 */

public interface ILivePlayActivity extends IErrorView {

    void updateLiveDetail(LiveDetailBean detailBean);//更新直播详情

    void updateDouyuDetail(LiveDetailDouyuBean detailDouyuBean);//更新斗鱼弹幕聊天室详情
}
