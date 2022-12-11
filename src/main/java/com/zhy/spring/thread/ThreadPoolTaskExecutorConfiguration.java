package com.zhy.spring.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouhongyin
 * @since 2021/8/3 16:49
 */
@Configuration
public class ThreadPoolTaskExecutorConfiguration {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(5);
        // 最大线程数
        taskExecutor.setMaxPoolSize(15);
        // 队列大小 默认使用LinkedBlockingQueue
        taskExecutor.setQueueCapacity(100);
        // 线程最大空闲时间
        taskExecutor.setKeepAliveSeconds(300);
        // 拒绝策略 默认new ThreadPoolExecutor.AbortPolicy()
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程名称前缀
        taskExecutor.setThreadNamePrefix("My-Task-Executor-");
        //交给spring托管的会自动初始化，因为实现了InitializingBean接口
        //taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService(){
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        //核心线程池大小
        scheduledThreadPoolExecutor.setCorePoolSize(20);

        return scheduledThreadPoolExecutor;
    }

}
