package com.zhy.java基础.IO;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * boolean mkdir()：创建当前File对象表示的目录；
 * boolean mkdirs()：创建当前File对象表示的目录，并在必要时将不存在的父目录也创建出来；
 * boolean delete()：删除当前File对象表示的目录，当前目录必须为空才能删除成功。
 */

public class IOFileDemo2 {
    public static void main(String[] args) throws IOException {

        File file = new File("F:\\压缩测试");
        System.out.println(file.getName());
        System.out.println(file);
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());

        // 是否为文件
        // false
        System.out.println(file.isFile());

        System.out.println(file.isDirectory());

        // 相对路径
        // [周宏寅附件3.《高等学校毕业生登记表》-非师范类.doc, 实习生计划城市排序.txt, 新建文本文档.txt, 毕业设计, 附件6：正文加目录.doc]
        System.out.println(Arrays.toString(file.list()));

        // 全路径
        // [F:\压缩测试\周宏寅附件3.《高等学校毕业生登记表》-非师范类.doc, F:\压缩测试\实习生计划城市排序.txt, F:\压缩测试\新建文本文档.txt, F:\压缩测试\毕业设计, F:\压缩测试\附件6：正文加目录.doc]
        System.out.println(Arrays.toString(file.listFiles()));

        for (File listFile : file.listFiles(File::isFile)) {
            System.out.println(listFile.getName());
        }

        //file.mkdir();

    }
}
