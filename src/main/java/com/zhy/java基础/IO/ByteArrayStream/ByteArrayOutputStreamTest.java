package com.zhy.java基础.IO.ByteArrayStream;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhouhongyin
 * @since 2022/5/30 16:48
 */
public class ByteArrayOutputStreamTest {
    public static void main(String[] args) throws IOException {
        // 内存输出流
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

        // 缓冲输出流（用buffered包装一下，为了提高效率使用缓冲区流）
        BufferedOutputStream bos = new BufferedOutputStream(arrayOutputStream);

        // 输出流转输入流
        InputStreamSource inputStreamSource = new ByteArrayResource(arrayOutputStream.toByteArray());
        InputStream inputStream = inputStreamSource.getInputStream();

        InputStream bufferedInputStream = new BufferedInputStream(inputStreamSource.getInputStream());
    }

}
