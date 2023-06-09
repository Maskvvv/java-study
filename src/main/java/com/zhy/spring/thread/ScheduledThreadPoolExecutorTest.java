package com.zhy.spring.thread;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouhongyin
 * @since 2023/2/8 16:02
 */
public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) throws IOException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2, new ThreadPoolExecutor.DiscardOldestPolicy());
        executor.setMaximumPoolSize(2);

        executor.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + ":1");
        }, 2, 3, TimeUnit.SECONDS);

        executor.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + ":2");
        }, 2, 3, TimeUnit.SECONDS);

        executor.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + ":3");
        }, 2, 3, TimeUnit.SECONDS);

        System.in.read();
    }
}
