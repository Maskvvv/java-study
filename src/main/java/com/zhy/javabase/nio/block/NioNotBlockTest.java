package com.zhy.javabase.nio.block;

import com.zhy.javabase.nio.ByteBufferUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * <p> 非阻塞模式 </p>
 *
 * @author zhouhongyin
 * @since 2023/7/21 11:08
 */
public class NioNotBlockTest {

    @Test
    public void server() {

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 获得服务器通道
        try (ServerSocketChannel server = ServerSocketChannel.open()) {
            // 非阻塞模式
            server.configureBlocking(false);
            // 为服务器通道绑定端口
            server.bind(new InetSocketAddress(8080));
            // 用户存放连接的集合
            ArrayList<SocketChannel> channels = new ArrayList<>();
            // 循环接收连接
            while (true) {
                // 没有连接时，返回null
                SocketChannel socketChannel = server.accept();
                if (socketChannel != null) {
                    System.out.println("after connecting...");

                    // 非阻塞模式
                    socketChannel.configureBlocking(false);
                    channels.add(socketChannel);
                }
                // 循环遍历集合中的连接
                for (SocketChannel channel : channels) {
                    // 处理通道中的数据
                    // 当通道中没有数据可读时，返回0
                    int read = channel.read(buffer);
                    if (read == 0) continue;

                    buffer.flip();
                    ByteBufferUtil.debugRead(buffer);
                    buffer.clear();
                    System.out.println("after reading");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void client() {

        try (SocketChannel socketChannel = SocketChannel.open()) {
            // 建立连接
            socketChannel.connect(new InetSocketAddress("localhost", 8080));
            socketChannel.write(Charset.defaultCharset().encode("hello"));
            System.out.println("waiting...");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
