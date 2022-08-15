package com.zhy.spring.thread;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2022/8/15 21:32
 */
@RestController
@RequestMapping("thread_pool")
public class ThreadPoolController {

    @Resource
    private ThreadPoolService threadPoolService;

    @GetMapping("test1")
    public String threadPoolTest1() throws InterruptedException {
        threadPoolService.run();

        return "success";
    }

}
