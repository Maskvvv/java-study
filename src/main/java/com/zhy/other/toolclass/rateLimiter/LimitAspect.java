package com.zhy.other.toolclass.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 限流 AOP
 */
@Component
@Scope
@Aspect
public class LimitAspect {
    //引用RateLimiter，内部是基于令牌桶实现的
    private static RateLimiter rateLimiter = RateLimiter.create(1.0);

    //定义限流注解的pointcut
    @Pointcut("@annotation(com.zhy.other.toolclass.rateLimiter.MyRateLimit)")
    public void MyRateLimitAspect() {
    }

    @Around("MyRateLimitAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Boolean flag = rateLimiter.tryAcquire();
        Object obj = null;
        try {
            if (flag) {
                obj = joinPoint.proceed();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }
}
