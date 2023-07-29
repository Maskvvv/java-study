package com.zhy.middleware.netty.stickybag;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.StandardCharsets;

/**
 * <P> netty 长度字段解码器 </P>
 * LengthFieldBasedFrameDecoder
 *
 * @author zhouhongyin
 * @since 2023/7/29 14:49
 */
public class LengthFieldTest {

    public static void main(String[] args) {
        // 模拟服务器
        // 使用EmbeddedChannel测试handler
        EmbeddedChannel channel = new EmbeddedChannel(

                /*
                    maxFrameLength 数据最大长度：表示数据的最大长度（包括附加信息、长度标识等内容）
                    lengthFieldOffset 数据长度标识的起始偏移量：用于指明数据第几个字节开始是用于标识有用字节长度的，因为前面可能还有其他附加信息
                    lengthFieldLength 数据长度标识所占字节数（用于指明有用数据的长度）：数据中用于表示有用数据长度的标识所占的字节数
                    lengthAdjustment 长度表示与有用数据的偏移量：用于指明数据长度标识和有用数据之间的距离，因为两者之间还可能有附加信息
                    initialBytesToStrip 数据读取起点：读取起点，不读取 0 ~ initialBytesToStrip 之间的数据
                 */

                // 数据最大长度为1KB，长度标识前后各有1个字节的附加信息，长度标识长度为4个字节（int）
                new LengthFieldBasedFrameDecoder(1024, 1, 4, 1, 0),
                new LoggingHandler(LogLevel.DEBUG)
        );

        // 模拟客户端，写入数据
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        send(buffer, "Hello");
        channel.writeInbound(buffer);
        send(buffer, "World");
        channel.writeInbound(buffer);
    }

    private static void send(ByteBuf buf, String msg) {
        // 得到数据的长度
        int length = msg.length();
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        // 将数据信息写入buf
        // 写入长度标识前的其他信息
        buf.writeByte(0xCA);
        // 写入数据长度标识
        buf.writeInt(length);
        // 写入长度标识后的其他信息
        buf.writeByte(0xFE);
        // 写入具体的数据
        buf.writeBytes(bytes);
    }
}
