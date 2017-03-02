package com.WeaponZhi.concurrencyTest;


import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CacheProducerAndConsumer 实现一个带有缓冲区的生产者消费者问题
 * <p>
 * author: 张冠之 <br>
 * time:   2017/03/02 11:33 <br>
 * e-mail: 584098488@qq.com <br>
 * </p>
 */

public class CacheProducerAndConsumer {
    public LinkedList<Product> productQueue = new LinkedList<>();
    public static int count = 0;
    private final int MAX_SIZE = 10;
    public Producer producer = new Producer(this);
    public Consumer consumer = new Consumer(this);
    public ExecutorService exec = Executors.newCachedThreadPool();

    public boolean isFull() {
        return productQueue.size() == MAX_SIZE;
    }

    public CacheProducerAndConsumer() {
        try {
            exec.execute(producer);
            exec.execute(consumer);
            TimeUnit.SECONDS.sleep(10);
            exec.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new CacheProducerAndConsumer();
    }
}

class Product {
    private final int id;

    public Product(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product" + id;
    }
}

class Producer implements Runnable {
    private CacheProducerAndConsumer restaurant;

    public Producer(CacheProducerAndConsumer restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.isFull())
                        wait();
                }
                synchronized (restaurant.consumer) {
                    restaurant.productQueue.add(new Product(++restaurant.count));
                    restaurant.consumer.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Producer is interrupted");
        }
    }
}

class Consumer implements Runnable {
    private CacheProducerAndConsumer restaurant;

    public Consumer(CacheProducerAndConsumer restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.productQueue.isEmpty())
                        wait();
                    System.out.print("Consumer got " + restaurant.productQueue.peek());
                    System.out.println(" remain " + restaurant.productQueue.size());
                }
                synchronized (restaurant.producer) {
                    restaurant.productQueue.remove();
                    restaurant.producer.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(70);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer is interrupted");
        }
    }
}