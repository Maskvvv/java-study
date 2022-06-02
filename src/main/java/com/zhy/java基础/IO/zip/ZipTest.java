package com.zhy.java基础.IO.zip;

import cn.hutool.log.Log;
import org.apache.commons.lang3.ObjectUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author zhouhongyin
 * @since 2022/5/31 16:54
 */
public class ZipTest {

    public static void main(String[] args) throws FileNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream outputStream = new BufferedOutputStream(byteArrayOutputStream);

        OutputStream outputStream1 = new FileOutputStream("F:/压缩文件/test.zip");

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream1)) {

            //File target = new File("F:\\压缩测试\\周宏寅附件3.《高等学校毕业生登记表》-非师范类.doc");
            //File target = new File("F:\\压缩测试\\空");
            File target = new File("F:\\压缩测试");
            //File target = new File("F:\\压缩测试\\毕业设计");

            zipDirectory(target, "", zipOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zip(String targetFilePath, String zipFilePath) throws Exception {
        // 判断带压缩文件在不在
        File targetFile = new File(targetFilePath);
        if (!targetFile.exists()) {
            throw new Exception("压缩文件不存在");
        }

        // 目标目录不存在创建目标目录
        File zipFile = new File(zipFilePath);
        if (!zipFile.exists()) {
            zipFile.mkdirs();
        }

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(zipFile));

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream)) {

            if (targetFile.isFile()) {
                //zip();
            }

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 压缩文件
     *
     * @param target
     * @param outputStream
     * @throws IOException
     */
    public static void zipFile(File target, OutputStream outputStream) throws IOException {

        zipFile(target, new ZipOutputStream(outputStream));
    }

    /**
     * 压缩目录
     *
     * @param target
     * @param outputStream
     * @throws IOException
     */
    public static void zipDirectory(File target, OutputStream outputStream) throws IOException {
        zipDirectory(target, new ZipOutputStream(outputStream));
    }

    /**
     * 压缩文件
     *
     * @param target
     * @param zipOutputStream
     * @throws IOException
     */
    public static void zipFile(File target, ZipOutputStream zipOutputStream) throws IOException {
        zipFile(target, "", zipOutputStream);
    }

    /**
     * 压缩目录
     *
     * @param target
     * @param zipOutputStream
     * @throws IOException
     */
    public static void zipDirectory(File target, ZipOutputStream zipOutputStream) throws IOException {
        zipDirectory(target, "", zipOutputStream);
    }


    /**
     * 压缩文件
     *
     * @param target
     * @param prefixPath
     * @param zipOutputStream
     * @throws IOException
     */
    public static void zipFile(File target, String prefixPath, ZipOutputStream zipOutputStream) throws IOException {

        if (target == null || !target.isFile()) {
            return;
        }

        zipOutputStream.putNextEntry(new ZipEntry(prefixPath + File.separator + target.getName()));

        byte[] bytes = new byte[1024];
        FileInputStream fileOutputStream = new FileInputStream(target);
        while (true) {
            int read = fileOutputStream.read(bytes);
            if (read == -1) break;
            zipOutputStream.write(bytes);
        }

        zipOutputStream.closeEntry();
    }


    /**
     * 压缩目录
     *
     * @param target
     * @param prefixPath
     * @param zipOutputStream
     * @throws IOException
     */
    public static void zipDirectory(File target, String prefixPath, ZipOutputStream zipOutputStream) throws IOException {

        // 带压缩文件的目录和文件
        File[] files = target.listFiles();
        if (ObjectUtils.isEmpty(files)) return;

        for (File file : files) {
            if (file.isFile()) {
                // 压缩文件
                zipFile(file, prefixPath, zipOutputStream);
            } else {
                // 压缩目录
                zipDirectory(file, prefixPath + File.separator + file.getName(), zipOutputStream);
            }

        }

    }

}
