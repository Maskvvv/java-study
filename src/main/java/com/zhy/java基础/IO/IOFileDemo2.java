package com.zhy.java基础.IO;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * boolean mkdir()：创建当前File对象表示的目录；
 * boolean mkdirs()：创建当前File对象表示的目录，并在必要时将不存在的父目录也创建出来；
 * boolean delete()：删除当前File对象表示的目录，当前目录必须为空才能删除成功。
 */

public class IOFileDemo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("G:\\qst\\qst需求文档\\迭代9\\dowlond\\问题文档.docx");
        System.out.println(file.getName());
        System.out.println(file);
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.isFile());
        System.out.println(Arrays.toString(file.list()));
        System.out.println(Arrays.toString(file.listFiles()));


        file.mkdir();

    }
}
