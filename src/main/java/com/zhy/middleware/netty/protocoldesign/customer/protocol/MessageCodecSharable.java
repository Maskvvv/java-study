package com.zhy.middleware.netty.protocoldesign.customer.protocol;

import com.zhy.middleware.netty.protocoldesign.customer.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * <p> 可 sharable 的自定义协议解码器 </p>
 *
 * <h3>Sharable注解</h3>
 * <P>为了提高handler的复用率，同时又避免出现一些并发问题，Netty中原生的handler中用@Sharable注解来标明，该handler能否在多个channel中共享。</P>
 *
 * @author zhouhongyin
 * @since 2023/7/30 11:38
 */
@ChannelHandler.Sharable
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
        ByteBuf outBuf = ctx.alloc().buffer();

        // 设置魔数 4个字节
        outBuf.writeBytes(new byte[]{'i', 'm', 's', 'g'});
        // 设置版本号 1个字节
        outBuf.writeByte(1);
        // 设置序列化方式 1个字节
        outBuf.writeByte(1);
        // 设置指令类型 1个字节
        outBuf.writeByte(msg.getMessageType());
        // 设置请求序号 4个字节
        outBuf.writeInt(msg.getSequenceId());
        // 为了补齐为16个字节，填充1个字节的数据
        outBuf.writeByte(0xff);

        // 获得序列化后的msg
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();

        // 获得并设置正文长度 长度用4个字节标识
        outBuf.writeInt(bytes.length);
        // 设置消息正文
        outBuf.writeBytes(bytes);

        out.add(outBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        // 获取魔数
        int magic = msg.readInt();
        // 获取版本号
        byte version = msg.readByte();
        // 获得序列化方式
        byte seqType = msg.readByte();
        // 获得指令类型
        byte messageType = msg.readByte();
        // 获得请求序号
        int sequenceId = msg.readInt();
        // 移除补齐字节
        msg.readByte();
        // 获得正文长度
        int length = msg.readInt();
        // 获得正文
        byte[] bytes = new byte[length];
        msg.readBytes(bytes, 0, length);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message) ois.readObject();
        // 将信息放入List中，传递给下一个handler
        out.add(message);

        // 打印获得的信息正文
        System.out.println("===========魔数===========");
        System.out.println(magic);
        System.out.println("===========版本号===========");
        System.out.println(version);
        System.out.println("===========序列化方法===========");
        System.out.println(seqType);
        System.out.println("===========指令类型===========");
        System.out.println(messageType);
        System.out.println("===========请求序号===========");
        System.out.println(sequenceId);
        System.out.println("===========正文长度===========");
        System.out.println(length);
        System.out.println("===========正文===========");
        System.out.println(message);
    }
}
