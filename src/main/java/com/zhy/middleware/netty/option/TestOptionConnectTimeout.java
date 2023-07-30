package com.zhy.middleware.netty.option;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;

/**
 * <p> CONNECT_TIMEOUT_MILLIS </p>
 *
 * <ul>
 * <li>属于 <strong>SocketChannal</strong> 的参数</li>
 * <li>用在<strong>客户端建立连接</strong>时，如果在指定毫秒内无法连接，会抛出 timeout 异常</li>
 * <li><strong>注意</strong>：Netty 中不要用成了SO_TIMEOUT 主要用在阻塞 IO，而 Netty 是非阻塞 IO</li>
 * </ul>
 *
 * @author zhouhongyin
 * @since 2023/7/30 17:45
 */
public class TestOptionConnectTimeout {
    public static void main(String[] args) {


        //客户端通过 Bootstrap.option 函数来配置参数，配置参数作用于 SocketChannel
        //服务器通过 ServerBootstrap来配置参数，但是对于不同的 Channel 需要选择不同的方法
        //通过 option 来配置 ServerSocketChannel 上的参数
        //通过 childOption 来配置 SocketChannel 上的参数

        // SocketChannel 5s内未建立连接就抛出异常
        new Bootstrap().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);

        // ServerSocketChannel 5s内未建立连接就抛出异常
        new ServerBootstrap().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        // SocketChannel 5s内未建立连接就抛出异常
        new ServerBootstrap().childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
    }
}
