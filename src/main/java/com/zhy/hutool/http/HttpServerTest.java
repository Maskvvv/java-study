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
                    OutputStream out = res.getOut();

                    res.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=a.exe");
                    res.setHeader(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE);

                    File file = new File("F:\\安装包\\Docker Desktop Installer.exe");
                    FileInputStream fileInputStream = new FileInputStream(file);

                    IoUtil.copy(fileInputStream, out);
                    IoUtil.close(out);
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
