package com.zhy.java基础.IO.zip;

import org.apache.commons.lang3.ObjectUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhouhongyin
 * @since 2022/5/31 16:54
 */
public class ZipTest1 {

    public static void main(String[] args) throws FileNotFoundException {

        List<FileInputStream> fileInputStreams = new ArrayList<>();
        List<String> paths = new ArrayList<>();

        File file = new File("F:\\压缩测试");
        File[] files = file.listFiles(File::isFile);

        for (File file1 : files) {
            fileInputStreams.add(new FileInputStream(file1));
            paths.add(file1.getName());
        }

        String[] pathArray = paths.toArray(new String[0]);

        try (OutputStream outputStream = new FileOutputStream("F:/压缩文件/test.zip")) {
            ZipUtil.zip(outputStream, pathArray, fileInputStreams.toArray(new FileInputStream[0]));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
