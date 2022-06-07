package com.zhy.java基础.IO;

import com.zhy.java基础.IO.zip.ZipUtil;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("file")
public class FileController {

    @GetMapping("download")
    public void downloadFile(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        // 创建流压缩需要的集合
        List<InputStream> fileInputStreams = new ArrayList<>();
        List<String> paths = new ArrayList<>();

        File file = new File("F:\\压缩测试");
        File[] files = file.listFiles(File::isFile);

        for (File file1 : files) {
            fileInputStreams.add(new FileInputStream(file1));
            paths.add(file1.getName());
        }

        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("test.zip", "UTF-8"));
        response.setContentType("text/plain;charset=UTF-8");

        // 方式一：
        ZipUtil.zip(outputStream, paths.toArray(new String[0]), fileInputStreams.toArray(new InputStream[0]));

        // 方式二：
        //IOUtils.copy(ZipUtil.zip(paths, fileInputStreams), outputStream);

        IOUtils.closeQuietly(outputStream);
    }

}
