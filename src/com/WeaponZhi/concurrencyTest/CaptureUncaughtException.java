package com.WeaponZhi.concurrencyTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * CaptureUncaughtException 线程捕获异常
 * 如果想要在代码中处处使用相同的异常处理器，那么更简单的方法是在Thread类
 * 中设置一个静态域，并将这个处理器设置为默认的未捕获异常处理器
 * Thread.setDefaultUncaughtExceptionHandler()
 * <p>
 * author:张冠之<br>
 * time: 2017/02/23 11:06 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread());
    }
}

class ExceptionThread implements Runnable {

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        return t;
    }
}