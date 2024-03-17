package com.zhy.spring.thread;


import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p> 监测线程池运行状态 </p>
 *
 * @author zhouhongyin
 * @since 2024/2/16 17:02
 */
@Slf4j
@Configuration
public class MonitorThreadPool {

    //@Bean
    public ScheduledExecutorService printThreadPoolStatus(Map<String, ThreadPoolExecutor> threadPools) {


        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new DefaultThreadFactory("print-images/thread-pool-status"));
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            for (Map.Entry<String, ThreadPoolExecutor> entry : threadPools.entrySet()) {
                String threadPoolName = entry.getKey();
                ThreadPoolExecutor threadPool = entry.getValue();

                log.info("=========================");
                log.info("ThreadPool name: [{}]", threadPoolName);
                log.info("ThreadPool Size: [{}]", threadPool.getPoolSize());
                log.info("Active Threads: {}", threadPool.getActiveCount());
                log.info("Number of Tasks : {}", threadPool.getCompletedTaskCount());
                log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());
                log.info("=========================");
            }


        }, 0, 1, TimeUnit.SECONDS);

        return scheduledExecutorService;

    }

}
