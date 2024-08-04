package com.zhy.javabase.juc.lock.countdownlatch;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  CountDownLatch 锁的使用
 *
 * @author zhouhongyin
 * @since 2023/2/15 22:46
 */
public class LatchSample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);

        // 任务一
        threadPool.execute(new FirstBatchWorker(latch));
        // 任务一
        threadPool.execute(new SecondBatchWorker(latch));

        // 等待任务 1 和 2 执行完毕
        latch.await();
        System.out.println("first and second batch finish");
    }
}

class FirstBatchWorker implements Runnable {
    private CountDownLatch latch;

    public FirstBatchWorker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("First batch executed!");
        latch.countDown();
    }
}

class SecondBatchWorker implements Runnable {
    private CountDownLatch latch;

    public SecondBatchWorker(CountDownLatch latch) {
        this.latch = latch;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Second batch executed!");
        Thread.sleep(1000);
        latch.countDown();
    }
}
