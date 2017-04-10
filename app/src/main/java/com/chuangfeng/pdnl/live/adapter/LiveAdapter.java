package com.chuangfeng.pdnl.live.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chuangfeng.pdnl.R;
import com.chuangfeng.pdnl.live.bean.LiveBean;

import java.util.List;

/**
 * Created by chuangfeng on 2017/4/10.
 */

public class LiveAdapter extends BaseQuickAdapter<LiveBean, BaseViewHolder> {

    public LiveAdapter(List<LiveBean> data) {
        super(R.layout.item_live_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveBean liveBean) {

    }


}
