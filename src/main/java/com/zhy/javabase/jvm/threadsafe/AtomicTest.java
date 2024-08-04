package com.zhy.javabase.jvm.threadsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p> Atomic increase test </p>
 *
 * 非阻塞同步
 *
 * @author zhouhongyin
 * @since 2023/10/30 15:56
 */
public class AtomicTest {
    public static AtomicInteger race = new AtomicInteger(0);

    public static void increase() {
        race.incrementAndGet();
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1)
            Thread.yield();
        System.out.println(race);
    }
}
