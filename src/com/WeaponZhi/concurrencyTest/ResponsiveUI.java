package com.WeaponZhi.concurrencyTest;

import java.io.IOException;

/**
 * ResponsiveUI 创建有响应的用户界面
 * <p>
 * author:张冠之<br>
 * time: 2017/02/23 10:37 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class ResponsiveUI extends Thread {
    private static volatile double d = 1;

    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true)
            d = d + (Math.PI + Math.E) / d;
    }

    public static void main(String[] args) throws IOException {
//        new ResponsiveUI();
        new UnresponsiveUI();
        System.in.read();
        System.out.println(d);
    }
}

class UnresponsiveUI {
    private volatile double d = 1;

    public UnresponsiveUI() throws IOException {
        while (d > 0)
            d = d + (Math.PI + Math.E) / d;
        System.in.read();
    }
}
