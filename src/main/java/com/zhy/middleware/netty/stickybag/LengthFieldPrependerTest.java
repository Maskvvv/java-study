package com.zhy.middleware.netty.stickybag;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.StandardCharsets;

/**
 * <P> netty 长度字段编码器 </P>
 * LengthFieldBasedFrameDecoder
 *
 * @author zhouhongyin
 * @since 2023/7/29 14:49
 */
public class LengthFieldPrependerTest {

    public static void main(String[] args) {
        // 模拟服务器
        // 使用EmbeddedChannel测试handler
        EmbeddedChannel channel = new EmbeddedChannel(
                new LoggingHandler(LogLevel.DEBUG),
                new LengthFieldPrepender(4),
                new LoggingHandler(LogLevel.DEBUG)
        );

        // 模拟服务器写出数据
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        write(buffer, "Hello");
        channel.writeOutbound(buffer);
    }

    private static void write(ByteBuf buf, String msg) {
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        // 将数据信息写入buf
        // 写入长度标识前的其他信息
        buf.writeByte(0xCA);
        // 写入具体的数据
        buf.writeBytes(bytes);
    }
}
