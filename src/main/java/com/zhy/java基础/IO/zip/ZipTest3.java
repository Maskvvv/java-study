package com.zhy.java基础.IO.zip;

import cn.hutool.core.util.ZipUtil;

import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2022/5/31 16:54
 */
public class ZipTest3 {

    public static void main(String[] args) throws IOException {

        //ZipUtil.zip("F:\\压缩测试", "F:\\压缩文件\\目录空.zip");

        ZipUtil.zip("F:\\压缩测试", "F:\\压缩文件\\目录空.zip");
    }


}
