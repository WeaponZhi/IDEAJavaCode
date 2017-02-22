package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.TimeUnit;

/**
 * DaemonsDontRunFinally 直接运行不会走finally子句，但当把setDaemon()注释了后，将会走finally子句
 * <p>
 * author:张冠之<br>
 * time: 2017/02/22 14:46 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class DaemonsDontRunFinally {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}

class ADaemon implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("This should always run?");
        }
    }
}
