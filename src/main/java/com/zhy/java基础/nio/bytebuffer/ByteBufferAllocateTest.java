package com.zhy.java基础.nio.bytebuffer;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * <p> </p>
 *  class java.nio.HeapByteBuffer
 *      Java 堆内存，读写效率较低，受到GC的影响
 *  class java.nio.DirectByteBuffer
 *      直接内存，相比 heapbytebuffer 少一次从直接内存拷贝到堆内存的过程，但是改对象不受 GC 控制，需要手动回收内存，否则有内存泄漏的风险
 * @author zhouhongyin
 * @since 2023/7/20 10:45
 */
public class ByteBufferAllocateTest {
    @Test
    public void test1() {
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());
    }

}
