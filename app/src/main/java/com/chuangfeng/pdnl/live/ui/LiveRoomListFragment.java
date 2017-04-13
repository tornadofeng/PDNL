package com.chuangfeng.pdnl.live.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chuangfeng.pdnl.R;
import com.chuangfeng.pdnl.live.adapter.LiveRoomAdapter;
import com.chuangfeng.pdnl.live.api.LiveAPI;
import com.chuangfeng.pdnl.live.bean.LiveRoomBean;
import com.chuangfeng.pdnl.live.mvp.presenter.impl.LiveRoomListPresenterImpl;
import com.chuangfeng.pdnl.live.mvp.view.ILiveRoomListFragment;
import com.chuangfeng.pdnl.util.ToastUtil;
import com.chuangfeng.pdnl.widget.animation.CustionAnimation;
import com.chuangfeng.pdnl.widget.fragment.LazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chuangfeng on 2017/4/11.
 */

public class LiveRoomListFragment extends LazyFragment implements ILiveRoomListFragment,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{

    private static final String CATE_ID = "cate_id";
    private String cate_id;
    private int offset = 0;//用于记录分页偏移量
    private List<LiveRoomBean> roomBeanList = new ArrayList<>();

    private Context context;
    private LiveRoomListPresenterImpl presenter;

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    LiveRoomAdapter adapter;

    public static LiveRoomListFragment newInstance() {
        return newInstance("");
    }

    public static LiveRoomListFragment newInstance(String cate_id) {
        LiveRoomListFragment fragment = new LiveRoomListFragment();
        Bundle args = new Bundle();
        args.putString(CATE_ID, cate_id);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_roomlist, container, false);
        ButterKnife.bind(this, view);

        context = view.getContext();
        cate_id = getArguments().getString(CATE_ID);//得到传入的cate_id

        initMVP();
        initRefreshView();
        initRecyclerView();

        return view;
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        initMVP();
        initRecyclerView();
        if (savedInstanceState == null) {
            refreshLayout.setProgressViewOffset(false, 0, 30);// 这句话是为了，第一次进入页面初始化数据的时候显示加载进度条
            refreshLayout.setRefreshing(true);
            if (TextUtils.isEmpty(cate_id)) {//传入cate_id为空，请求全部直播
                presenter.getAllRoomList(offset, LiveAPI.LIMIT, LiveAPI.CLIENT_SYS);
            } else {//不为空，根据cate_id分类请求直播数据
                presenter.getColumnRoomList(cate_id, offset, LiveAPI.LIMIT, LiveAPI.CLIENT_SYS);
            }
        }
    }

    private void initMVP() {
        presenter = new LiveRoomListPresenterImpl(context, this);
    }

    private void initRefreshView() {
        refreshLayout.setColorSchemeResources(R.color.color_primary);
        refreshLayout.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        adapter = new LiveRoomAdapter(roomBeanList);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        adapter.openLoadAnimation(new CustionAnimation());
        adapter.isFirstOnly(true);
        adapter.openLoadMore(LiveAPI.LIMIT, true);//加载更多的触发条件
        adapter.setLoadingView(LayoutInflater.from(context).inflate(R.layout.layout_loading, (ViewGroup) recyclerView.getParent(), false));
        adapter.setOnLoadMoreListener(this);
        adapter.setEmptyView(LayoutInflater.from(context).inflate(R.layout.layout_empty, (ViewGroup) recyclerView.getParent(), false));
        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                roomBeanList.get(i);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void updateRecyclerView(List<LiveRoomBean> list) {
        refreshLayout.setRefreshing(false);
        roomBeanList.addAll(offset, list);//在roomBeanList的尾部添加
        offset = roomBeanList.size();
        if (list.size() < LiveAPI.LIMIT) {
            adapter.notifyDataChangedAfterLoadMore(false);
            adapter.addFooterView(LayoutInflater.from(context).inflate(R.layout.layout_footer, (ViewGroup) recyclerView.getParent(), false));
            return;
        }
        adapter.notifyDataChangedAfterLoadMore(true);
    }

    @Override
    public void showError(String message) {
        refreshLayout.setRefreshing(false);
        ToastUtil.show(context, message);
    }

    @Override
    public void onRefresh() {
        offset = 0;//重置偏移量
        roomBeanList.clear();//清空原数据
        adapter.removeAllFooterView();
        refreshLayout.setRefreshing(true);
        if (TextUtils.isEmpty(cate_id)){//传入cate_id为空，请求全部直播
            presenter.getAllRoomList(offset, LiveAPI.LIMIT, LiveAPI.CLIENT_SYS);
        }else {//不为空，根据cate_id分类请求直播数据
            presenter.getColumnRoomList(cate_id, offset, LiveAPI.LIMIT, LiveAPI.CLIENT_SYS);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (TextUtils.isEmpty(cate_id)){//传入cate_id为空，请求全部直播
            presenter.getAllRoomList(offset, LiveAPI.LIMIT, LiveAPI.CLIENT_SYS);
        }else {//不为空，根据cate_id分类请求直播数据
            presenter.getColumnRoomList(cate_id, offset, LiveAPI.LIMIT, LiveAPI.CLIENT_SYS);
        }
    }
}
