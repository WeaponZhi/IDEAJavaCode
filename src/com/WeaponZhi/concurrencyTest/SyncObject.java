package com.WeaponZhi.concurrencyTest;

import java.util.Objects;

/**
 * SyncObject 在其他对象上同步
 * <p>
 * author:张冠之<br>
 * time: 2017/02/24 15:56 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

class DualSynch {
    private Object syncObject = new Object();

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}

public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread() {
            @Override
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }
}