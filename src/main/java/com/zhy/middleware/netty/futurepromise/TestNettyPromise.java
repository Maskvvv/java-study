package com.zhy.middleware.netty.futurepromise;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <p> Netty Promise </p>
 *
 * @author zhouhongyin
 * @since 2023/7/27 11:16
 */
public class TestNettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        EventLoop eventLoop = group.next();

        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                // 自定义线程向Promise中存放结果
                promise.setSuccess(50);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                promise.setFailure(e);
            }

        }).start();

        promise.addListener(future -> {
            System.out.println(Thread.currentThread().getName() + " " + future.get());

        });
        // 主线程从Promise中获取结果
        System.out.println(Thread.currentThread().getName() + " " + promise.get());


    }
}
