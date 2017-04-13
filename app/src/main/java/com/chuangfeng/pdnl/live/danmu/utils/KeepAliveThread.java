package com.chuangfeng.pdnl.live.danmu.utils;

import com.chuangfeng.pdnl.live.danmu.client.DanmuClient;

/**
 * Created by chuangfeng on 2017/4/13.
 */

public class KeepAliveThread extends Thread {

    @Override
    public void run() {

        //获取弹幕客户端
        DanmuClient danmuClient = DanmuClient.getClient();

        //判断客户端就绪状态
        while(danmuClient.getReady())
        {
            //发送心跳保持协议给服务器端
            danmuClient.keepAlive();
            try
            {
                //设置间隔45秒再发送心跳协议
                Thread.sleep(45000);        //keep live at least once per minute
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
