package com.zhy.middleware.netty.protocoldesign.customer;

import com.zhy.middleware.netty.protocoldesign.customer.protocol.MessageCodec;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author zhouhongyin
 * @since 2023/7/30 14:22
 */
public class ChatServer {

    public static void main(String[] args) {
        LoggingHandler LOGGING_HANDLER = new LoggingHandler();
        MessageCodec MESSAGE_CODEC = new MessageCodec();

        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        ChannelFuture channelFuture = new ServerBootstrap()
                .group(nioEventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(LOGGING_HANDLER);
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0));
                        ch.pipeline().addLast(MESSAGE_CODEC);
                    }
                }).bind(8080);

        try {
            channelFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
