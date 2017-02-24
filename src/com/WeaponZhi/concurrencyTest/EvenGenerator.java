package com.WeaponZhi.concurrencyTest;

/**
 * EvenGenerator 产生一系列偶数的Generator
 * <p>
 * author:张冠之<br>
 * time: 2017/02/23 15:27 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class EvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;
    @Override
    public int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
