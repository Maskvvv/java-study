package com.zhy.java基础.nio;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * <p> Zero Copy Test</p>
 *
 * @author zhouhongyin
 * @since 2023/11/5 13:36
 */
public class ZeroCopyTest {

    @Test
    public void zeroCopyWrite() throws FileNotFoundException {

        try (RandomAccessFile rw = new RandomAccessFile("C:\\Users\\lenovo\\Desktop\\sql.txt", "rw")) {

            FileChannel channel = rw.getChannel();
            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5_000_000 * 10);

            byte[] bytes = "0123456789".getBytes(StandardCharsets.UTF_8);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bytes.length);
            byteBuffer.put(bytes);

            Stopwatch stopwatch = Stopwatch.createStarted();

            for (int i = 0; i < 5_000_000; i++) {
                //byteBuffer.position(0);
                //mappedByteBuffer.put(byteBuffer);
                mappedByteBuffer.put(bytes);
            }

            stopwatch.stop();
            // 执行时间（单位：秒）
            System.out.printf("执行时长：%d 秒. %n", stopwatch.elapsed().getSeconds()); // %n 为换行
            // 执行时间（单位：毫秒）
            System.out.printf("执行时长：%d 豪秒.", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (IOException e) {

        }

    }

    @Test
    public void commonWrite() throws FileNotFoundException {

        try (RandomAccessFile rw = new RandomAccessFile("C:\\Users\\lenovo\\Desktop\\sql1.txt", "rw")) {

            rw.seek(rw.length());
            Stopwatch stopwatch = Stopwatch.createStarted();

            byte[] bytes = "0123456789".getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < 5_000_000; i++) {
                rw.write(bytes);
            }

            // 执行时间（单位：秒）
            System.out.printf("执行时长：%d 秒. %n", stopwatch.elapsed().getSeconds()); // %n 为换行
            // 执行时间（单位：毫秒）
            System.out.printf("执行时长：%d 豪秒.", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (IOException e) {

        }
    }

}
