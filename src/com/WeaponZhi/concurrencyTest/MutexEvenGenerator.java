package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MutexEvenGenerator 使用显示的Lock对象
 * 虽然代码比synchronized关键字要多，但它有机会去做一些清理工作
 * <p>
 * author:张冠之<br>
 * time: 2017/02/24 08:47 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class MutexEvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
        try{
            ++currentEvenValue;
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}
