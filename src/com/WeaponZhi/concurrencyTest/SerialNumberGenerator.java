package com.WeaponZhi.concurrencyTest;

/**
 * SerialNumberGenerator 产生序列数字的类
 * <p>
 * author:张冠之<br>
 * time: 2017/02/24 14:01 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static int nextSerialNumber(){
        return serialNumber++;
    }
}
