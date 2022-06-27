package com.zhy.java基础.IO.zip;


import com.zhy.java基础.IO.zip.utils.ZipUtil;

import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2022/6/8 11:46
 */
public class ZipTest4 {
    public static void main(String[] args) throws IOException {

        ZipUtil.zip("E:\\需求文档\\文件压缩", "E:\\需求文档", "目录空.zip");

    }
}
