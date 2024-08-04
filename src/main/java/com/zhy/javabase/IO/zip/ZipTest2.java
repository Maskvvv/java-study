package com.zhy.javabase.IO.zip;

import org.apache.commons.compress.utils.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhouhongyin
 * @since 2022/5/31 16:54
 */
public class ZipTest2 {

    public static void main(String[] args) throws IOException {

        try (FileOutputStream fileOutputStream = new FileOutputStream("F:\\压缩文件\\目录空.zip");
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
             FileInputStream fileInputStream1 = new FileInputStream("F:\\压缩测试\\周宏寅附件3.《高等学校毕业生登记表》-非师范类.doc");
             FileInputStream fileInputStream2 = new FileInputStream("F:\\压缩测试\\实习生计划城市排序.txt")) {

            zipOutputStream.putNextEntry(new ZipEntry("//pre/"));

            zipOutputStream.putNextEntry(new ZipEntry("//pre1/周宏寅附件3.《高等学校毕业生登记表》-非师范类.doc"));
            zipOutputStream.putNextEntry(new ZipEntry("//pre1/"));

            IOUtils.copy(fileInputStream1, zipOutputStream);

            zipOutputStream.putNextEntry(new ZipEntry("实习生计划城市排序.txt"));
            IOUtils.copy(fileInputStream2, zipOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
