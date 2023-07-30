package com.zhy.middleware.netty.protocoldesign.customer.protocol;

import com.zhy.middleware.netty.protocoldesign.customer.message.LoginRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * <p> 自定义协议编解码器 测试 </p>
 *
 * @author zhouhongyin
 * @since 2023/7/30 11:38
 */
public class MessageCodectTest {

    public static void main(String[] args) throws Exception {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0),
                new LoggingHandler(),
                new MessageCodec());


        // encode
        LoginRequestMessage Message = new LoginRequestMessage("zhy", "123");
        channel.writeOutbound(Message);


        // decode
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, Message, buffer);

        // 模拟半包现象
        ByteBuf slice1 = buffer.slice(0, 100);
        ByteBuf slice2 = buffer.slice(100, buffer.readableBytes() - 100);

        slice1.retain();
        channel.writeInbound(slice1);
        channel.writeInbound(slice2);
    }

}
