package com.zhy.middleware.netty.channel;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

/**
 * <p> channel 常用方法 </p>
 * close() 可以用来关闭Channel
 * closeFuture() 用来处理 Channel 的关闭
 * sync 方法作用是同步等待 Channel 关闭
 * 而 addListener 方法是异步等待 Channel 关闭
 * pipeline() 方法用于添加处理器
 * write() 方法将数据写入
 * 因为缓冲机制，数据被写入到 Channel 中以后，不会立即被发送
 * 只有当缓冲满了或者调用了flush()方法后，才会将数据通过 Channel 发送出去
 * writeAndFlush() 方法将数据写入并立即发送（刷出）
 * <p>
 * ChannelFuture
 *
 * @author zhouhongyin
 * @since 2023/7/26 22:46
 */
public class ChannelClient {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 该方法为异步非阻塞方法，主线程调用后不会被阻塞，真正去执行连接操作的是NIO线程
                // NIO线程：NioEventLoop 中的线程
                .connect(new InetSocketAddress("localhost", 8080));

        // 该方法用于等待连接真正建立
        channelFuture.sync();
        // 获取客户端-服务器之间的Channel对象
        Channel channel = channelFuture.channel();
        channel.writeAndFlush("hello world");
        System.in.read();
    }
}
