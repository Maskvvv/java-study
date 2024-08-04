package com.zhy.javabase.IO.file;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

/**
 * <p> File API test </p>
 *
 * @author zhouhongyin
 * @since 2023/10/26 14:51
 */
public class IOFileDemo1 {
    public static void main(String[] args) throws IOException {
        File file = new File("./");
        System.out.println(file);
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.isFile());

        //创建一个文件
//        file.createNewFile();

        //删除一个文件
//        file.delete();

        File[] files = file.listFiles();// 列出所有文件和子目录
        for (File file1 : files) {
            System.out.println(file1);
        }
        System.out.println(files);

        //过滤不想要的文件和目录
        String[] list = file.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println(list.toString());


    }

    /**
     * File list filter
     */
    @Test
    public void test1() {

        File file = new File("D:\\UserFiles\\桌面");

        //过滤不想要的文件和目录
        String[] list = file.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("报名信息_20231026");
            }
        });

        System.out.println(Arrays.toString(list));
    }
}
