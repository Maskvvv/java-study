package com.zhy.middleware.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

/**
 * <p> Netty ByteBuf Composite 组合多个 buf</p>
 *
 * @author zhouhongyin
 * @since 2023/7/27 22:27
 */
public class ByteBufCompositeTest {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        buffer.writeBytes(new byte[]{1, 2, 3, 4, 5});

        ByteBuf buffer1 = ByteBufAllocator.DEFAULT.buffer();
        buffer1.writeBytes(new byte[]{6, 7, 8, 9, 10});

        CompositeByteBuf buffer3 = ByteBufAllocator.DEFAULT.compositeBuffer();
        buffer3.addComponents(buffer1, buffer);

        System.out.println(buffer3);
    }
}
