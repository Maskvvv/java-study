package com.zhy.java基础.IO.readerwriter;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author zhouhongyin
 * @since 2022/6/27 22:14
 */
public class WriterTest {

    @Test
    public void test() throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("G:\\qst\\qst需求文档\\a.txt"));
        outputStreamWriter.write("aaaa\n");
        outputStreamWriter.write("bbbb");
        outputStreamWriter.write("bbbb");
        outputStreamWriter.close();
    }

    @Test
    public void test1() throws IOException {
        FileWriter fileWriter = new FileWriter("D:\\code\\java\\java-study\\src\\main\\java\\com\\zhy\\java基础\\IO\\readerwriter\\a.txt");

        //fileWriter.write("aaaa");
        //fileWriter.write("bbb");
        //fileWriter.write("ccc");
        fileWriter.append("aaaa\n");
        fileWriter.append("bbbb");
        fileWriter.append("ccc", 1, 2);
        fileWriter.close();
    }
}
