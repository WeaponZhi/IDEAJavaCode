package com.WeaponZhi.concurrencyTest;

/**
 * LiftOff 通过Runnable接口实现显示发射之前的倒计时
 * <p>
 * author:张冠之<br>
 * time: 2017/02/20 17:04 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class LiftOff implements Runnable {
    protected int countDown = 10;//Default
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    @Override
    public void run() {
        while (countDown-- > 0 ){
            System.out.print(status());
            Thread.yield();
        }
    }
}
