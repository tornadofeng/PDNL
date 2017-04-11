package com.chuangfeng.pdnl.live.api;

import com.chuangfeng.pdnl.live.bean.LiveBaseBean;
import com.chuangfeng.pdnl.live.bean.LiveIndicatorBean;
import com.chuangfeng.pdnl.live.bean.LiveRoomBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chuangfeng on 2017/4/11.
 */

public interface LiveAPI {

    public static final String CLIENT_SYS = "client_sys";
    public static final int LIMIT = 20;

    /**
     * 请求顶部栏目标题
     * @return
     */
    @GET("/api/v1/getColumnList?client_sys=android")
    Observable<LiveBaseBean<List<LiveIndicatorBean>>> getColumnList();

    /**
     * 请求全部直播
     * @param offset 分页偏移量
     * @return
     */
    @GET("/api/v1/live")
    Observable<LiveBaseBean<List<LiveRoomBean>>> getAllLiveList(
            @Query("offset") int offset,
            @Query("limit") int limit,
            @Query("client_sys") String client_sys
    );

    /**
     * 根据cate_id请求不同分类节目的直播
     * @param cate_id
     * @param offset
     * @param limit
     * @param client_sys
     * @return
     */
    @GET("/api/v1/getColumnRoom/{cate_id}")
    Observable<LiveBaseBean<List<LiveRoomBean>>> getColumnLiveList(
            @Path("cate_id") String cate_id,
            @Query("offset") int offset,
            @Query("limit") int limit,
            @Query("client_sys") String client_sys
    );
}
