package com.WeaponZhi.concurrencyTest;

/**
 * MoreBasicThreads 多线程情况下的发射情况
 * <p>
 * author:张冠之<br>
 * time: 2017/02/21 14:30 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for LiftOff");
    }
}
