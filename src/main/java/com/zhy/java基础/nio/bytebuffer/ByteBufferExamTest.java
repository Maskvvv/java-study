package com.zhy.java基础.nio.bytebuffer;

import com.zhy.java基础.nio.ByteBufferUtil;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * <p> 粘包 </p>
 *
 * @author zhouhongyin
 * @since 2023/7/20 9:37
 */
public class ByteBufferExamTest {


    @Test
    public void test1() {

        ByteBuffer buffer = ByteBuffer.allocate(32);
        // 模拟粘包+半包
        buffer.put("Hello,world\nI'm Mike\nHo".getBytes());
        // 调用split函数处理
        split(buffer);
        buffer.put("w are you?\n".getBytes());
        split(buffer);


    }

    private void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {

            // get(i)不会移动 position
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                ByteBuffer byteBuffer = ByteBuffer.allocate(length);

                for (int j = source.position(); j <= i; j++) {
                    byteBuffer.put(source.get());

                }
                ByteBufferUtil.debugAll(byteBuffer);
            }
        }

        source.compact();
    }

}
