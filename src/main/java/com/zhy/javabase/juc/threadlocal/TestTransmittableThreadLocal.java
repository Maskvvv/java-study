package com.zhy.javabase.juc.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestTransmittableThreadLocal {

    TransmittableThreadLocal transmittableThreadLocal = new TransmittableThreadLocal();

    @Test
    public void test1() throws IOException {

        transmittableThreadLocal.set("aaa");
        Runnable runnable = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("====" + Thread.currentThread().getId() + "====");
            System.out.println(transmittableThreadLocal.get());
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        int i = 0;
        while (i < 10) {
            executorService.execute(TtlRunnable.get(runnable));
            transmittableThreadLocal.set(i + "AA");
            i++;
        }


        System.in.read();
    }

    @Test
    public void test2() throws Exception {

        transmittableThreadLocal.set("aaa");
        Runnable runnable = () -> {
            System.out.println("====" + Thread.currentThread().getName() + "====");
            System.out.println("value: " + transmittableThreadLocal.get());

        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);


        executorService.execute(TtlRunnable.get(runnable, true));
        transmittableThreadLocal.set(1 + "AA");
        executorService.execute(TtlRunnable.get(runnable, true));


        TimeUnit.MILLISECONDS.sleep(1000);


        executorService.execute(TtlRunnable.get(runnable, true));


        System.in.read();
    }


    @Test
    public void test3() {
        final TransmittableThreadLocal transmittableThreadLocal = new TransmittableThreadLocal();
        transmittableThreadLocal.set("aaa");
        Runnable runnable = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("====" + Thread.currentThread().getId() + "====");
            System.out.println(transmittableThreadLocal.get());
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(executorService);

        int i = 0;
        while (Boolean.TRUE) {
            ttlExecutorService.execute(runnable);
            transmittableThreadLocal.set(i + "AA");
            i++;
        }
    }
}

