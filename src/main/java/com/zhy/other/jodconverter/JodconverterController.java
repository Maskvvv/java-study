package com.zhy.other.jodconverter;

import cn.hutool.core.net.URLEncoder;
import cn.hutool.http.Header;
import lombok.extern.slf4j.Slf4j;
import org.jodconverter.DocumentConverter;
import org.jodconverter.OnlineConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("jodconverter")
@Slf4j
public class JodconverterController {

    @Autowired(required = false)
    DocumentConverter documentConverter;

    @Autowired(required = false)
    OnlineConverter onlineConverter;

    @Resource
    private ThreadPoolTaskExecutor JodConverterThreadPoolTaskExecutor;

    /**
     * local 版
     */
    @GetMapping("/transfer")
    public void toPdfFile(HttpServletResponse response, HttpServletRequest request, MultipartFile file) throws Exception {

        ServletOutputStream outputStream = response.getOutputStream();

        String contentType = file.getContentType();
        log.info("contentType:{}", contentType);
        String name = file.getName();
        log.info("name:{}", name);
        String originalFilename = file.getOriginalFilename();
        log.info("originalFilename:{}", originalFilename);

        int i = originalFilename.lastIndexOf(".");

        String pdfName = originalFilename.substring(0, i) + ".pdf";
        response.setHeader(Header.CONTENT_DISPOSITION.name(), "attachment;fileName=" + URLEncoder.createDefault().encode(pdfName, StandardCharsets.UTF_8));
        response.setContentType(contentType);

        documentConverter.convert(file.getInputStream()).to(outputStream).as(DefaultDocumentFormatRegistry.PDF).execute();
    }

    /**
     * online 版
     */
    @GetMapping("/transfer/online")
    public void toPdfFileOnline(HttpServletResponse response, HttpServletRequest request, MultipartFile file) throws Exception {

        ServletOutputStream outputStream = response.getOutputStream();

        String contentType = file.getContentType();
        log.info("contentType:{}", contentType);
        String name = file.getName();
        log.info("name:{}", name);
        String originalFilename = file.getOriginalFilename();
        log.info("originalFilename:{}", originalFilename);

        int i = originalFilename.lastIndexOf(".");

        String pdfName = originalFilename.substring(0, i) + ".pdf";
        response.setHeader(Header.CONTENT_DISPOSITION.name(), "attachment;fileName=" + URLEncoder.createDefault().encode(pdfName, StandardCharsets.UTF_8));
        response.setContentType(contentType);

        onlineConverter.convert(file.getInputStream()).as(DefaultDocumentFormatRegistry.XLSX).to(outputStream).as(DefaultDocumentFormatRegistry.PDF).execute();
    }


    /**
     * local 版 thread
     */
    @GetMapping("/transfer/thread")
    public void toPdfFileThread(HttpServletResponse response, HttpServletRequest request, MultipartFile file) throws Exception {

        String contentType = file.getContentType();
        log.info("contentType:{}", contentType);
        String name = file.getName();
        log.info("name:{}", name);
        String originalFilename = file.getOriginalFilename();
        log.info("originalFilename:{}", originalFilename);

        String pdfName = originalFilename.substring(0, originalFilename.lastIndexOf(".")) + ".pdf";


        for (int i = 0; i < 5; i++) {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\UserFiles\\桌面\\学习\\workingDir\\" + i + pdfName);

            JodConverterThreadPoolTaskExecutor.execute(() -> {
                try {
                    log.info("--------" + Thread.currentThread().getName() + "：开始转换------");
                    documentConverter.convert(file.getInputStream()).to(fileOutputStream).as(DefaultDocumentFormatRegistry.PDF).execute();
                    log.info("--------" + Thread.currentThread().getName() + "：转换结束------");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

}
