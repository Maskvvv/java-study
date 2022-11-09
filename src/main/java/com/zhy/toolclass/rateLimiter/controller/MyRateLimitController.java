package com.zhy.toolclass.rateLimiter.controller;

import com.zhy.toolclass.rateLimiter.MyRateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhongyin
 * @since 2022/9/30 14:08
 */
@RestController
@RequestMapping("rate_limit")
public class MyRateLimitController {

    @GetMapping
    @MyRateLimit
    public String rate() {
        return "success";
    }

    @GetMapping("inter")
    public String rateInterceptor() {
        return "success";
    }
}
