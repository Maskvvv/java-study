package com.zhy.web.header;

import cn.hutool.core.io.IoUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimeType;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author zhouhongyin
 * @since 2022/7/1 16:50
 */
@RestController
@RequestMapping("header")
public class HeaderController {

    @GetMapping("content/disposition/pdf")
    public void contentDispositionPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        //response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=a.pdf");
        //response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=a.pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+ new String("富 雨的 简历1653869894.pdf".getBytes(), "ISO8859-1"));


        //response.setHeader(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/pdf");

        FileInputStream fileInputStream = new FileInputStream("D:\\UserFiles\\桌面\\富雨的简历1653869894.pdf");
        IoUtil.copy(fileInputStream, outputStream);

        IoUtil.close(outputStream);
        IoUtil.close(fileInputStream);
    }

    @GetMapping("content/disposition/word")
    public void contentDispositionWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=a.docx");
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        FileInputStream fileInputStream = new FileInputStream("D:\\UserFiles\\桌面\\转正资料\\转正述职申请流程.docx");
        IoUtil.copy(fileInputStream, outputStream);

        IoUtil.close(outputStream);
        IoUtil.close(fileInputStream);
    }

    @GetMapping("content/disposition/html")
    public void contentDispositionHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=a.html");
        response.setHeader(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_HTML_VALUE);

        String htmlContent = "<h1> 我是标题</h1>";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));

        IoUtil.copy(byteArrayInputStream, outputStream);

        IoUtil.close(outputStream);
        IoUtil.close(byteArrayInputStream);
    }


}
