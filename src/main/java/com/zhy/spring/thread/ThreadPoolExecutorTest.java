package com.zhy.spring.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouhongyin
 * @since 2022/12/11 20:15
 */
@Slf4j
public class ThreadPoolExecutorTest {

    @Test
    public void scheduledExecutorService() throws IOException {
        ScheduledExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                thread.setName("com.zhy.spring.thread");
                return thread;
            }
        });

        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {

            log.info("时间:{}", LocalDateTime.now());
        }, 1, 1, TimeUnit.SECONDS);

        System.in.read();
    }

    @Test
    public void scheduledExecutorService1() throws IOException {
        ScheduledExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                thread.setName("com.zhy.spring.thread");
                return thread;
            }
        });

        log.info("开始:{}", LocalDateTime.now());
        scheduledThreadPoolExecutor.schedule(() -> {
            log.info("task1:{}", LocalDateTime.now());
        }, 5,  TimeUnit.SECONDS);

        log.info("结束:{}", LocalDateTime.now());


        scheduledThreadPoolExecutor.schedule(() -> {
            log.info("task2:{}", LocalDateTime.now());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("task2:{}", LocalDateTime.now());
        }, 2,  TimeUnit.SECONDS);


        System.in.read();
    }

    @Test
    public void scheduledExecutorService2() throws IOException {
        ScheduledExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                thread.setName("com.zhy.spring.thread");
                return thread;
            }
        });

        log.info("开始:{}", LocalDateTime.now());
        scheduledThreadPoolExecutor.execute(() -> {

            log.info("时间:{}", LocalDateTime.now());
        });

        log.info("结束:{}", LocalDateTime.now());
        System.in.read();
    }

}
