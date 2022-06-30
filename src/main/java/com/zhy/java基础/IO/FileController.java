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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileController {

    @GetMapping("zip/stream")
    public void downloadFile(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        // 创建流压缩需要的集合
        List<InputStream> fileInputStreams = new ArrayList<>();
        List<String> paths = new ArrayList<>();

        File file = new File("E:\\需求文档\\文件压缩");
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

        ZipUtil.zip("E:\\需求文档\\文件压缩", outputStream);

        IOUtils.closeQuietly(outputStream);
    }

    @GetMapping("header/content/disposition")
    public void contentDisposition(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        //PrintWriter writer = response.getWriter();

        String file = "+UTF-8''不送充电器？苹果被判赔巴西一消费者7000元,数%20码,数码综合,好看视频.json";
        String encode = URLEncoder.encode(file, "UTF-8");

        response.setHeader("content-disposition", "attachment;fileName=" + encode);
        System.out.println(encode);
        response.setContentType("application/json");

        //writer.write("{\"code\":2000000,\"data\":{\"total\":12,\"rows\":[{\"createAt\":1655952701785,\"createBy\":\"76f58422b3a74a42b6c8e21a5efca469\",\"id\":\"cff1e1ccae9e4a13879d5c5bff16bdb4\",\"name\":\"88888888888888\"},{\"createAt\":1655952695492,\"createBy\":\"76f58422b3a74a42b6c8e21a5efca469\",\"id\":\"7a0f9a727eb4461295a7747d4b83195a\",\"name\":\"77777777777777\"},{\"createAt\":1655952688687,\"createBy\":\"76f58422b3a74a42b6c8e21a5efca469\",\"id\":\"12fd52ee80724c95ad709714ba39007a\",\"name\":\"11111111111111\"},{\"createAt\":1655691812438,\"createBy\":\"740aa4b88232415eb7cd5c49764bd0f8\",\"id\":\"3c24cd0caa2d4799bfa7005e747ed3bd\",\"name\":\"1344\"},{\"createAt\":1655691353540,\"createBy\":\"740aa4b88232415eb7cd5c49764bd0f8\",\"id\":\"36fa930e884a4a06850f5e1652643e86\",\"name\":\"大厉害大厉害呦\"},{\"createAt\":1655691339950,\"createBy\":\"99a4531f2b7b414090f806fefd3a4faf\",\"id\":\"4b7c61136f394d7fb479ce0fb28e7e3d\",\"name\":\"李伟111\"},{\"createAt\":1655689594641,\"createBy\":\"76f58422b3a74a42b6c8e21a5efca469\",\"id\":\"669c9149ca2444d0b82a76a87069c01d\",\"name\":\"aaaaaaaaaaaaaa\"},{\"createAt\":1655457546811,\"createBy\":\"740aa4b88232415eb7cd5c49764bd0f8\",\"id\":\"7dde6a1f5d9d4a1caf84c902fed35a46\",\"name\":\"2222333333333\"},{\"createAt\":1655453425315,\"createBy\":\"76f58422b3a74a42b6c8e21a5efca469\",\"id\":\"a16b8617040944aab2010fa108667af2\",\"name\":\"5\"},{\"createAt\":1655453421998,\"createBy\":\"76f58422b3a74a42b6c8e21a5efca469\",\"id\":\"e6946aa7db934ed9bb49f9192334bc84\",\"name\":\"3\"},{\"createAt\":1655453283024,\"createBy\":\"76f58422b3a74a42b6c8e21a5efca469\",\"id\":\"ec6c9e6fcad5477387ed66c703fe7484\",\"name\":\"333\"},{\"createAt\":1655434280368,\"createBy\":\"76f58422b3a74a42b6c8e21a5efca469\",\"id\":\"4cbbfdfa743d40508f113a10584b9041\",\"name\":\"3333\"}]},\"success\":true,\"tracer\":\"44698a741ebff3b637612e1dfd536e99\",\"message\":\"OK\",\"status\":200}");

        byte[] bytes = file.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        IOUtils.copy(byteArrayInputStream, outputStream);

        IOUtils.closeQuietly(outputStream);
    }


}
