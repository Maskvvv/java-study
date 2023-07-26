package com.zhy.java基础.nio.path;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/21 9:47
 */
public class PathTest {

    /**
     * Path 用来表示文件路径
     * Paths 是工具类，用来获取 Path 实例
     */
    @Test
    public void test1() {

        Path source = Paths.get("1.txt"); // 相对路径 不带盘符 使用 user.dir 环境变量来定位 1.txt

        Path source1 = Paths.get("d:\\1.txt"); // 绝对路径 代表了  d:\1.txt 反斜杠需要转义

        Path source2 = Paths.get("d:/1.txt"); // 绝对路径 同样代表了  d:\1.txt

        Path projects = Paths.get("d:\\data", "projects"); // 代表了  d:\data\projects

        Path path = Paths.get("d:\\data\\projects\\a\\..\\b");
        System.out.println(path);
        System.out.println(path.normalize()); // 正常化路径 会去除 . 以及 ..
    }

}
