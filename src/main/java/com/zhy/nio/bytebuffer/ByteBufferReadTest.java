package com.zhy.nio.bytebuffer;

import com.zhy.nio.ByteBufferUtil;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * <p> </p>
 * rewind
 * mark
 * reset
 *
 * @author zhouhongyin
 * @since 2023/7/20 10:45
 */
public class ByteBufferReadTest {

    /**
     * rewind(): 重置 position
     */
    @Test
    public void test1() {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});

        buffer.flip();

        byte b = buffer.get();
        ByteBufferUtil.debugAll(buffer);

        buffer.rewind();
        ByteBufferUtil.debugAll(buffer);
    }

    /**
     * mark & reset
     */
    @Test
    public void test2() {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});

        buffer.flip();


        System.out.println(((char) buffer.get()));
        buffer.mark();
        System.out.println(((char) buffer.get()));
        System.out.println(((char) buffer.get()));
        System.out.println(((char) buffer.get()));


        buffer.reset();
        System.out.println(((char) buffer.get()));
    }

    /**
     * get(index)
     * 不会改变 position 的位置
     */
    @Test
    public void test3() {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});

        buffer.flip();


        System.out.println(((char) buffer.get(2)));


    }

}
