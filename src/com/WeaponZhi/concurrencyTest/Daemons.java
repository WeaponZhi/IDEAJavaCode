package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.TimeUnit;

/**
 * Daemons 通过isDaemon()方法来确定线程是否是一个后台线程
 * <p>
 * author:张冠之<br>
 * time: 2017/02/22 14:32 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon = "+d.isDaemon()+", ");
        TimeUnit.SECONDS.sleep(1);
    }
}

class DaemonSpawn implements Runnable {

    @Override
    public void run() {
        while (true)
            Thread.yield();
    }
}

class Daemon implements Runnable {
    private Thread[] t = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.println("DaemonSpawn " + i + " started, ");
        }
        for (int i = 0; i < t.length; i++)
            System.out.println("t[" + i + "].isDaemon() = " +
                    t[i].isDaemon() + ", ");
        while (true)
            Thread.yield();
    }
}
