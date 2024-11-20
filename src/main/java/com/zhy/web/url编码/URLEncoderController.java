package com.zhy.web.url编码;

import cn.hutool.core.io.IoUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author zhouhongyin
 * @since 2022/12/16 16:26
 */
@RestController
@RequestMapping("url_encoder")
public class URLEncoderController {

    @GetMapping("encode")
    public void encode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = "/?+!@#有空格 you空格.doc";

        OutputStream outputStream = response.getOutputStream();
        File file = new File("D:\\UserFiles\\桌面\\毕设名称.txt");

        InputStream inputStream = new FileInputStream(file);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));

        IoUtil.copy(inputStream, outputStream);

    }

}
