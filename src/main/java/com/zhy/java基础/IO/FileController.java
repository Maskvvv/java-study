package com.zhy.java基础.IO;

import com.zhy.java基础.IO.zip.utils.ZipUtil;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("upload")
    public void uploadFile(HttpServletRequest request, MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
    }


    @GetMapping("zip")
    public void zipFiles(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        String encode = URLEncoder.encode("+UTF-8''不送充电器？苹果被判赔巴西一消费者7000元,数%20码,数码综合,好看视频.zip", "UTF-8");
        response.setHeader("content-disposition", "attachment;fileName=" + encode);
        System.out.println(encode);
        response.setContentType("text/plain;charset=UTF-8");

        ZipUtil.zip("E:\\需求文档" ,outputStream);

        IOUtils.closeQuietly(outputStream);
    }


}
