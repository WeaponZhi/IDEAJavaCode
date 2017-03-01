package com.WeaponZhi.concurrencyTest;

/**
 * MultiLock 同一个互斥被同一个对象多次获得
 * <p>
 * author: 张冠之 <br>
 * time:   2017/03/01 10:27 <br>
 * e-mail: 584098488@qq.com <br>
 * </p>
 */

public class MultiLock {
    public synchronized void f1(int count) {
        if (count-- > 0) {
            System.out.println("f1() calling f2() with count" + count);
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if (count-- > 0) {
            System.out.println("f2() calling f1() with count" + count);
            f1(count);
        }
    }

    public static void main(String[] args) {
        final MultiLock multiLock = new MultiLock();
        new Thread() {
            @Override
            public void run() {
                multiLock.f1(10);
            }
        }.start();
    }
}
