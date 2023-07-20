package com.zhy.java基础.IO.randomaceessfile;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/20 17:16
 */
public class RandomAccessFileTest {

    @Test
    public void read() throws IOException {
        URL resource = RandomAccessFileTest.class.getClassLoader().getResource("file.txt");

        //rw : 设置模式为读写模式
        RandomAccessFile raf = new RandomAccessFile(resource.getFile(), "rw");
        System.out.println("当前记录指针位置：" + raf.getFilePointer());
        byte[] buf = new byte[3];
        int len = 0;
        while ((len = raf.read(buf)) != -1) {
            System.out.println(new String(buf));
        }

    }

    @Test
    public void write() throws IOException {
        URL resource = RandomAccessFileTest.class.getClassLoader().getResource("file.txt");


        //rw : 设为读写模式
        RandomAccessFile raf = new RandomAccessFile(resource.getFile(), "rw");
        System.out.println("当前记录指针位置：" + raf.getFilePointer());

        raf.seek(29);

        String s = "123";
        raf.write(s.getBytes(StandardCharsets.UTF_8));
        raf.writeBytes(s);
        System.out.println("当前记录指针位置：" + raf.getFilePointer());


        raf.close();
    }

}



