package com.WeaponZhi.concurrencyTest;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * DeadlockingDiningPhilosophers 死锁版本的哲学家问题
 * {@link Chopstick},{@link Philosopher}
 * <p>
 * author: 张冠之 <br>
 * time:   2017/03/02 10:29 <br>
 * e-mail: 584098488@qq.com <br>
 * </p>
 */

public class DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws InterruptedException, IOException {
        int ponder = 5;
        if (args.length > 0)
            ponder = Integer.parseInt(args[0]);
        int size = 5;
        if (args.length > 1)
            size = Integer.parseInt(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++)
            sticks[i] = new Chopstick();
        for (int i = 0; i < size; i++)
            exec.execute(new Philosopher(
                    sticks[i], sticks[(i + 1) % size], i, ponder));
        if (args.length == 3 && args[2].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
