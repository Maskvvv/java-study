package com.zhy.jodconverter;

import cn.hutool.core.net.URLEncoder;
import cn.hutool.http.Header;
import lombok.extern.slf4j.Slf4j;
import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.document.DefaultDocumentFormatRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("jodconverter")
@Slf4j
public class JodconverterController {

    @Autowired(required = false)
    DocumentConverter documentConverter;

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
}
