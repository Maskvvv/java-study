package com.zhy.redis.queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouhongyin
 * @since 2022/8/9 17:08
 */
@Configuration
public class RedisQueueThreadPool {

    @Bean
    public ThreadPoolTaskExecutor redisQueueTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(1);
        // 最大线程数
        taskExecutor.setMaxPoolSize(15);
        // 队列大小 默认使用LinkedBlockingQueue
        taskExecutor.setQueueCapacity(100);
        // 线程最大空闲时间
        taskExecutor.setKeepAliveSeconds(300);
        // 拒绝策略 默认new ThreadPoolExecutor.AbortPolicy()
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程名称前缀
        taskExecutor.setThreadNamePrefix("redis-queue-thread-pool-");
        //交给spring托管的会自动初始化，因为实现了InitializingBean接口
        //taskExecutor.initialize();
        return taskExecutor;
    }

}
