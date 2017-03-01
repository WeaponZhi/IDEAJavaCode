package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BlockedMutex 在ReentrantLock上阻塞的任务具备可以被中断的能力
 * <p>
 * author: 张冠之 <br>
 * time:   2017/03/01 10:35 <br>
 * e-mail: 584098488@qq.com <br>
 * </p>
 */

class BlockedMutex {
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        lock.lock();
    }

    public void f() {
        try {
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted from lock acquisition in f()");
        }
    }
}

class Blocked implements Runnable {
    BlockedMutex blocked = new BlockedMutex();

    @Override
    public void run() {
        System.out.println("waiting for f() in BlockedMutex");
        blocked.f();
        System.out.println("Broken out of blocked call");
    }
}

public class Interrupting {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}