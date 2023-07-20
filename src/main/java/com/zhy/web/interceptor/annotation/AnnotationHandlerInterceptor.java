package com.zhy.web.interceptor.annotation;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.zhy.web.filter.MyServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 注解拦截器测试
 */
@Component
@Slf4j
public class AnnotationHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        PermissionsAnnotation methodAnnotation = handlerMethod.getMethodAnnotation(PermissionsAnnotation.class);
        String[] value = methodAnnotation.value();
        log.info("methodAnnotation:{}", value);

        String id = request.getParameter("id");
        log.info("id:{}", id);

        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        MyServletRequestWrapper requestWrapper = (MyServletRequestWrapper) request;

        AnnotationParam annotationParam = JSON.parseObject(requestWrapper.getBody(), AnnotationParam.class);
        log.info("annotationParam:{}", annotationParam);

        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(2);

        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
            System.out.println(time + ":" + rateLimiter.tryAcquire());
            //Thread.sleep(250);
        }

    }
}
