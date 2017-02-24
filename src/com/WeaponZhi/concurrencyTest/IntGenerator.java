package com.WeaponZhi.concurrencyTest;

/**
 * IntGenerator
 * <p>
 * author:张冠之<br>
 * time: 2017/02/23 15:08 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel(){ canceled = true;}
    public boolean isCanceled(){return canceled;}
}
