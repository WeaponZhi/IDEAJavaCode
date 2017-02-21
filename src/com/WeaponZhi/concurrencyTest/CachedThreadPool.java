package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CachedThreadPool 使用Executor来创建Thread对象
 * 单个Executor被用来创建和管理系统中所有的任务
 * <p>
 * author:张冠之<br>
 * time: 2017/02/21 14:43 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class CachedThreadPool {
    public static void main(String[] args) {
        //ExecutorService是具有生命周期的Executor，例如关闭
        //通过Executors静态方法创建ExecutorService，这个方法可以确定其Executor类型
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i<5;i++)
            exec.execute(new LiftOff());
        //shutdown()方法的调用可以防止新任务被提交给这个Executor
        exec.shutdown();
    }
}
