package com.zhy.spring.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouhongyin
 * @since 2022/8/15 21:32
 */
@Slf4j
@RestController
@RequestMapping("thread_pool")
public class ThreadPoolController {

    @Resource
    private ThreadPoolService threadPoolService;

    @Resource
    private ScheduledExecutorService scheduledExecutorService;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping("test1")
    public String threadPoolTest1() throws InterruptedException {
        threadPoolService.run();

        return "success";
    }

    @GetMapping("test2")
    public String threadPoolTest2() throws InterruptedException {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            threadPoolTaskExecutor.execute(() -> {
                log.info("scheduledExecutorService");
            });
        }, 1, 1,  TimeUnit.MILLISECONDS);//（频率可以适当调节）
        return "success";
    }

}
