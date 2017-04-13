package com.chuangfeng.pdnl.live.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chuangfeng.pdnl.R;
import com.chuangfeng.pdnl.live.bean.LiveRoomBean;

import java.util.List;

/**
 * Created by chuangfeng on 2017/4/11.
 * 直播房间列表的Adapter
 */

public class LiveRoomAdapter extends BaseQuickAdapter<LiveRoomBean> {
    public LiveRoomAdapter(List<LiveRoomBean> data) {
        super(R.layout.item_live_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, LiveRoomBean bean) {
        viewHolder.setText(R.id.live_tv_roomname, bean.getRoom_name())//房间名称
                .setText(R.id.live_tv_nickname, bean.getNickname())//主播昵称
                .setText(R.id.live_tv_online, String.valueOf(bean.getOnline()));//在线人数
        Glide.with(mContext)//直播房间截图
                .load(bean.getRoom_src())
                .crossFade()
                .centerCrop()
                .into((ImageView) viewHolder.getView(R.id.live_iv_roomsrc));
        Glide.with(mContext)//主播头像
                .load(bean.getAvatar_mid())
                .crossFade()
                .centerCrop()
                .into((ImageView) viewHolder.getView(R.id.live_iv_avatar));
    }
}
