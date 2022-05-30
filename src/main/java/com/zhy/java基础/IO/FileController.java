package com.zhy.java基础.IO;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("file")
public class FileController {

    @GetMapping("download")
    public void downloadFile(HttpServletResponse response) throws IOException {
        InputStream inputStream = new FileInputStream("D:\\UserFiles\\桌面\\周宏寅附件3.《高等学校毕业生登记表》-非师范类.doc");
        //FileOutputStream outputStream = new FileOutputStream("G:\\qst\\qst需求文档\\迭代9\\dowlond\\2022\\ceshi.pdf");

        ServletOutputStream outputStream = response.getOutputStream();

        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("fileName.doc", "UTF-8"));
        response.setContentType("text/plain;charset=UTF-8");

        IOUtils.copy(inputStream, outputStream);

        outputStream.close();
        inputStream.close();
    }

}
