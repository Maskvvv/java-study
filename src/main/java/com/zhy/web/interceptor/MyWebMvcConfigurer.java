package com.zhy.web.interceptor;

import com.zhy.web.interceptor.annotation.AnnotationHandlerInterceptor;
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
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private MyHandlerInterceptor myHandlerInterceptor;

    @Resource
    private AnnotationHandlerInterceptor annotationHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myHandlerInterceptor).addPathPatterns("/filter/path2");
        registry.addInterceptor(annotationHandlerInterceptor).addPathPatterns("/annotation/**");
    }
}
