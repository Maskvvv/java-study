package com.zhy.java基础.IO;


import java.io.File;
import java.io.IOException;

/**
 * boolean mkdir()：创建当前File对象表示的目录；
 * boolean mkdirs()：创建当前File对象表示的目录，并在必要时将不存在的父目录也创建出来；
 * boolean delete()：删除当前File对象表示的目录，当前目录必须为空才能删除成功。
 */

public class IOFileDemo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("./a");
        System.out.println(file);
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.isFile());


        file.mkdir();

    }
}
