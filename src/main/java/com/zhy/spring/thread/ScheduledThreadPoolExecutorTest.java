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
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, new ThreadPoolExecutor.DiscardOldestPolicy());

        executor.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName());
        }, 2, 3, TimeUnit.SECONDS);

        System.in.read();
    }
}
