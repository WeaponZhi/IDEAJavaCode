package com.WeaponZhi.concurrencyTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Philosopher 哲学家类
 * 筷子类：{@link Chopstick}
 * {@link DeadlockingDiningPhilosophers 死锁版本}
 * {@link FixedDiningPhilosophers 非死锁版本}
 * <p>
 * author: 张冠之 <br>
 * time:   2017/03/02 10:10 <br>
 * e-mail: 584098488@qq.com <br>
 * </p>
 */

public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);

    public Philosopher(Chopstick left, Chopstick right, int ident, int ponder) {
        this.left = left;
        this.right = right;
        this.id = ident;
        ponderFactor = ponder;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                pause();
                System.out.println(this + " " + "grabbing right");
                right.taken();
                System.out.println(this + " " + "grabbing left");
                left.taken();
                System.out.println(this + " " + "eating");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " " + "exiting via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}
