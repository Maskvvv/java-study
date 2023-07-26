package com.zhy.java基础.nio.bytebuffer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/20 10:45
 */
public class ScatteringReadsTest {

    @Test
    public void test1() {
        URL resource = ScatteringReadsTest.class.getClassLoader().getResource("file.txt");

        try (FileChannel channel = new RandomAccessFile(resource.getFile(), "r").getChannel()) {
            ByteBuffer b1 = ByteBuffer.allocate(3);
            ByteBuffer b2 = ByteBuffer.allocate(3);
            ByteBuffer b3 = ByteBuffer.allocate(3);

            channel.read(new ByteBuffer[]{b1, b2, b3});
            b1.flip();
            b2.flip();
            b3.flip();

            while (b1.hasRemaining()) {
                System.out.print(((char) b1.get()));
            }
            System.out.println();

            while (b2.hasRemaining()) {
                System.out.print(((char) b2.get()));
            }
            System.out.println();

        } catch (IOException e) {
        }

        //ByteBufferUtil.debugAll(buffer);
    }

}
