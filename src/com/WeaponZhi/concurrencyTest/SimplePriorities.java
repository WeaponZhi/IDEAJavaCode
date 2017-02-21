package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SimplePriorities 优先级
 * <p>
 * author:张冠之<br>
 * time: 2017/02/21 16:18 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class SimplePriorities {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new SimplePrioritiesClass(Thread.MIN_PRIORITY));
        exec.execute(new SimplePrioritiesClass(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}

class SimplePrioritiesClass implements Runnable {
    private int countDown = 5;
    private volatile double d;
    private int priority;

    public SimplePrioritiesClass(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }
}