package com.zhy.middleware.netty.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * <p> Event Loop </p>
 *
 * @author zhouhongyin
 * @since 2023/7/26 17:09
 */
@Slf4j
public class PipLineClient {
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
                .group(new NioEventLoopGroup())
                // 选择客户 Socket 实现类，NioSocketChannel 表示基于 NIO 的客户端实现
                .channel(NioSocketChannel.class)
                // ChannelInitializer 处理器（仅执行一次）
                // 它的作用是待客户端SocketChannel建立连接后，执行initChannel以便添加更多的处理器
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        // 消息会经过通道 handler 处理，这里是将 String => ByteBuf 编码发出
                        channel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 指定要连接的服务器和端口
                .connect(new InetSocketAddress("localhost", 8080))
                // Netty 中很多方法都是异步的，如 connect
                // 这时需要使用 sync 方法等待 connect 建立连接完毕
                .sync()
                // 获取 channel 对象，它即为通道抽象，可以进行数据读写操作
                .channel();

        Channel read = channel.read();

        read.pipeline().addLast(new ChannelInboundHandlerAdapter(){
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                ByteBuf byteBuf = (ByteBuf) msg;
                log.info(byteBuf.toString(Charset.defaultCharset()));
                super.channelRead(ctx, msg);
            }
        });

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // 写入消息并清空缓冲区
            channel.writeAndFlush(line);
        }

    }
}
