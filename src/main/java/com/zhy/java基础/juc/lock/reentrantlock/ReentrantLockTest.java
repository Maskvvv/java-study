package com.zhy.java基础.juc.lock.reentrantlock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可重入锁
 *
 * 结论：ReentrantLock 当被 static 修饰时，会锁class，当没有被修饰时锁当前对象。
 *
 * @author zhouhongyin
 * @since 2023/2/16 16:19
 */
@Slf4j
public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        log.info("begin");

        threadPool.execute(() -> {
            ReentrantLockBean lock1 = new ReentrantLockBean();
            lock1.method();

        });

        threadPool.execute(() -> {
            ReentrantLockBean lock1 = new ReentrantLockBean();
            lock1.method();

        });

        log.info("end");
        Thread.sleep(300000);

    }

    @Test
    public void test() {


    }
}
