package com.WeaponZhi.concurrencyTest;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * CallableDemo 从任务中产生返回值，通过实现Callable接口，调用call()方法返回参数
 * <p>
 * author:张冠之<br>
 * time: 2017/02/21 15:04 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            results.add(exec.submit(new TaskWithResult(i)));
        for (Future<String> fs : results)
            try {
                //可以调用isDone()方法来查询Future是否已经完成，如果不调用，get()将阻塞
                //直到结果准备就绪
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
    }
}

class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }
}
