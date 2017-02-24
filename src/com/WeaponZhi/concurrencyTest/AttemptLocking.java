package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AttemptLocking 尝试获取锁
 * <p>
 * author:张冠之<br>
 * time: 2017/02/24 09:27 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock()：" + captured);
        } finally {
            lock.unlock();
        }
    }

    public void timed() {
        boolean captured;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2,TimeUnit.SECONDS)：" + captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final AttemptLocking al = new AttemptLocking();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        });
//        thread.setDaemon(true);
        thread.start();
        Thread.yield();
        al.untimed();
        al.timed();
    }
}
