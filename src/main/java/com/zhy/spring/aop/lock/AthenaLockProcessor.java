package com.zhy.spring.aop.lock;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * <p> Lock Processor </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 10:52
 */
public interface AthenaLockProcessor {

    Object proceed(ProceedingJoinPoint joinPoint, String key, long leaseTime) throws Throwable;

}
