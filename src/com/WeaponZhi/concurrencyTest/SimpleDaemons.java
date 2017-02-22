package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.TimeUnit;

/**
 * SimpleDaemons 后台线程测试
 * <p>
 * author:张冠之<br>
 * time: 2017/02/22 10:44 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);//必须在start之前调用
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
