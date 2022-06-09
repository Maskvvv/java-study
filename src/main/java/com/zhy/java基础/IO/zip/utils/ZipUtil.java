package com.zhy.java基础.IO.zip.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩工具类
 *
 * @author zhouhongyin
 * @since 2022/6/1 15:01
 */
public class ZipUtil {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;


    /**
     * 通过文件字符串路径压缩文件或目录
     *
     * @param target 待压缩文件全路径
     * @param zip    压缩包存放全路径 （----.zip）
     */
    public static void zip(String target, String zip) throws IOException {
        if (StringUtils.isBlank(target) || StringUtils.isBlank(zip)) {
            throw new IOException("压缩路径不能为空");
        }
        zip(new File(target), new File(zip));
    }

    /**
     * 通过 File 对象压缩文件或目录
     *
     * @param target 待压缩文件位置
     * @param zip    压缩包存放位置
     */
    public static void zip(File target, File zip) throws IOException {
        if (target == null || !target.exists()) {
            throw new IOException("带压缩文件为空");
        }
        if (target.isFile()) {
            zipFile(target, zip);
        } else if (target.isDirectory()) {
            zipDirectory(target, zip);
        }
    }

    /**
     * 有输出流的压缩文件或目录
     *
     * @param target       待压缩文件全路径
     * @param outputStream 输出流
     * @return 输出流
     */
    public static OutputStream zip(String target, OutputStream outputStream) throws IOException {
        if (StringUtils.isBlank(target)) {
            throw new IOException("带压缩文件为空");
        }
        return zip(new File(target), outputStream);
    }

    /**
     * 有输出流的压缩文件或目录
     *
     * @param target       待压缩文件
     * @param outputStream 输出流
     * @return 输出流
     */
    public static OutputStream zip(File target, OutputStream outputStream) throws IOException {
        if (target == null || !target.exists()) {
            throw new IOException("带压缩文件为空");
        }

        if (target.isFile()) {
            zipFile(target, outputStream);
        } else if (target.isDirectory()) {
            zipDirectory(target, outputStream);
        }

        return outputStream;
    }

    /**
     * 压缩文件或目录到内存后转为输入流
     *
     * @param target 待压缩文件
     * @return 输入流
     */
    public static InputStream zipToInputStream(String target) throws IOException {
        if (StringUtils.isBlank(target)) {
            throw new IOException("带压缩文件为空");
        }
        return zipToInputStream(new File(target));
    }

