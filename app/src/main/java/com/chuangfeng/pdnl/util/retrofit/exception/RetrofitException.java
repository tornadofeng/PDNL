package com.chuangfeng.pdnl.util.retrofit.exception;

/**
 * 统一处理网络异常errorCode
 * Created by chuangfeng on 2017/4/6.
 */

public class RetrofitException extends RuntimeException {

    public static final int ERROR_TOKEN = 100;
    public static final int ERROR_NETWORK = ERROR_TOKEN + 1;
    public static final int ERROR_SERVER = ERROR_NETWORK + 1;

    public RetrofitException(int code) {
        this(getErrorMessage(code));
    }

    public RetrofitException(String msg) {
        super(msg);
    }

    private static String getErrorMessage(int code) {
        String msg = "";
        switch (code) {
            case ERROR_TOKEN:
                msg = "token过期，请重新登录！";
                break;
            case ERROR_NETWORK:
                msg = "网络错误，请检查网络连接设置！";
                break;
            case ERROR_SERVER:
                msg = "服务器内部错误，请稍后再试！";
                break;
            default:
                msg = "未知错误！";
                break;
        }
        return msg;
    }
}
