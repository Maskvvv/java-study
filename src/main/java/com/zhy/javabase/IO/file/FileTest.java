package com.zhy.javabase.IO.file;

import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

        String startTime = "2023-02-21";
        String endTime = "2023-02-22";

        File file = new File("D:\\UserFiles\\桌面\\stdout.log");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        File log = new File("D:\\UserFiles\\桌面\\2月21号.log");
        BufferedWriter writer = new BufferedWriter(new FileWriter(log));


        while (true){
            String line;

            try {
                line = reader.readLine();
            } catch (Exception e) {
                break;
            }

            if (StrUtil.startWith(line, startTime)) {
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

            if (StrUtil.startWith(line, endTime)) {
                break;
            }
        }

        reader.close();
        writer.close();


    }


}
