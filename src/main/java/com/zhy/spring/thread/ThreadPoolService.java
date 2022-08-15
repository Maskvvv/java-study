package com.zhy.spring.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author zhouhongyin
 * @since 2022/8/15 21:33
 */
@Service
@Slf4j
public class ThreadPoolService {

    @Async("threadPoolTaskExecutor")
    public void run() throws InterruptedException {
        log.info(Thread.currentThread() + "-ThreadPoolService-start");

        Thread.sleep(5000);

        log.info(Thread.currentThread() + "-ThreadPoolService-end");
    }


}
