package com.zhy.javabase.nio.selector.basicevent.write.readaccept;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * <p> Selector 基础事件处理 </p>
 * <p>
 * Write
 *
 * @author zhouhongyin
 * @since 2023/7/21 11:50
 */
public class SelectorServer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 获得服务器通道
        try (ServerSocketChannel server = ServerSocketChannel.open()) {
            server.bind(new InetSocketAddress(8080));
            // 创建选择器
            Selector selector = Selector.open();

            // 通道必须设置为非阻塞模式
            server.configureBlocking(false);
            // 将通道注册到选择器中，并设置感兴趣的事件
            server.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                // 若没有事件就绪，线程会被阻塞，反之不会被阻塞。从而避免了CPU空转
                // 返回值为就绪的事件个数
                int ready = selector.select();
                System.out.println("selector ready counts : " + ready);

                // 获取所有事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                // 使用迭代器遍历事件
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();


                    // 判断key的类型
                    // 处理 Acceptable 事件
                    if (key.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = channel.accept();
                        socketChannel.configureBlocking(false);

                        SelectionKey sckey = socketChannel.register(selector, 0, null);

                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < 3000000; i++) {
                            sb.append("a");
                        }

                        ByteBuffer byteBuffer = Charset.defaultCharset().encode(sb.toString());
                        int write = socketChannel.write(byteBuffer);
                        System.out.println(write);

                        while (buffer.hasRemaining()) {
                            sckey.interestOps(sckey.interestOps() | SelectionKey.OP_WRITE);
                            sckey.attach(byteBuffer);
                        }
                    }


                    // 处理写事件
                    else if (key.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer attachment = (ByteBuffer) key.attachment();
                        int write = socketChannel.write(attachment);
                        System.out.println(write);

                        if (!buffer.hasRemaining()) {
                            // 无内容可写清除 buffer 和 Write事件
                            key.attach(null);
                            key.interestOps(key.interestOps() ^ SelectionKey.OP_WRITE);
                        }
                    }


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        System.out.println(1 ^ 2);

    }
}
