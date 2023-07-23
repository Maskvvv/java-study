package com.zhy.nio.selector;

import com.zhy.nio.bytebuffer.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p> Selector </p>
 *
 * @author zhouhongyin
 * @since 2023/7/21 11:50
 */
public class SelectorServer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 获得服务器通道
        try(ServerSocketChannel server = ServerSocketChannel.open()) {
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
                    if(key.isAcceptable()) {
                        // 获得key对应的channel
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        System.out.println("before accepting...");

                        // 获取连接并处理，而且是必须处理，否则需要取消
                        SocketChannel socketChannel = channel.accept();
                        System.out.println("after accepting...");
                        socketChannel.configureBlocking(false);
                        SelectionKey scKey = socketChannel.register(selector, 0, null);
                        scKey.interestOps(SelectionKey.OP_READ);

                    }

                    // 处理 Read 事件
                    else if (key.isReadable()) {
                        try {
                            SocketChannel socketChannel = (SocketChannel) key.channel();

                            // 客户端通过 close 断开链接时，会返回 -1
                            int read = socketChannel.read(buffer);
                            if (read == -1) {
                                System.out.println("客户端断开:" + socketChannel);
                                key.cancel();
                                continue;
                            }

                            buffer.flip();
                            ByteBufferUtil.println(buffer);
                            buffer.clear();
                        } catch (IOException e) {
                            // 客户端不通过 close 断开会发送 Read 事件，应该捕捉异常取消对该 key 的监听
                            e.printStackTrace();
                            key.cancel();
                        }
                    }


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
