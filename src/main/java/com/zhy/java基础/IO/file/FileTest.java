package com.zhy.java基础.IO.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author zhouhongyin
 * @since 2022/9/13 14:19
 */
public class FileTest {
    @Test
    public void fileTest() {

        String path = "F:\\压缩测试\\新建文本文档.txt";

        File file = new File(path);
        boolean delete = file.delete();
        System.out.println(delete);

    }

    @Test
    public void filesTest() {

        String path = "F:\\压缩测试\\周宏寅附件3.《高等学校毕业生登记表》-非师范类.doc";

        File file = new File(path);

        try {
            Files.delete(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void log() throws IOException {
        File file = new File("D:\\UserFiles\\桌面\\stdout.log");
        BufferedReader reader = FileUtil.getReader(file, StandardCharsets.UTF_8);

        File log = new File("D:\\UserFiles\\桌面\\2月8号.log");
        BufferedWriter writer = FileUtil.getWriter(log, StandardCharsets.UTF_8, true);


        while (true){
            String line;

            try {
                line = reader.readLine();
            } catch (Exception e) {
                break;
            }

            if (StrUtil.startWith(line, "2023-02-08")) {
                writer.write(line + "\n");
                break;
            }
        }

        while (true){
            String line;

            try {
                line = reader.readLine();
                writer.write(line + "\n");
            } catch (Exception e) {
                break;
            }

            if (StrUtil.startWith(line, "2023-02-09")) {
                break;
            }
        }

        reader.close();
        writer.close();


    }


}
