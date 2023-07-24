package com.zhy.nio.bytebuffer;

import com.zhy.nio.ByteBufferUtil;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/20 10:45
 */
public class ByteBufferStringTest {

    @Test
    public void test1() {
        String s = "我是谁";
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(s.getBytes(StandardCharsets.UTF_8));


        ByteBufferUtil.debugAll(buffer);
    }

    @Test
    public void test2() {
        String s = "我是谁";
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(s);
        ByteBufferUtil.debugAll(buffer);
    }

    @Test
    public void test3() {
        String s = "我是谁";
        ByteBuffer buffer = ByteBuffer.wrap(s.getBytes(StandardCharsets.UTF_8));
        ByteBufferUtil.debugAll(buffer);

        byte[] b = new byte[3];
        buffer.get(b);
        System.out.println(new String(b));
    }

}
