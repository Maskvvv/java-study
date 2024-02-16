package com.zhy.other.hutool.zip;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ZipUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2022/5/30 17:30
 */
public class ZipTest {
    public static void main(String[] args) throws IOException {
        //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //ZipUtil.zip("F:\\压缩测试", "F:/压缩文件/hutool.zip");
        File target = new File("F:\\压缩测试");
        File zip = new File("F:\\压缩文件\\my.zip");

        //ZipUtil.zip(target, zip);
        //ZipUtil.zip("F:\\压缩测试", "F:\\压缩文件\\my1.zip");

        //ZipUtil.zip(target, new FileOutputStream("F:\\压缩文件\\my.zip2"));



    }

    @Test
    public void unzip() {
        ZipUtil.unzip("C:\\Users\\Qstest\\AppData\\Local\\Temp\\jetty-docbase.8088.9322025331285751285\\upload\\importResume900ebae4caf64a18a18895b620a35628\\附件简历.zip",
                "C:\\Users\\Qstest\\AppData\\Local\\Temp\\jetty-docbase.8088.9322025331285751285\\upload\\importResume900ebae4caf64a18a18895b620a35628\\out", CharsetUtil.CHARSET_GBK);


    }
}
