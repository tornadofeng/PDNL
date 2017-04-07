package com.chuangfeng.pdnl.util.retrofit.bean;

/**
 * Created by chuangfeng on 2017/4/8.
 */

public class BaseBean<T> {

    /**
     * code : 0
     * message : 成功
     * data : {}
     */

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
