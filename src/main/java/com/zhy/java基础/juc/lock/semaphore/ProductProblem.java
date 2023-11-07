package com.zhy.java基础.juc.lock.semaphore;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p> producer consumer problem </p>
 *
 * @author zhouhongyin
 * @since 2023/11/7 10:40
 */
public class ProductProblem {
    public static final AtomicInteger message = new AtomicInteger();

    // 互斥信号量
    public static final Semaphore mutex = new Semaphore(1);
    // 同步信号量 表示空闲缓冲区的数量
    public static final Semaphore empty = new Semaphore(10);
    // 同步信号量 表示产品的数量
    public static final Semaphore full = new Semaphore(0);

    public static final Deque<String> queue = new ArrayDeque<>();

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

        int producerCount = 0;
        int consumerCount = 0;
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                threads.add(new Thread(new Producer(), "producer" + producerCount++));
            } else {
                threads.add(new Thread(new Consumer(), "consumer" + consumerCount++));
            }
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("end");

    }


    public static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {

                    empty.acquire();
                    mutex.acquire();


                    System.out.println("生产者-" + Thread.currentThread().getName() + "-生产");
                    queue.addLast(String.valueOf(message.getAndIncrement()));

                    full.release();
                    mutex.release();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {

                    full.acquire();
                    mutex.acquire();

                    System.out.println("消费者-" + Thread.currentThread().getName() + "-消费: " + queue.removeFirst());

                    empty.release();
                    mutex.release();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }


}
