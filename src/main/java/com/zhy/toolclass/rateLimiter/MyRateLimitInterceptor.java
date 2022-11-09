package com.zhy.toolclass.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
@Component
public class MyRateLimitInterceptor implements HandlerInterceptor {
    private final RateLimiter rateLimiter = RateLimiter.create(1);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!rateLimiter.tryAcquire()){
            log.info("限流策略！");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("系统繁忙");
            response.getWriter().close();
            return false;
        }
        return true;
    }
}
