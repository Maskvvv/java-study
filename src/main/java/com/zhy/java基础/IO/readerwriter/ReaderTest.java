package com.zhy.java基础.IO.readerwriter;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p> ReaderTest </p>
 *
 * @author zhouhongyin
 * @since 2023/8/17 17:34
 */
public class ReaderTest {

    @Test
    public void bufferedReaderTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\UserFiles\\桌面\\新建文本文档 (2).txt")));

        System.out.println(bufferedReader.readLine());

        //InputStream is = Files.newInputStream(Paths.get(generatorLogoPath));
    }

}
