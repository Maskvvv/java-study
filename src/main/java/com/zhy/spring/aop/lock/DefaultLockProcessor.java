package com.zhy.spring.aop.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * <p> Lock 单机实现(没有锁的过期时间) </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 10:59
 */
@Component
public class DefaultLockProcessor implements AthenaLockProcessor {

    @Override
    public Object proceed(ProceedingJoinPoint joinPoint, String key, long leaseTime) throws Throwable {
        synchronized (key.intern()) {
            return joinPoint.proceed();
        }
    }
}
