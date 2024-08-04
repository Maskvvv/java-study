package com.zhy.javabase.IO.zip.utils;

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
     * @param targetSrc 待压缩文件全路径
     * @param zipSrc    压缩包存放目录 (E:\xx\xxxx\xxxxx)
     * @param zipName   压缩包名 （----.zip）
     */
    public static void zip(String targetSrc, String zipSrc, String zipName) throws IOException {
        if (StringUtils.isBlank(targetSrc) || StringUtils.isBlank(zipSrc)) {
            throw new IOException("压缩路径不能为空");
        }
        zip(new File(targetSrc), new File(zipSrc), zipName);
    }

    /**
     * 通过 File 对象压缩文件或目录
     *
     * @param targetSrc 待压缩文件位置
     * @param zipSrc    压缩包存放目录 (E:\xx\xxxx\xxxxx)
     * @param zipName   压缩包文件名 （----.zip）
     */
    public static void zip(File targetSrc, File zipSrc, String zipName) throws IOException {
        if (targetSrc == null || !targetSrc.exists()) {
            throw new IOException("带压缩文件为空");
        }
        if (targetSrc.isFile()) {
            zipFile(targetSrc, zipSrc, zipName);
        } else if (targetSrc.isDirectory()) {
            zipDirectory(targetSrc, zipSrc, zipName);
        }
    }

    /**
     * 有输出流的压缩文件或目录
     *
     * @param targetSrc       待压缩文件全路径
     * @param outputStream 输出流
     * @return 输出流
     */
    public static OutputStream zip(String targetSrc, OutputStream outputStream) throws IOException {
        if (StringUtils.isBlank(targetSrc)) {
            throw new IOException("带压缩文件为空");
        }
        return zip(new File(targetSrc), outputStream);
    }

    /**
     * 有输出流的压缩文件或目录
     *
     * @param targetSrc       待压缩文件
     * @param outputStream 输出流
     * @return 输出流
     */
    public static OutputStream zip(File targetSrc, OutputStream outputStream) throws IOException {
        if (targetSrc == null || !targetSrc.exists()) {
            throw new IOException("带压缩文件为空");
        }

        if (targetSrc.isFile()) {
            zipFile(targetSrc, outputStream);
        } else if (targetSrc.isDirectory()) {
            zipDirectory(targetSrc, outputStream);
        }

        return outputStream;
    }

    /**
     * 压缩文件或目录到内存后转为输入流
     *
     * @param targetSrc 待压缩文件
     * @return 输入流
     */
    public static InputStream zipToInputStream(String targetSrc) throws IOException {
        if (StringUtils.isBlank(targetSrc)) {
            throw new IOException("带压缩文件为空");
        }
        return zipToInputStream(new File(targetSrc));
    }

    /**
     * 压缩文件或目录到内存后转为输入流
     *
     * @param targetSrc 待压缩文件
     * @return 输入流
     */
    public static InputStream zipToInputStream(File targetSrc) throws IOException {
        if (targetSrc == null || !targetSrc.exists()) {
            throw new IOException("带压缩文件为空");
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream)) {
            if (targetSrc.isFile()) {
                zipFile(targetSrc, bufferedOutputStream);
            } else if (targetSrc.isDirectory()) {
                zipDirectory(targetSrc, bufferedOutputStream);
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
                        zipOutputStream.write(bytes, 0, read);
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
     * @param targetSrc 待压缩文件位置
     * @param zipSrc    压缩包存放目录 (E:\xx\xxxx\xxxxx)
     * @param zipName   压缩包名 （----.zip）
     */
    private static void zipFile(File targetSrc, File zipSrc, String zipName) throws IOException {
        if (!zipSrc.exists()) {
            zipSrc.mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(zipSrc.getAbsolutePath() + File.separator + zipName)) {
            zipFile(targetSrc, fileOutputStream);
        }
    }

    /**
     * 通过 File 对象压缩目录
     *
     * @param targetSrc 待压缩文件位置
     * @param zipSrc    压缩包存放目录 (E:\xx\xxxx\xxxxx)
     * @param zipName   压缩包名 （----.zip）
     */
    private static void zipDirectory(File targetSrc, File zipSrc, String zipName) throws IOException {
        if (!zipSrc.exists()) {
            zipSrc.mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(zipSrc.getAbsolutePath() + File.separator + zipName)) {
            zipDirectory(targetSrc, fileOutputStream);
        }

    }

    /**
     * 通过 File 对象压缩文件
     *
     * @param targetSrc       待压缩文件位置
     * @param outputStream 压缩包输出流
     */
    private static void zipFile(File targetSrc, OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IOException("outputStream为空");
        }

        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        zipFile(targetSrc, zipOutputStream);


    }

    /**
     * 通过 File 对象压缩目录
     *
     * @param targetSrc       待压缩文件位置
     * @param outputStream 压缩包输出流
     */
    private static void zipDirectory(File targetSrc, OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IOException("outputStream为空");
        }

        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        zipDirectory(targetSrc, zipOutputStream);


    }

    /**
     * 压缩文件
     */
    private static void zipFile(File targetSrc, ZipOutputStream zipOutputStream) throws IOException {
        try {
            zipFile(targetSrc, "", zipOutputStream);
        } catch (IOException e) {
            throw e;
        } finally {
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
        }
    }

    /**
     * 压缩目录
     */
    private static void zipDirectory(File targetSrc, ZipOutputStream zipOutputStream) throws IOException {
        try {
            zipDirectory(targetSrc, "", zipOutputStream);
        } catch (IOException e) {
            throw e;
        } finally {
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param targetSrc     待压缩文件
     * @param prefixPath 压缩包内相对路径
     */
    private static void zipFile(File targetSrc, String prefixPath, ZipOutputStream zipOutputStream) throws IOException {

        if (targetSrc == null || !targetSrc.isFile()) {
            throw new IOException("zipOutputStream为空");
        }

        if (zipOutputStream == null) {
            throw new IOException("zipOutputStream为空");
        }

        byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
        zipOutputStream.putNextEntry(new ZipEntry(prefixPath + File.separator + targetSrc.getName()));

        try (FileInputStream fileInputStream = new FileInputStream(targetSrc)) {
            while (true) {
                int read = fileInputStream.read(bytes);
                if (read == -1) break;
                zipOutputStream.write(bytes, 0, read);
            }
            zipOutputStream.closeEntry();
        }

    }


    /**
     * 压缩目录
     *
     * @param targetSrc     待压缩文件
     * @param prefixPath 压缩包内相对路径
     */
    private static void zipDirectory(File targetSrc, String prefixPath, ZipOutputStream zipOutputStream) throws IOException {
        if (targetSrc == null || !targetSrc.exists()) {
            throw new IOException("待压缩目录不存在");
        }

        // 带压缩文件的目录和文件
        File[] files = targetSrc.listFiles();
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
