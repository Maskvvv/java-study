package com.zhy.middleware.netty.stickybag;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> netty 定长解码器 解决 粘包与半包 </p>
 * <pre>
 *     客户端于服务器约定一个最大长度，保证客户端每次发送的数据长度都不会大于该长度。若发送数据长度不足则需要补齐至该长度.
 *     服务器接收数据时，将接收到的数据按照约定的最大长度进行拆分，即使发送过程中产生了粘包，也可以通过定长解码器将数据正确地进行拆分。服务端需要用到FixedLengthFrameDecoder对数据进行定长解码，具体使用方法如下
 * </pre>
 *
 * @author zhouhongyin
 * @since 2023/7/28 17:34
 */
@Slf4j
public class FixLengthServer {
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
                    ch.pipeline().addLast(new FixedLengthFrameDecoder(16));
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
        new FixLengthServer().start();
    }
}
