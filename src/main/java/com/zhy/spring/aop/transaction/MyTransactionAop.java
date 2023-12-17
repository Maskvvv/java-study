package com.zhy.spring.aop.transaction;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouhongyin
 * @since 2023/3/2 22:18
 */
@Order(1)
@Slf4j
@Aspect
@Component
public class MyTransactionAop {
    static ThreadLocal<ReentrantLock> threadLocal = new ThreadLocal<>();

    @Pointcut("@annotation(com.zhy.spring.aop.transaction.MyTransaction)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("-----------------around before-----------------");

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        threadLocal.set(lock);

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } finally {
            threadLocal.get().unlock();
            threadLocal.remove();
        }
        log.info("-----------------around after-----------------");

        return proceed;
    }

    /**
     * 前置操作
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("-----------------before-----------------");
    }


    /**
     * 后置操作
     */
    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) throws IOException {
        log.info("-----------------after-----------------");
    }

    /**
     * 返回通知
     */
    @AfterReturning(returning = "result", pointcut = "pointCut()")
    public void doAfterReturning(Object result) {
        log.info("-------------doAfterReturning------------------");
    }

}
