package com.WeaponZhi.concurrencyTest;

/**
 * BasicThreads 基础Thread
 * <p>
 * author:张冠之<br>
 * time: 2017/02/21 14:22 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");
    }
}
