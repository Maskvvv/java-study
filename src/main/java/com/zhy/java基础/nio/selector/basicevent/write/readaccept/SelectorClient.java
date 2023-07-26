package com.zhy.java基础.nio.selector.basicevent.write.readaccept;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/21 13:56
 */
public class SelectorClient {

    public static void main(String[] args) {

        try (SocketChannel channel = SocketChannel.open()) {
            channel.connect(new InetSocketAddress("localhost", 8080));


            int i = 0;
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
                i += channel.read(byteBuffer);
                System.out.println(i);
                byteBuffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
