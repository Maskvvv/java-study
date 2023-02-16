package com.zhy.other.toolclass.rateLimiter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2022/6/29 17:19
 */
@Order(1)
@Component
public class MyRateLimitWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private MyRateLimitInterceptor myRateLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myRateLimitInterceptor).addPathPatterns("/rate_limit/inter");
    }
}
