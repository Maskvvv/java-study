package com.zhy.spring.aop;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2022/8/22 17:17
 */
@Component
public class AopWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private AopInterceptor aopInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(aopInterceptor).addPathPatterns("/aop/**");
    }
}
