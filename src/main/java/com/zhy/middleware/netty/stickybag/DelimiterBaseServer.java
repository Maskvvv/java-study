package com.zhy.middleware.netty.stickybag;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * <p> netty 自定义分隔符行解码器 </p>
 * <p>
 * 行解码器的是通过分隔符对数据进行拆分来解决粘包半包问题的
 * 可以通过 {@code LineBasedFrameDecoder(int maxLength)} 来拆分以换行符(\n)为分隔符的数据，
 * 也可以通过 {@code DelimiterBasedFrameDecoder(int maxFrameLength, ByteBuf... delimiters)} 来指定通过什么分隔符来拆分数据（可以传入多个分隔符）,
 * 两种解码器都需要传入数据的最大长度，若超出最大长度，会抛出TooLongFrameException异常
 *
 * @author zhouhongyin
 * @since 2023/7/28 17:34
 */
@Slf4j
public class DelimiterBaseServer {
    void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, ch.alloc().buffer().writeBytes("\\c".getBytes(StandardCharsets.UTF_8))));
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(8080);
            channelFuture.sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server error", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new DelimiterBaseServer().start();
    }
}
