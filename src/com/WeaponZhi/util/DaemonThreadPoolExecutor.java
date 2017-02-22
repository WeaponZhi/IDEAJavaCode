package com.WeaponZhi.util;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * DaemonThreadPoolExecutor
 * <p>
 * author:张冠之<br>
 * time: 2017/02/22 14:28 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class DaemonThreadPoolExecutor extends ThreadPoolExecutor{
    public DaemonThreadPoolExecutor(){
        super(0,Integer.MAX_VALUE,60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new DaemonThreadFactory());
    }
}
