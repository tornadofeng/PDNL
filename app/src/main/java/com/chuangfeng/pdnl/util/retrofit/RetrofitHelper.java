package com.chuangfeng.pdnl.util.retrofit;

import com.chuangfeng.pdnl.live.mvp.model.bean.LiveBaseBean;
import com.chuangfeng.pdnl.util.retrofit.exception.RetrofitException;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chuangfeng on 2017/4/6.
 */

public class RetrofitHelper {

    public static final String BASE_EXPLORE_URL = "http://api.douban.com/v2/movie/";
    public static final String BASE_LIVE_URL = "http://api.maxjia.com";
    public static final String BASE_USER_URL = "http://api.douban.com/v2/movie/";
    public static final String BASE_DANMU_URL = "http://api.douban.com/v2/movie/";

    private static Retrofit explore = null;
    private static Retrofit live = null;
    private static Retrofit user = null;

    public static Retrofit getExploreHelper() {
        if (explore == null) {
            synchronized (RetrofitHelper.class) {
                explore = new Retrofit.Builder()
                        .client(new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build())
                        .baseUrl(BASE_EXPLORE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
            }
        }
        return explore;
    }

    public static Retrofit getLiveHelper() {
        if (live == null) {
            synchronized (RetrofitHelper.class) {
                live = new Retrofit.Builder()
                        .client(new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build())
                        .baseUrl(BASE_LIVE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
            }
        }
        return live;
    }

    public static Retrofit getUserHelper() {
        if (user == null) {
            synchronized (RetrofitHelper.class) {
                user = new Retrofit.Builder()
                        .client(new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build())
                        .baseUrl(BASE_USER_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
            }
        }
        return user;
    }


    public static <T> Observable.Transformer<LiveBaseBean<T>, T> handleLiveResult() {
        return new Observable.Transformer<LiveBaseBean<T>, T>() {//被观察者：XXBasrBean<T> --> T
            @Override
            public Observable<T> call(Observable<LiveBaseBean<T>> baseBeanObservable) {//Step 1：获取Observable<XXBaseBean<T>>
                return baseBeanObservable
                        .flatMap(new Func1<LiveBaseBean<T>, Observable<T>>() {//Step 2：把Observable<XXBaseBean<T>>转换为Observable<T>
                            @Override
                            public Observable<T> call(final LiveBaseBean<T> baseBean) {//Step 3:根据返回码决定是否发送事件
                                if (baseBean.getStatus().equals("ok")){// ok：成功
                                    return Observable.create(new Observable.OnSubscribe<T>() {
                                        @Override
                                        public void call(Subscriber<? super T> subscriber) {
                                            try {
                                                subscriber.onNext(baseBean.getResult());//发送事件给Subscriber
                                                subscriber.onCompleted();
                                            } catch (Exception e) {
                                                subscriber.onError(e);
                                            }
                                        }
                                    });
                                } else {//error:错误Exception
                                    return Observable.error(new RetrofitException(baseBean.getMsg()));
                                }

                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
