package com.zhy.javabase.IO.randomaceessfile;

import org.junit.jupiter.api.Test;

import java.io.File;
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

        raf.seek(40);

        String s = "123";
        raf.write(s.getBytes(StandardCharsets.UTF_8));
        raf.writeBytes(s);
        System.out.println("当前记录指针位置：" + raf.getFilePointer());


        raf.close();
    }

    /**
     * public int skipBytes(int n) throws IOException：尝试跳过n个字节的输入并丢弃跳过的字节。（本质是使用seeek()方法设置文件偏移量指针的位置，并且会判断文件的实际长度，即不会抛出EOFException。）
     * @throws IOException
     */
    @Test
    public void breakpointContinuation() throws IOException {

        //rw : 设为读写模式
        String inFileName = "D:\\UserFiles\\桌面\\新建文本文档 (3).txt";
        String outFileName = "D:\\UserFiles\\桌面\\out_file_name.txt";
        File file = new File(outFileName);
        if (!file.exists()) file.createNewFile();


        RandomAccessFile in = new RandomAccessFile(inFileName, "rw");
        RandomAccessFile out = new RandomAccessFile(outFileName, "rw");


        long position = file.length();
        in.seek(position);
        out.seek(position);
        in.skipBytes((int) position);
        //out.skipBytes((int) position);

        byte[] buffer = new byte[10];
        in.read(buffer);
        out.write(buffer);


        in.close();
        out.close();
    }



}



