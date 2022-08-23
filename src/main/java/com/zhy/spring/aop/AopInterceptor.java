package com.zhy.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhouhongyin
 * @since 2022/8/20 16:37
 */
@Component
@Slf4j
public class AopInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("-----------------Interceptor-begin-----------------");

        log.info("-----------------Interceptor-end-----------------");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
