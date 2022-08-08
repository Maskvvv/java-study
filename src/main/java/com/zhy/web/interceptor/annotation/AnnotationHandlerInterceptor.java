package com.zhy.web.interceptor.annotation;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.zhy.web.filter.MyServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

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
}
