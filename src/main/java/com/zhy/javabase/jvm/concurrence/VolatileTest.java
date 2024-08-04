package com.zhy.javabase.jvm.concurrence;

import java.util.concurrent.CountDownLatch;

/**
 * <p> volatile变量自增运算测试 </p>
 *
 * @author zhouhongyin
 * @since 2023/10/29 21:09
 */
public class VolatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];

        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    increase();
                }

                countDownLatch.countDown();
            });
            threads[i].start();
        }


        // 等待所有累加线程都结束
        countDownLatch.await();
        System.out.println(race);
    }
}
