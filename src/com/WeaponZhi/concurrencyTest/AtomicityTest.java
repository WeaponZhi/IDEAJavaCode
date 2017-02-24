package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AtomicityTest 盲目地使用原子性概念的测试
 * <p>
 * author:张冠之<br>
 * time: 2017/02/24 11:17 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class AtomicityTest implements Runnable {
    private int i = 0;

    public int getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true)
            evenIncrement();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
