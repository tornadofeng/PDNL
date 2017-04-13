package com.chuangfeng.pdnl.live.danmu.utils;

import com.chuangfeng.pdnl.live.danmu.client.DanmuClient;

/**
 * Created by chuangfeng on 2017/4/13.
 */

public class KeepGetMsgThread extends Thread {

    @Override
    public void run() {

        ////获取弹幕客户端
        DanmuClient danmuClient = DanmuClient.getClient();

        //判断客户端就绪状态
        while(danmuClient.getReady())
        {
            //获取服务器发送的弹幕信息
            danmuClient.getServerMsg();
        }
    }
}
