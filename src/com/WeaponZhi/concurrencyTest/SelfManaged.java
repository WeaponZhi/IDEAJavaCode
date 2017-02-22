package com.WeaponZhi.concurrencyTest;

/**
 * SelfManaged 自管理的Runnable
 * <p>
 * author:张冠之<br>
 * time: 2017/02/22 15:47 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class SelfManaged implements Runnable {
    private int countDown = 5;
    private Thread t = new Thread(this);

    public SelfManaged() {
        t.start();
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + "(" + countDown + "), ";
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
        for (int i = 0; i < 5; i++)
            new SelfManaged();
    }
}
