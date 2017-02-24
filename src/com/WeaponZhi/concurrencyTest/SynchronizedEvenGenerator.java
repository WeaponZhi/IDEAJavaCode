package com.WeaponZhi.concurrencyTest;

/**
 * SynchronizedEvenGenerator 同步控制EvenGenerator
 * <p>
 * author:张冠之<br>
 * time: 2017/02/24 08:39 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class SynchronizedEvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;

    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
