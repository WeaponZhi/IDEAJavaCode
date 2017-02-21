package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SleepingTask 休眠测试类
 * <p>
 * author:张冠之<br>
 * time: 2017/02/21 15:50 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class SleepingTask {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new SleepingTaskRunnable());
        exec.shutdown();
    }
}

class SleepingTaskRunnable extends LiftOff {
    @Override
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.print(status());
//                old-style
//                Thread.sleep(100);
//                Java SE5/6-style
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }
}
