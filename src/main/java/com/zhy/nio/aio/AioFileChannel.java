package com.zhy.nio.aio;

import com.zhy.nio.ByteBufferUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * <p> aio </p>
 *
 * @author zhouhongyin
 * @since 2023/7/25 16:44
 */
public class AioFileChannel {
    public static void main(String[] args) {
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get(""), StandardOpenOption.READ)) {

            ByteBuffer buffer = ByteBuffer.allocate(16);

            // 1 ByteBuffer 2 读取的其实位置 3 附件 回调对象
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    ByteBufferUtil.println(attachment);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {

                }
            });


        } catch (IOException e) {
        }
    }
}
