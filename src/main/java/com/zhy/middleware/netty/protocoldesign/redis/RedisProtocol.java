package com.zhy.middleware.netty.protocoldesign.redis;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * <p> 解析 Redis 协议 </p>
 *
 * <h3> Redis 协议 </h3>
 * <pre>
 * set name 123456
 * 该指令一共有3部分，每条指令之后都要添加回车与换行符
 * *3\r\n
 * 第一个指令的长度是3
 * $3\r\n
 * 第一个指令是set指令
 * set\r\n
 * 下面的指令以此类推
 * $4\r\n
 * name\r\n
 * $6\r\n
 * Nyima\r\n
 * </pre>
 *
 * @author zhouhongyin
 * @since 2023/7/29 15:22
 */
public class RedisProtocol {
    private static final byte[] LINE = {'\r', '\n'};
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 打印日志
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                            // 打印返回结果
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf byteBuf = (ByteBuf) msg;
                                    System.out.println("Redis result > \n" + byteBuf.toString(StandardCharsets.UTF_8));
                                    super.channelRead(ctx, msg);
                                }
                            });
                        }
                    })
                    .connect(new InetSocketAddress("localhost", 6379));
            channelFuture.sync();


            Channel channel = channelFuture.channel().read();
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("exit")) break;

                // 获得ByteBuf
                ByteBuf buffer = channel.alloc().buffer();
                writeRedisCommand(buffer, line);
                channel.writeAndFlush(buffer);
            }

            // 关闭channel
            channelFuture.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭group
            group.shutdownGracefully();
        }
    }

    public static void writeRedisCommand(ByteBuf buffer, String command) {
        String[] split = command.split(" ");
        writeLine(buffer, ("*" + split.length));

        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            writeLine(buffer, "$" + s.length());
            writeLine(buffer, s);
        }
    }

    public static void writeLine(ByteBuf buffer, String command) {
        buffer.writeBytes(command.getBytes(StandardCharsets.UTF_8));
        buffer.writeBytes(LINE);
    }
}
