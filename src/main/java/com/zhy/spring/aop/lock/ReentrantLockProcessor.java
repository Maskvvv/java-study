package com.zhy.spring.aop.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p> 可重入锁实现 </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 10:59
 */
@Primary
@Component
public class ReentrantLockProcessor implements AthenaLockProcessor {
    Map<String, ReentrantLock> lockMap = new HashMap<>();

    @Override
    public Object proceed(ProceedingJoinPoint joinPoint, String key, long leaseTime) throws Throwable {
        ReentrantLock lock = getLock(key);
        try {
            lock.lock();
            return joinPoint.proceed();
        } finally {
            lock.unlock();
        }

    }

    public ReentrantLock getLock(String key) {
        ReentrantLock lock = lockMap.get(key);
        if (lock == null) {
            synchronized (key.intern()) {
                lock = lockMap.get(key);
                if (lock == null) {
                    lock = new ReentrantLock();
                    lockMap.put(key, lock);
                }
            }
        }

        return lock;
    }
}
