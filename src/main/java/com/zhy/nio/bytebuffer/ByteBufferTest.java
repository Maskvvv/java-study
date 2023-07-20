package com.zhy.nio.bytebuffer;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p> ByteBuffer </p>
 *
 * @author zhouhongyin
 * @since 2023/7/20 9:37
 */
public class ByteBufferTest {


    @Test
    public void test1() {
        URL resource = ByteBufferReadWritTest.class.getClassLoader().getResource("file.txt");
        try (FileChannel channel = new FileInputStream(resource.getFile()).getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(10);

            while (channel.read(buffer) != -1) {

                buffer.flip();

                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.print(((char) b));
                }

                buffer.clear();
            }

        } catch (IOException e) {

        }
    }

}
