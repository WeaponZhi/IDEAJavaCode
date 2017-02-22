package com.WeaponZhi.concurrencyTest;

/**
 * SimpleThread 简单情况下，可以使用直接从Thread继承这种可替换的方式
 * <p>
 * author:张冠之<br>
 * time: 2017/02/22 15:07 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */
public class SimpleThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread() {
        super(Integer.toString(++threadCount));
        start();
    }

    @Override
    public String toString() {
        return "#" + getName() + "(" + countDown + "), ";
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
