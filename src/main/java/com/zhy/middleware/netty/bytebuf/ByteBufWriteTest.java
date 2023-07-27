package com.zhy.middleware.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * <p> Netty ByteBuf </p>
 *
 * @author zhouhongyin
 * @since 2023/7/27 22:27
 */
public class ByteBufWriteTest {
    public static void main(String[] args) {
        // 创建ByteBuf
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(16, 20);
        ByteBufUtils.log(buffer);

        // 向buffer中写入数据
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        ByteBufUtils.log(buffer);

        buffer.writeInt(5);
        ByteBufUtils.log(buffer);

        buffer.writeIntLE(6);
        ByteBufUtils.log(buffer);

        buffer.writeLong(7);
        ByteBufUtils.log(buffer);
    }
}
