package com.chuangfeng.pdnl.live.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chuangfeng.pdnl.R;
import com.chuangfeng.pdnl.live.mvp.model.bean.LiveListItemBean;

import java.util.List;

/**
 * Created by chuangfeng on 2017/4/11.
 * 直播房间列表的Adapter
 */

public class LiveListAdapter extends BaseQuickAdapter<LiveListItemBean> {
    public LiveListAdapter(List<LiveListItemBean> data) {
        super(R.layout.item_live_list_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, LiveListItemBean bean) {
        viewHolder.setText(R.id.live_tv_roomname, bean.getLive_title())//房间名称
                .setText(R.id.live_tv_nickname, bean.getLive_nickname())//主播昵称
                .setText(R.id.live_tv_online, String.valueOf(bean.getLive_online()))//在线人数
                .setOnClickListener(R.id.live_cardview, new OnItemChildClickListener());//添加子Item点击监听，在UI中实现回调接口
        Glide.with(mContext)//直播房间截图
                .load(bean.getLive_img())
                .crossFade()
                .centerCrop()
                .into((ImageView) viewHolder.getView(R.id.live_iv_roomsrc));
        Glide.with(mContext)//主播头像
                .load(bean.getLive_userimg())
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.ic_avatar_default_40dp)
                .into((ImageView) viewHolder.getView(R.id.live_iv_avatar));
    }
}
