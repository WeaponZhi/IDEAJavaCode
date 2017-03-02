package com.WeaponZhi.concurrencyTest;

/**
 * Chopstick 哲学家问题中的筷子类
 * 哲学家类：{@link Philosopher}
 * <p>
 * author: 张冠之 <br>
 * time:   2017/03/02 10:08 <br>
 * e-mail: 584098488@qq.com <br>
 * </p>
 */

public class Chopstick {
    private boolean taken = false;

    public synchronized void taken() throws InterruptedException {
        while (taken)
            wait();
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
