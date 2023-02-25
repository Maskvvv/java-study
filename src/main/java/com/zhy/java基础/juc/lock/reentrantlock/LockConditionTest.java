package com.zhy.java基础.juc.lock.reentrantlock;



import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock & condition
 *
 * @author zhouhongyin
 * @since 2023/2/20 11:25
 */
@Slf4j
public class LockConditionTest {

    //final static ReentrantLock lock = new ReentrantLock();
    //final static Condition done = lock.newCondition();


    @Test
    public void test() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition done = lock.newCondition();


        AtomicInteger o = new AtomicInteger(0);

        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                log.info("lock:{" + Thread.currentThread().getName() + "}");


                while (o.get() == 0) {
                    try {
                        log.info("wait:{" + Thread.currentThread().getName() + "}");
                        done.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                log.info("done:{" + Thread.currentThread().getName() + "}");

            } finally {
                lock.unlock();
                log.info("unlock:{" + Thread.currentThread().getName() + "}");

            }

        });


        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                log.info("lock:{" + Thread.currentThread().getName() + "}");

                o.compareAndSet(0, 1);

                done.signal();

                log.info("notifyAll:{" + Thread.currentThread().getName() + "}");

            } finally {
                lock.unlock();
                log.info("unlock:{" + Thread.currentThread().getName() + "}");
            }

        });

        thread1.start();

        Thread.sleep(100);

        thread2.start();

        thread1.join();
        thread2.join();

    }

}
