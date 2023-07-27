package com.zhy.middleware.netty.futurepromise.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;

import java.util.concurrent.ExecutionException;

/**
 * <p> Netty Future </p>
 *
 * @author zhouhongyin
 * @since 2023/7/27 11:16
 */
public class TestNettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        EventLoop eventLoop = group.next();

        Future<Integer> future = eventLoop.submit(() -> {
            Thread.sleep(1000);
            return 40;
        });

        //System.out.println(future.get());

        future.addListener(future1 -> {
            System.out.println(future1.getNow());
        });

    }
}
