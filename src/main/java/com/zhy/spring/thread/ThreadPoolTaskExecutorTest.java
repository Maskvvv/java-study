package com.zhy.spring.thread;

import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p> 线程池拒绝策略 </p>
 *
 * @author zhouhongyin
 * @since 2023/12/27 9:13
 */
public class ThreadPoolTaskExecutorTest {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(1);
        // 最大线程数
        taskExecutor.setMaxPoolSize(1);
        // 队列大小 默认使用LinkedBlockingQueue
        taskExecutor.setQueueCapacity(1);
        // 线程最大空闲时间
        taskExecutor.setKeepAliveSeconds(300);
        // 拒绝策略 默认new ThreadPoolExecutor.AbortPolicy()
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 线程名称前缀
        taskExecutor.setThreadNamePrefix("My-Task-Executor-");

        taskExecutor.initialize();


        taskExecutor.execute(() -> {
            try {
                Thread.sleep(20 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        taskExecutor.execute(() -> {
            try {
                Thread.sleep(20 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            taskExecutor.execute(() -> {
                try {
                    Thread.sleep(20 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (TaskRejectedException e) {
            System.out.println("被拒绝了");
        }


    }

}
