package com.zhy.java基础.IO.zip;

import com.zhy.java基础.IO.zip.utils.ZipUtil;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouhongyin
 * @since 2022/5/31 16:54
 */
public class ZipTest1 {

    public static void main(String[] args) throws IOException {

        List<FileInputStream> fileInputStreams = new ArrayList<>();
        List<String> paths = new ArrayList<>();

        File file = new File("F:\\压缩测试");
        File[] files = file.listFiles(File::isFile);

        for (File file1 : files) {
            fileInputStreams.add(new FileInputStream(file1));
            paths.add(file1.getName());
        }
        InputStream zip = null;
        try (FileOutputStream fileOutputStream = new FileOutputStream("F:\\压缩文件\\sadfasdf.zip")) {
            zip = ZipUtil.zip(paths.toArray(new String[0]), fileInputStreams.toArray(new FileInputStream[0]));
            IOUtils.copy(zip, fileOutputStream);

            //System.out.println(zip);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            zip.close();
        }
    }


}
