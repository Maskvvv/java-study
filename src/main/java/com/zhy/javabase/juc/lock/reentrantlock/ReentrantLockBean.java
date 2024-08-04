package com.zhy.javabase.juc.lock.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author zhouhongyin
 * @since 2023/2/16 16:20
 */
@Slf4j
public class ReentrantLockBean {
    private static final ReentrantLock reentrantLock = new ReentrantLock();


    public void method() {
        reentrantLock.lock();
        log.info("lock:{}", Thread.currentThread().getName());

    }

}