    /**
     * 压缩文件或目录到内存后转为输入流
     *
     * @param target 待压缩文件
     * @return 输入流
     */
    public static InputStream zipToInputStream(File target) throws IOException {
        if (target == null || !target.exists()) {
            throw new IOException("带压缩文件为空");
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream)) {
            if (target.isFile()) {
                zipFile(target, bufferedOutputStream);
            } else if (target.isDirectory()) {
                zipDirectory(target, bufferedOutputStream);
            }
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }
    }

    /**
     * 将文件流压缩返回输入流
     *
     * @param paths        流数据在压缩文件中的路径或文件名
     * @param inputStreams 要压缩的源，添加完成后自动关闭流
     */
    public static InputStream zip(List<String> paths, List<InputStream> inputStreams) throws IOException {
        if (ObjectUtils.isEmpty(paths) || ObjectUtils.isEmpty(inputStreams)) {
            throw new IllegalArgumentException("Paths or inputStreams is empty !");
        }
        if (paths.size() != inputStreams.size()) {
            throw new IllegalArgumentException("Paths length is not equals to inputStreams length !");
        }

        return zip(paths.toArray(new String[0]), inputStreams.toArray(new InputStream[0]));
    }


    /**
     * 将文件流压缩返回输入流
     *
     * @param paths        流数据在压缩文件中的路径或文件名
     * @param inputStreams 要压缩的源，添加完成后自动关闭流
     */
    public static InputStream zip(String[] paths, InputStream[] inputStreams) throws IOException {

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream)) {
            zip(bufferedOutputStream, paths, inputStreams);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }

    }

    /**
     * 将文件流压缩到目标流中
     *
     * @param outputStream 目标流，压缩完成自动关闭
     * @param paths        流数据在压缩文件中的路径或文件名
     * @param inputStreams 要压缩的源，添加完成后自动关闭流
     */
    public static void zip(OutputStream outputStream, String[] paths, InputStream[] inputStreams) throws IOException {
        if (ObjectUtils.isEmpty(paths) || ObjectUtils.isEmpty(inputStreams)) {
            throw new IllegalArgumentException("Paths or inputStreams is empty !");
        }
        if (paths.length != inputStreams.length) {
            throw new IllegalArgumentException("Paths length is not equals to inputStreams length !");
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            for (int i = 0; i < paths.length; i++) {
                zipOutputStream.putNextEntry(new ZipEntry(paths[i]));
                byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
                try {
                    while (true) {
                        int read = inputStreams[i].read(bytes);
                        if (read == -1) break;
                        zipOutputStream.write(bytes);
                    }
                    zipOutputStream.closeEntry();
                } catch (Exception e) {
                    throw new IOException(e);
                } finally {
                    if (inputStreams[i] != null) {
                        inputStreams[i].close();
                    }
                }
            }
        }
    }


    //---------------------------------------------------------------------------------------------

    /**
     * 通过 File 对象压缩文件
     *
     * @param target 待压缩文件位置
     * @param zip    压缩包存放位置
     */
    private static void zipFile(File target, File zip) throws IOException {
        if (!zip.exists()) {
            zip.mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(zip)) {
            zipFile(target, fileOutputStream);
        }
    }

    /**
     * 通过 File 对象压缩目录
     *
     * @param target 待压缩文件位置
     * @param zip    压缩包存放位置
     */
    private static void zipDirectory(File target, File zip) throws IOException {
        if (!zip.exists()) {
            zip.mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(zip)) {
            zipDirectory(target, fileOutputStream);
        }

    }

    /**
     * 通过 File 对象压缩文件
     *
     * @param target       待压缩文件位置
     * @param outputStream 压缩包输出流
     */
    private static void zipFile(File target, OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IOException("outputStream为空");
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            zipFile(target, zipOutputStream);
        }

    }

    /**
     * 通过 File 对象压缩目录
     *
     * @param target       待压缩文件位置
     * @param outputStream 压缩包输出流
     */
    private static void zipDirectory(File target, OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IOException("outputStream为空");
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            zipDirectory(target, zipOutputStream);
        }

    }

    /**
     * 压缩文件
     */
    private static void zipFile(File target, ZipOutputStream zipOutputStream) throws IOException {
        zipFile(target, "", zipOutputStream);
    }

    /**
     * 压缩目录
     */
    private static void zipDirectory(File target, ZipOutputStream zipOutputStream) throws IOException {
        zipDirectory(target, "", zipOutputStream);
    }

    /**
     * 压缩文件
     *
     * @param target     待压缩文件
     * @param prefixPath 压缩包内相对路径
     */
    private static void zipFile(File target, String prefixPath, ZipOutputStream zipOutputStream) throws IOException {

        if (target == null || !target.isFile()) {
            throw new IOException("zipOutputStream为空");
        }

        if (zipOutputStream == null) {
            throw new IOException("zipOutputStream为空");
        }

        zipOutputStream.putNextEntry(new ZipEntry(prefixPath + File.separator + target.getName()));

        byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];

        try (FileInputStream fileInputStream = new FileInputStream(target)) {
            while (true) {
                int read = fileInputStream.read(bytes);
                if (read == -1) break;
                zipOutputStream.write(bytes);
            }
            zipOutputStream.closeEntry();
        }

    }


    /**
     * 压缩目录
     *
     * @param target     待压缩文件
     * @param prefixPath 压缩包内相对路径
     */
    private static void zipDirectory(File target, String prefixPath, ZipOutputStream zipOutputStream) throws IOException {
        if (target == null || !target.exists()) {
            throw new IOException("待压缩目录不存在");
        }

        // 带压缩文件的目录和文件
        File[] files = target.listFiles();
        if (ObjectUtils.isEmpty(files)) {
            return;
        }

        if (zipOutputStream == null) {
            throw new IOException("zipOutputStream为空");
        }

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
