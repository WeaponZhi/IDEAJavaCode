package com.WeaponZhi.util;

import java.util.concurrent.ThreadFactory;

/**
 * DaemonThreadFactory 定制ThreadFactory
 * <p>
 * author:张冠之<br>
 * time: 2017/02/22 14:14 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class DaemonThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
