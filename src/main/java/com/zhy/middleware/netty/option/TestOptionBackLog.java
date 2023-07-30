package com.zhy.middleware.netty.option;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;

/**
 * <p> SO_BACKLOG </p>
 * <h3>TCP连接队列</h3>
 * <ul>
 * <li>半连接队列 - sync queue<ul>
 * <li>大小通过 /proc/sys/net/ipv4/tcp_max_syn_backlog 指定，在 <code>syncookies</code> 启用的情况下，逻辑上没有最大值限制，这个设置便被忽略</li>
 * </ul>
 * </li>
 * <li><strong>全连接队列 - accept queue</strong><ul>
 * <li>其大小通过 /proc/sys/net/core/somaxconn 指定，在使用 listen 函数时，<strong>内核会根据传入的 backlog 参数与系统参数，取二者的较小值</strong></li>
 * <li>如果 accpet queue 队列满了，server 将发送一个拒绝连接的错误信息到 client</li>
 * </ul>
 * </li>
 * </ul>
 *
 * <h3>全连接队列默认指</h3>
 * <ul>
 * <li>backlog的值会根据操作系统的不同，来<strong>选择不同的默认值</strong><ul>
 * <li>Windows 200</li>
 * <li>Linux/Mac OS 128</li>
 * </ul>
 * </li>
 * <li><strong>如果配置文件<code>/proc/sys/net/core/somaxconn</code>存在</strong>，会读取配置文件中的值，并将backlog的值设置为配置文件中指定的</li>
 * </ul>
 *
 * @author zhouhongyin
 * @since 2023/7/30 17:45
 */
public class TestOptionBackLog {
    public static void main(String[] args) {

        // 在Netty中，SO_BACKLOG主要用于设置全连接队列的大小。当处理Accept的速率小于连接建立的速率时，全连接队列中堆积的连接数大于SO_BACKLOG设置的值是，便会抛出异常
        // 设置全连接队列，大小为2
        new ServerBootstrap().option(ChannelOption.SO_BACKLOG, 2);


    }
}
