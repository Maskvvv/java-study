package com.zhy.middleware.netty.futurepromise.future.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p> jdk future </p>
 *
 * @author zhouhongyin
 * @since 2023/7/27 11:09
 */
public class TestJdkFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Future<Integer> submit = threadPool.submit(() -> {
            Thread.sleep(1000);
            return 40;
        });

        Integer integer = submit.get();

        System.out.println(integer);
    }

}
