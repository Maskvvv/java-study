package com.zhy.javabase.nio.bytebuffer;

import com.zhy.javabase.nio.ByteBufferUtil;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class ByteBufferReadWriteTest {
    ByteBuffer buffer = ByteBuffer.allocate(10);

    @Test
    public void test0() {
        int a = 1;
        // 向buffer中写入1个字节的数据
        buffer.putInt(a);
        // 使用工具类，查看buffer状态
        ByteBufferUtil.debugAll(buffer);
    }

    @Test
    public void test1() {
        // 向buffer中写入1个字节的数据
        buffer.put((byte) 97);
        // 使用工具类，查看buffer状态
        ByteBufferUtil.debugAll(buffer);

    }

    @Test
    public void test2() {
        // 向buffer中写入4个字节的数据
        buffer.put(new byte[]{98, 99, 100, 101});
        ByteBufferUtil.debugAll(buffer);


    }


    @Test
    public void test3() {

        // 向buffer中写入4个字节的数据
        buffer.put(new byte[]{98, 99, 100, 101});
        ByteBufferUtil.debugAll(buffer);

        // 获取数据
        buffer.flip();
        ByteBufferUtil.debugAll(buffer);
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        ByteBufferUtil.debugAll(buffer);

        // 使用compact切换模式
        buffer.compact();
        ByteBufferUtil.debugAll(buffer);

        // 再次写入
        buffer.put((byte)102);
        buffer.put((byte)103);
        ByteBufferUtil.debugAll(buffer);
    }
}
