package com.chuangfeng.pdnl.live.bean;

/**
 * Created by chuangfeng on 2017/4/10.
 */

public class LiveBaseBean<T> {
    /**
     * error : 0
     * message : 成功
     * data : {}
     */
    private int error;
    private T data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
