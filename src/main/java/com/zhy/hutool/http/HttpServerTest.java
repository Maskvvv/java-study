package com.zhy.hutool.http;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MimeTypeUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhouhongyin
 * @since 2022/7/5 11:31
 */
public class HttpServerTest {

    @Test
    public void httpServerTest1() throws IOException {
        HttpUtil.createServer(8888)
                .addAction("/", (req, res) -> {

                    res.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=review2.pdf");
                    res.setHeader(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE);

                    File file = new File("D:\\UserFiles\\桌面\\review2.pdf");
                    FileInputStream fileInputStream = new FileInputStream(file);

                    res.write(fileInputStream);

                    IoUtil.close(fileInputStream);

                })
                .start();

        System.in.read();
    }

    @Test
    public void httpServerTest2() throws IOException {
        HttpUtil.createServer(8888)
                // 设置默认根目录
                .setRoot("E:\\myproject\\hutool-site")
                .start();

        System.in.read();
    }

}
