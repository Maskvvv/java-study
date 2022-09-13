package com.zhy.java基础.IO.file;

import org.junit.Test;

import java.io.File;
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


}
